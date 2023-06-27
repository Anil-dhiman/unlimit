package com.unlimit_test.presentation.ui.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.unlimit_test.domain.usecase.JokesUseCase

class JokesViewModel(private val JokesUseCase: JokesUseCase) : ViewModel() {

    fun getJokes() = liveData {
        val movieList = JokesUseCase.execute()
        emit(movieList)
    }

}