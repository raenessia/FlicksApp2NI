package com.example.android.flicksapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String posterPath;
    String title;
    String overview;
    String backdropPath;
    double ratingAverage;
    int movieId;

    // Empty constructor needed by the Parceler library
    public Movie() {
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        ratingAverage = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");
    }

    public static List<Movie> fromJsonArray(JSONArray jsonMovieArray) throws JSONException{
        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < jsonMovieArray.length(); ++i){
            movies.add(new Movie(jsonMovieArray.getJSONObject(i)));
        }

        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public double getRatingAverage() {
        return ratingAverage;
    }

    public int getMovieId() {
        return movieId;
    }
}
