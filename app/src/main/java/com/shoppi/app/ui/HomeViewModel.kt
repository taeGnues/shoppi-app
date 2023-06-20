package com.shoppi.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoppi.app.Banner
import com.shoppi.app.Title

class HomeViewModel : ViewModel(){

    private val _title = MutableLiveData<Title>() // 외부에서 데이터를 가져오지 못한다는 변수명 컨벤션이  있음
    val title:LiveData<Title> = _title // LiveData로 변환됨

    private val _topBanners = MutableLiveData<List<Banner>>()
    val topBanners: LiveData<List<Banner>> = _topBanners // 할당과 동시에 LiveData로 변환됨

    // => 이 데이터들을 HomeFragment에서 사용할 수 있음!!



    fun loadHomeData(){
        // TODO Data Layer의 Repository가 처리해야함!
    } // Data를 요청하는 함수

}