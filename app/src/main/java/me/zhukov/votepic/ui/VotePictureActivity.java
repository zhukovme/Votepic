package me.zhukov.votepic.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import me.zhukov.votepic.R;
import me.zhukov.votepic.model.GifMovie;
import me.zhukov.votepic.presenter.VotePicturePresenter;
import me.zhukov.votepic.ui.view.GifMovieView;
import me.zhukov.votepic.view.VotePictureView;

public class VotePictureActivity extends AppCompatActivity implements VotePictureView {

    private Toolbar toolbar;
    private ProgressBar pbFirst;
    private ProgressBar pbSecond;
    private GifMovieView ivFirst;
    private GifMovieView ivSecond;

    private VotePicturePresenter votePicturePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_picture);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        pbFirst = (ProgressBar) findViewById(R.id.pb_first);
        pbSecond = (ProgressBar) findViewById(R.id.pb_second);

        ivFirst = (GifMovieView) findViewById(R.id.iv_first);
        ivSecond = (GifMovieView) findViewById(R.id.iv_second);

        votePicturePresenter = new VotePicturePresenter(this);

        toolbar.setOnClickListener(v -> ivFirst.setMovie(votePicturePresenter.getImage().getMovie()));
//        setIvFirst();
//        setIvSecond();
    }

    public void setIvFirst() {
        GifMovie gifMovie = votePicturePresenter.getImage();
        ivFirst.setMovie(gifMovie.getMovie());
    }

    public void setIvSecond() {
        GifMovie gifMovie = votePicturePresenter.getImage();
        ivSecond.setMovie(gifMovie.getMovie());
    }

    public void onFirstIvClick(View view) {
        setIvSecond();
    }

    public void onSecondIvClick(View view) {
        setIvFirst();
    }

    @Override
    public void onError(String msg) {
        Snackbar
                .make(toolbar, msg, Snackbar.LENGTH_LONG)
                .setAction("RETRY", v -> { votePicturePresenter.fetchImage(); })
                .show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
