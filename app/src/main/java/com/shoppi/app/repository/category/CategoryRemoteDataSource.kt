package com.shoppi.app.repository.category

import com.shoppi.app.model.Category
import com.shoppi.app.network.ApiClient

// apiClient가 데이터를 제공해주므로 생성자에 넣었음.
class CategoryRemoteDataSource(private val apiClient: ApiClient) : CategoryDataSource {
    override suspend fun getCategories(): List<Category> {
        return apiClient.getCategories()
    }

}