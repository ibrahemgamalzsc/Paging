package com.example.paging.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.paging.pojo.Movie;

import javax.inject.Inject;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;
    private final MovieDataSource movieDataSource;

    @Inject
    public MovieDataSourceFactory(MovieDataSource movieDataSource) {
        this.movieDataSource = movieDataSource;
        movieDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
