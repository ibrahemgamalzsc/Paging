package com.example.paging.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.paging.pojo.Movie;
import com.example.paging.pojo.MovieResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {
    private static final int FIRST_PAGE = 1;

    private final APIService apiService;
    private final CompositeDisposable disposable;
    private MutableLiveData<ResponseState> responseState;

    @Inject
    public MovieDataSource(APIService apiService, CompositeDisposable disposable) {
        this.apiService = apiService;
        this.disposable = disposable;
        responseState = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        apiService.getPopularMovies(FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        responseState.postValue(ResponseState.loading());
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull MovieResponse movieResponse) {
                        responseState.setValue(ResponseState.success());
                        callback.onResult(movieResponse.getMovieList(), null, FIRST_PAGE + 1);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        responseState.setValue(ResponseState.error(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        apiService.getPopularMovies(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        responseState.postValue(ResponseState.loading());
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull MovieResponse movieResponse) {
                        responseState.setValue(ResponseState.success());
                        callback.onResult(movieResponse.getMovieList(), params.key - 1);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        responseState.setValue(ResponseState.error(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        apiService.getPopularMovies(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        responseState.postValue(ResponseState.loading());
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull MovieResponse movieResponse) {
                        responseState.setValue(ResponseState.success());
                        callback.onResult(movieResponse.getMovieList(), params.key + 1);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        responseState.setValue(ResponseState.error(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<ResponseState> getResponseState() {
        return responseState;
    }
}
