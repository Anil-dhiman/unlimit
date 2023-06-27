package com.unlimit_test.presentation.base.adapter

import android.view.View
import androidx.databinding.ViewDataBinding

interface RecyclerCallback<VM : ViewDataBinding?, T> {
    fun bindData(binder: VM, model: T, position: Int, itemView: View?)
}