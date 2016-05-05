package me.zhukov.votepic.view;

import pl.droidsonroids.gif.GifImageView;

/**
 * @author Michael Zhukov
 */
public interface VotePictureView extends ViewMvp {

    GifImageView getFirstGif();
    GifImageView getSecondGif();
}
