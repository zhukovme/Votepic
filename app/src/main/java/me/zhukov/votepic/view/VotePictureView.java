package me.zhukov.votepic.view;

import me.zhukov.votepic.ui.view.GifView;

/**
 * @author Michael Zhukov
 */
public interface VotePictureView extends ViewMvp {

    GifView getFirstGif();
    GifView getSecondGif();
}
