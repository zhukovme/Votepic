package me.zhukov.votepic.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import me.zhukov.votepic.R;
import me.zhukov.votepic.api.Repository;
import me.zhukov.votepic.ui.view.GifMovieView;

public class VotePictureActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ProgressBar pbFirst;
    private ProgressBar pbSecond;
    private Button btnRetryFirst;
    private Button btnRetrySecond;
    private GifMovieView ivFirst;
    private GifMovieView ivSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_picture);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        pbFirst = (ProgressBar) findViewById(R.id.pb_first);
        pbSecond = (ProgressBar) findViewById(R.id.pb_second);

        btnRetryFirst = (Button) findViewById(R.id.btn_retry_first);
        btnRetrySecond = (Button) findViewById(R.id.btn_retry_second);

        ivFirst = (GifMovieView) findViewById(R.id.iv_first);
        ivSecond = (GifMovieView) findViewById(R.id.iv_second);

        setIvFirst();
        setIvSecond();
    }

    public void onFirstIvClick(View view) {
        onSecondIvLoading();
        setIvSecond();
    }

    public void onSecondIvClick(View view) {
        onFirstIvLoading();
        setIvFirst();
    }

    public void onRetryFirstClick(View view) {
        setIvFirst();
    }

    public void onRetrySecondClick(View view) {
        setIvSecond();
    }

    private void setIvFirst() {
        onFirstIvLoading();
        Repository
                .getImage(this)
                .subscribe(
                        gifMovie -> ivFirst.setMovie(gifMovie.getMovie()),
                        throwable -> {
                            onFirstError();
                            throwable.printStackTrace();
                        },
                        this::onFirstIvLoaded);
    }

    private void setIvSecond() {
        onSecondIvLoading();
        Repository
                .getImage(this)
                .subscribe(
                        gifMovie -> ivSecond.setMovie(gifMovie.getMovie()),
                        throwable -> {
                            onSecondError();
                            throwable.printStackTrace();
                        },
                        this::onSecondIvLoaded);
    }

    private void onFirstIvLoaded() {
        ivFirst.setVisibility(View.VISIBLE);
        pbFirst.setVisibility(View.GONE);
        btnRetryFirst.setVisibility(View.GONE);
    }

    private void onFirstIvLoading() {
        ivFirst.setVisibility(View.GONE);
        pbFirst.setVisibility(View.VISIBLE);
        btnRetryFirst.setVisibility(View.GONE);
    }

    private void onSecondIvLoaded() {
        ivSecond.setVisibility(View.VISIBLE);
        pbSecond.setVisibility(View.GONE);
        btnRetrySecond.setVisibility(View.GONE);
    }

    private void onSecondIvLoading() {
        ivSecond.setVisibility(View.GONE);
        pbSecond.setVisibility(View.VISIBLE);
        btnRetrySecond.setVisibility(View.GONE);
    }

    private void onFirstError() {
        ivFirst.setVisibility(View.GONE);
        pbFirst.setVisibility(View.GONE);
        btnRetryFirst.setVisibility(View.VISIBLE);
        showErrorSnackbar();
    }

    private void onSecondError() {
        ivSecond.setVisibility(View.GONE);
        pbSecond.setVisibility(View.GONE);
        btnRetrySecond.setVisibility(View.VISIBLE);
        showErrorSnackbar();
    }

    private void showErrorSnackbar() {
        Snackbar
                .make(toolbar, "Something went wrong", Snackbar.LENGTH_LONG)
                .show();
    }
}
