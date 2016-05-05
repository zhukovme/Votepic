package me.zhukov.votepic.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import me.zhukov.votepic.R;
import me.zhukov.votepic.presenter.VotePicturePresenter;
import me.zhukov.votepic.ui.view.GifView;
import me.zhukov.votepic.view.VotePictureView;

public class VotePictureActivity extends AppCompatActivity implements VotePictureView {

    private Toolbar toolbar;
    private GifView givFirst;
    private GifView givSecond;

    private VotePicturePresenter votePicturePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_picture);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        givFirst = (GifView) findViewById(R.id.giv_first);
        givSecond = (GifView) findViewById(R.id.giv_second);

        votePicturePresenter = new VotePicturePresenter(this);

        toolbar.setOnClickListener(v -> {
            Snackbar.make(toolbar, "Privet", Snackbar.LENGTH_LONG).show();
            votePicturePresenter.fetchPicture();
        });
    }

    @Override
    public GifView getFirstGif() {
        return givFirst;
    }

    @Override
    public GifView getSecondGif() {
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
