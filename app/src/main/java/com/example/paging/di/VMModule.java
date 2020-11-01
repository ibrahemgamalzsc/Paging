package com.example.paging.di;

import com.example.paging.MainVM;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class VMModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainVM.class)
    abstract MainVM mainVM(MainVM mainVM);
}
