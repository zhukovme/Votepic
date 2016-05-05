package me.zhukov.votepic.presenter;

import java.util.LinkedList;
import java.util.Queue;

import me.zhukov.votepic.api.Fetcher;
import me.zhukov.votepic.model.RandomGif;
import me.zhukov.votepic.view.VotePictureView;

/**
 * @author Michael Zhukov
 */
public class VotePicturePresenter {

    private Queue<RandomGif> gifQueue;

    private VotePictureView view;

    public VotePicturePresenter(VotePictureView view) {
        this.view = view;
        gifQueue = new LinkedList<>();
    }

    public void fetchPicture() {
        Fetcher
                .fetchGifOriginal(view.getContext())
                .subscribe(
                        gifImage -> view.getFirstGif().setMovie(gifImage.getMovie()),
                        throwable -> {
                            view.onError("Error");
                            throwable.printStackTrace();
                        });
    }
}
