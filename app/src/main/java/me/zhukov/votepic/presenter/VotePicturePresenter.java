package me.zhukov.votepic.presenter;

import java.util.LinkedList;
import java.util.Queue;

import me.zhukov.votepic.api.Fetcher;
import me.zhukov.votepic.model.GifMovie;
import me.zhukov.votepic.view.VotePictureView;
import rx.Observable;

/**
 * @author Michael Zhukov
 */
public class VotePicturePresenter {

    private VotePictureView view;
    private Queue<GifMovie> gifMovieQueue;

    public VotePicturePresenter(VotePictureView view) {
        this.view = view;
        gifMovieQueue = new LinkedList<>();
        fetchImage();
    }

    public void fetchImage() {
        Fetcher
                .fetchImage(view.getContext())
                .repeat(2)
                .subscribe(
                        gifMovieQueue::offer,
                        throwable -> {
                            view.onError("Something went wrong");
                            gifMovieQueue.clear();
                            throwable.printStackTrace();
                        });
    }

    public GifMovie getImage() {
        if (gifMovieQueue.size() <= 1) {
            fetchImage();
        }
        return gifMovieQueue.poll();
    }
}
