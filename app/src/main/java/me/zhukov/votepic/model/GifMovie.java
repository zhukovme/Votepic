package me.zhukov.votepic.model;

import android.graphics.Movie;

/**
 * @author Michael Zhukov
 */
public class GifMovie {

    private String id;
    private Movie movie;
    private int width;
    private int height;

    public GifMovie() {
    }

    public GifMovie(String id, Movie movie, int width, int height) {
        this.id = id;
        this.movie = movie;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
