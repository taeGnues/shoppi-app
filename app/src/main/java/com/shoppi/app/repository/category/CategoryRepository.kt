package com.shoppi.app.repository.category

import com.shoppi.app.model.Category

class CategoryRepository (// data 소스에게 데이터 요청
    private val remoteDataSource: CategoryRemoteDataSource // 데이터를 받음.

) {
    // suspend를 쓰면 UI 스레드에서는 절대 실행되지 않는 함수를 의미함.
    suspend fun getCategories(): List<Category>{ // getCategories는 viewModel에서 호출함!
//        withContext(Dispatchers.IO){
//            remoteDataSource.getCategories()
//        } WithContext로 실행될 스레드를 선택할 수 있다, 다만 retrofit 라이브러리가 알아서 해줌.
        return remoteDataSource.getCategories()
    }
}