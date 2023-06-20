package com.shoppi.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoppi.app.AssetLoader
import com.shoppi.app.repository.HomeAssetDataSource
import com.shoppi.app.repository.HomeRepository
import com.shoppi.app.ui.home.HomeViewModel

class ViewModelFactory(private val context:Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){ // HomeViewModel Type인지 확인

            val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
            // HomeRepository가 있어야 homeviewmodel 생성가능.
            // 의존 관계 확인.
            // 의존 관계 -> Dependency Injection
            return HomeViewModel(repository) as T
        // HomeViewModel인지 맞다면 HomeViewModel 객체 생성
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}