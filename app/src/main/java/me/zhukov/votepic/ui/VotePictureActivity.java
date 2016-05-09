package me.zhukov.votepic.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.zhukov.votepic.R;
import me.zhukov.votepic.api.Repository;
import me.zhukov.votepic.data.GifImage;
import pl.droidsonroids.gif.GifImageView;

/**
 * @author Michael Zhukov
 */
public class VotePictureActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ProgressBar pbFirst;
    private ProgressBar pbSecond;
    private Button btnRetryFirst;
    private Button btnRetrySecond;
    private GifImageView givFirst;
    private GifImageView givSecond;
    private TextView tvAboutFirst;
    private TextView tvAboutSecond;

    private GifImage gifImageFirst;
    private GifImage gifImageSecond;

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

        givFirst = (GifImageView) findViewById(R.id.giv_first);
        givSecond = (GifImageView) findViewById(R.id.giv_second);

        tvAboutFirst = (TextView) findViewById(R.id.tv_about_first);
        tvAboutSecond = (TextView) findViewById(R.id.tv_about_second);

        VotePictureActivity lastActivity = (VotePictureActivity)
                getLastCustomNonConfigurationInstance();
        if (lastActivity != null) {
            gifImageFirst = lastActivity.gifImageFirst;
            gifImageSecond = lastActivity.gifImageSecond;
        }

        if (gifImageFirst != null) {
            setFirstGif(gifImageFirst);
        } else {
            loadFirstGif();
        }

        if (gifImageSecond != null) {
            setSecondGif(gifImageSecond);
        } else {
            loadSecondGif();
        }
    }

    @Override
    public AppCompatActivity onRetainCustomNonConfigurationInstance() {
        return this;
    }

    public void onFirstGifClick(View view) {
        loadSecondGif();
    }

    public void onSecondGifClick(View view) {
        loadFirstGif();
    }

    public void onRetryBtnFirstClick(View view) {
        loadFirstGif();
    }

    public void onRetryBtnSecondClick(View view) {
        loadSecondGif();
    }

    private void loadFirstGif() {
        onFirstGifLoading();
        Repository
                .getImage(this)
                .subscribe(
                        this::setFirstGif,
                        throwable -> {
                            onFirstError();
                            throwable.printStackTrace();
                        });
    }

    private void loadSecondGif() {
        onSecondGifLoading();
        Repository
                .getImage(this)
                .subscribe(
                        this::setSecondGif,
                        throwable -> {
                            onSecondError();
                            throwable.printStackTrace();
                        });
    }

    private void setFirstGif(GifImage gifImage) {
        givFirst.setImageDrawable(gifImage.getGifDrawable());
        tvAboutFirst.setText(gifImage.getId());
        onFirstGifLoaded();
        gifImageFirst = gifImage;
    }

    private void setSecondGif(GifImage gifImage) {
        givSecond.setImageDrawable(gifImage.getGifDrawable());
        tvAboutSecond.setText(gifImage.getId());
        onSecondGifLoaded();
        gifImageSecond = gifImage;
    }

    private void onFirstGifLoaded() {
        givFirst.setVisibility(View.VISIBLE);
        pbFirst.setVisibility(View.GONE);
        btnRetryFirst.setVisibility(View.GONE);
        givSecond.setClickable(true);
    }

    private void onFirstGifLoading() {
        gifImageFirst = null;
        pbFirst.setVisibility(View.VISIBLE);
        givFirst.setVisibility(View.GONE);
        btnRetryFirst.setVisibility(View.GONE);
        tvAboutFirst.setText("");
        givSecond.setClickable(false);
    }

    private void onSecondGifLoaded() {
        givSecond.setVisibility(View.VISIBLE);
        pbSecond.setVisibility(View.GONE);
        btnRetrySecond.setVisibility(View.GONE);
        givFirst.setClickable(true);
    }

    private void onSecondGifLoading() {
        gifImageSecond = null;
        pbSecond.setVisibility(View.VISIBLE);
        givSecond.setVisibility(View.GONE);
        btnRetrySecond.setVisibility(View.GONE);
        tvAboutSecond.setText("");
        givFirst.setClickable(false);
    }

    private void onFirstError() {
        btnRetryFirst.setVisibility(View.VISIBLE);
        givFirst.setVisibility(View.GONE);
        pbFirst.setVisibility(View.GONE);
        tvAboutFirst.setText("");
        showErrorSnackbar();
    }

    private void onSecondError() {
        btnRetrySecond.setVisibility(View.VISIBLE);
        givSecond.setVisibility(View.GONE);
        pbSecond.setVisibility(View.GONE);
        tvAboutSecond.setText("");
        showErrorSnackbar();
    }

    private void showErrorSnackbar() {
        Snackbar
                .make(toolbar, R.string.error_msg, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vote_picture, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close_app:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
