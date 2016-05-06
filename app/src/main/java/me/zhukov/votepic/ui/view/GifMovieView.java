package me.zhukov.votepic.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Michael Zhukov
 */
public class GifMovieView extends View {

    private static final int DEFAULT_MOVIEW_DURATION = 1000;

    private Movie movie;

    private long movieStart;
    private int currentAnimationTime = 0;

    private float left;
    private float top;

    private float scale;

    private int measuredMovieWidth;
    private int measuredMovieHeight;

    private volatile boolean paused = false;
    private boolean visible = true;

    public GifMovieView(Context context) {
        super(context);
        init();
    }

    public GifMovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GifMovieView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public void setMovie(int resourceId) {
        movie = Movie.decodeStream(getResources().openRawResource(resourceId));
        requestLayout();
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        requestLayout();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovieTime(int time) {
        currentAnimationTime = time;
        invalidate();
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        if (!paused) {
            movieStart = android.os.SystemClock.uptimeMillis() - currentAnimationTime;
        }
        invalidate();
    }

    public boolean isPaused() {
        return this.paused;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (movie != null) {
            int movieWidth = movie.width();
            int movieHeight = movie.height();

            float scaleH = 1f;
            int measureModeWidth = MeasureSpec.getMode(widthMeasureSpec);

            if (measureModeWidth != MeasureSpec.UNSPECIFIED) {
                int maximumWidth = MeasureSpec.getSize(widthMeasureSpec);
                if (movieWidth > maximumWidth) {
                    scaleH = (float) movieWidth / (float) maximumWidth;
                }
            }

            float scaleW = 1f;
            int measureModeHeight = MeasureSpec.getMode(heightMeasureSpec);

            if (measureModeHeight != MeasureSpec.UNSPECIFIED) {
                int maximumHeight = MeasureSpec.getSize(heightMeasureSpec);
                if (movieHeight > maximumHeight) {
                    scaleW = (float) movieHeight / (float) maximumHeight;
                }
            }

            scale = 1f / Math.max(scaleH, scaleW);

            measuredMovieWidth = (int) (movieWidth * scale);
            measuredMovieHeight = (int) (movieHeight * scale);

            setMeasuredDimension(measuredMovieWidth, measuredMovieHeight);

        } else {
            setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        left = (getWidth() - measuredMovieWidth) / 2f;
        top = (getHeight() - measuredMovieHeight) / 2f;
        visible = getVisibility() == View.VISIBLE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (movie != null) {
            if (!paused) {
                updateAnimationTime();
                drawMovieFrame(canvas);
                invalidateView();
            } else {
                drawMovieFrame(canvas);
            }
        }
    }

    private void invalidateView() {
        if(visible) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                postInvalidateOnAnimation();
            } else {
                invalidate();
            }
        }
    }

    private void updateAnimationTime() {
        long now = android.os.SystemClock.uptimeMillis();
        if (movieStart == 0) {
            movieStart = now;
        }
        int dur = movie.duration();
        if (dur == 0) {
            dur = DEFAULT_MOVIEW_DURATION;
        }
        currentAnimationTime = (int) ((now - movieStart) % dur);
    }

    /**
     * Draw current GIF frame
     */
    private void drawMovieFrame(Canvas canvas) {

        movie.setTime(currentAnimationTime);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(scale, scale);
        movie.draw(canvas, left / scale, top / scale);
        canvas.restore();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        visible = screenState == SCREEN_STATE_ON;
        invalidateView();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        visible = visibility == View.VISIBLE;
        invalidateView();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        visible = visibility == View.VISIBLE;
        invalidateView();
    }
}
