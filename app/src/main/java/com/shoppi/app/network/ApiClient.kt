package com.shoppi.app.network

import com.shoppi.app.model.Category
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClient {

    @GET("categories.json") // json 파일로 받음.
    suspend fun getCategories(): List<Category>

    companion object{

        private const val baseUrl = "https://shoppi-b9fa0-default-rtdb.asia-southeast1.firebasedatabase.app/"

        fun create(): ApiClient{
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC // 로그 레벨을 설정. okhttp는 네트워크의 결과를 로그로 출력해주는 역할.
            }

            val client = OkHttpClient.Builder().addInterceptor(logger).build()

            return Retrofit.Builder()// HTTP 통신을 하는 객체를 구성하는 방법을 Builder 클래스로 제공함
                .baseUrl(baseUrl) // url
                .addConverterFactory(GsonConverterFactory.create()) // HTTP 응답의 결과를 프로젝트에서 사용하는 객체로 변환하는 방법을 정의함.
                .build()
                .create(ApiClient::class.java)
        }
    }
}