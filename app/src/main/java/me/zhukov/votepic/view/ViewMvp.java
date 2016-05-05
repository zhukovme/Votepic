package me.zhukov.votepic.view;

import android.content.Context;

/**
 * @author Michael Zhukov
 */
public interface ViewMvp {

    void onError(String msg);
    Context getContext();
}
