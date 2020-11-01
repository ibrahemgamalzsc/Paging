package com.example.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.paging.data.MovieDataSource;
import com.example.paging.data.MovieDataSourceFactory;
import com.example.paging.data.ResponseState;
import com.example.paging.pojo.Movie;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MainVM extends ViewModel {
    private CompositeDisposable disposable;
    private LiveData<PagedList<Movie>> pagedListLiveData;
    private LiveData<ResponseState> status;
    private final MovieDataSourceFactory movieDataSourceFactory;

    @Inject
    public MainVM(MovieDataSourceFactory movieDataSourceFactory) {
        this.movieDataSourceFactory = movieDataSourceFactory;
        disposable = new CompositeDisposable();
        init();
    }


    private void init() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .setInitialLoadSizeHint(40)
                .build();

        pagedListLiveData = new LivePagedListBuilder<Integer, Movie>(
                movieDataSourceFactory, config
        ).build();

        status = Transformations.switchMap(
                movieDataSourceFactory.getMovieDataSourceMutableLiveData(),
                MovieDataSource::getResponseState);
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public LiveData<ResponseState> getStatus() {
        return status;
    }

    public void retry(){
        if (pagedListLiveData.getValue() != null) {
            pagedListLiveData.getValue().getDataSource().invalidate();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
