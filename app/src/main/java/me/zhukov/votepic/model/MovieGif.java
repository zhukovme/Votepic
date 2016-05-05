package me.zhukov.votepic.model;

import android.graphics.Movie;

/**
 * @author Michael Zhukov
 */
public class MovieGif {

    private Movie movie;
    private String id;
    private int width;
    private int height;

    public MovieGif(Movie movie, String id, int width, int height) {
        this.movie = movie;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
