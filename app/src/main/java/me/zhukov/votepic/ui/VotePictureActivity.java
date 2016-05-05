package me.zhukov.votepic.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import me.zhukov.votepic.R;
import me.zhukov.votepic.presenter.VotePicturePresenter;
import me.zhukov.votepic.view.VotePictureView;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class VotePictureActivity extends AppCompatActivity implements VotePictureView {

    private Toolbar toolbar;
    private GifImageView givFirst;
    private GifImageView givSecond;

    private VotePicturePresenter votePicturePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_picture);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        givFirst = (GifImageView) findViewById(R.id.giv_first);
        givSecond = (GifImageView) findViewById(R.id.giv_second);

        votePicturePresenter = new VotePicturePresenter(this);
        votePicturePresenter.setup();

        toolbar.setOnClickListener(v -> votePicturePresenter.fetchPicture());
    }

    @Override
    public GifImageView getFirstGif() {
        return givFirst;
    }

    @Override
    public GifImageView getSecondGif() {
        return givSecond;
    }

    @Override
    public void onError(String msg) {
        Snackbar
                .make(toolbar, msg, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
