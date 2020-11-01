package com.example.paging.di;

import android.app.Application;

import com.example.paging.BaseApplication;
import com.example.paging.MainActivity;
import com.example.paging.MainVM;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,PagingModule.class, VMModule.class})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

    void inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
