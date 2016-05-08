package me.zhukov.votepic.api;

import android.content.Context;

import java.util.LinkedList;
import java.util.Queue;

import me.zhukov.votepic.data.GifImage;
import rx.Observable;

/**
 * @author Michael Zhukov
 */
public class Repository {

    private static Queue<GifImage> gifQueue = new LinkedList<>();

    public static Observable<GifImage> getImage(Context context) {
        if (gifQueue.isEmpty()) {
            addToQueue(context);
            return Fetcher.fetchImage(context);
        } else if (gifQueue.size() == 1) {
            addToQueue(context);
            return Observable.just(gifQueue.poll());
        }
        return Observable.just(gifQueue.poll());
    }

    private static void addToQueue(Context context) {
        Fetcher
                .fetchImage(context)
                .subscribe(
                        gifQueue::offer,
                        Throwable::printStackTrace);
    }
}
