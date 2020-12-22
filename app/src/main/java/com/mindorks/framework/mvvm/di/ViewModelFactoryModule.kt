package com.mindorks.framework.mvvm.di

import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.ui.base.ViewModelFactory
import org.koin.dsl.module

val viewModelFactoryModule = module {

    single {
        MainRepository(get())
    }
    single {
        ViewModelFactory(get())
    }
}
