package com.shoppi.app.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.Category
import com.shoppi.app.repository.category.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
    ) // Repository에 데이터 요청. => 네트워크이므로 UI 스레도 하면 안됨
    : ViewModel() {

    private val _items = MutableLiveData<List<Category>>()
    val items: LiveData<List<Category>> = _items

    init { // ViewModel이 생성되는 시점에 함수 시작.
        loadCategory()
    }

    private fun loadCategory(){ // viewModel Scope으로 repository 메소드 호
        viewModelScope.launch {
            val categories = categoryRepository.getCategories()
            _items.value = categories
        } // Coroutine 시작.

    }
}