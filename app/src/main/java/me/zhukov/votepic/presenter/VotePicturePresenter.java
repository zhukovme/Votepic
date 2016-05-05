package me.zhukov.votepic.presenter;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.Queue;

import me.zhukov.votepic.api.Fetcher;
import me.zhukov.votepic.model.RandomGif;
import me.zhukov.votepic.view.VotePictureView;
import rx.Observable;

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

    public void setup() {
//        Fetcher
//                .fetchGiphyRandom(view.getContext())
//                .repeat(3)
//                .flatMap(response -> Observable.just(response.getRandomGif()))
//                .subscribe(RandomGif::getImage,
//                            throwable -> {
//                                view.onError("Error");
//                                throwable.printStackTrace();
//                            }
//                });
    }

    public void fetchPicture() {
        Fetcher
                .fetchGiphyRandom(view.getContext())
                .flatMap(response -> Observable.just(response.getRandomGif()))
                .subscribe(
                        randomGif -> Picasso
                                .with(view.getContext())
                                .load(randomGif.getImageUrl())
                                .into(view.getFirstGif()),
                        throwable -> view.onError("Error"));
    }
}
