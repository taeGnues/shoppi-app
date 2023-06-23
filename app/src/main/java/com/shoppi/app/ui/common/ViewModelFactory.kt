package com.shoppi.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoppi.app.AssetLoader
import com.shoppi.app.network.ApiClient
import com.shoppi.app.repository.category.CategoryRemoteDataSource
import com.shoppi.app.repository.category.CategoryRepository
import com.shoppi.app.repository.home.HomeAssetDataSource
import com.shoppi.app.repository.home.HomeRepository
import com.shoppi.app.ui.category.CategoryViewModel
import com.shoppi.app.ui.home.HomeViewModel

class ViewModelFactory(private val context:Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)){ // HomeViewModel Type인지 확인

            val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
            // HomeRepository가 있어야 homeviewmodel 생성가능.
            // 의존 관계 확인.
            // 의존 관계 -> Dependency Injection
            HomeViewModel(repository) as T
            // HomeViewModel인지 맞다면 HomeViewModel 객체 생성
        } else if(modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            val repository = CategoryRepository(CategoryRemoteDataSource(ApiClient.create())) // ApiClient는 create()로 생성함
            CategoryViewModel(repository) as T // Category ViewModel를 생성함!!!
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}