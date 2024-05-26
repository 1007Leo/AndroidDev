package com.mmcs.memeapiclient.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mmcs.memeapiclient.viewmodel.MemeApiClientViewModel

class ViewModelFactory(private val dao: IImageDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemeApiClientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MemeApiClientViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}