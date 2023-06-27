package com.unlimit_test.presentation.di

import com.unlimit_test.presentation.ui.jokes.JokesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {


    viewModel { JokesViewModel(get()) }
}