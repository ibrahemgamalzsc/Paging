package com.example.paging.data;

import com.example.paging.pojo.MovieResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(@Query("page") int page);
}
