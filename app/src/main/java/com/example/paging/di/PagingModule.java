package com.example.paging.di;

import com.example.paging.adapter.PageListAdapter;
import com.example.paging.data.APIService;
import com.example.paging.data.MovieDataSource;
import com.example.paging.data.MovieDataSourceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public abstract class PagingModule {

    @Singleton
    @Provides
    public static MovieDataSourceFactory movieDataSourceFactory(MovieDataSource movieDataSource) {
        return new MovieDataSourceFactory(movieDataSource);
    }

    @Singleton
    @Provides
    public static MovieDataSource movieDataSource(APIService apiService, CompositeDisposable disposable) {
        return new MovieDataSource(apiService, disposable);
    }


    @Singleton
    @Provides
    public static CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Singleton
    @Provides
    public static PageListAdapter pageListAdapter(){
        return new PageListAdapter();
    }
}
