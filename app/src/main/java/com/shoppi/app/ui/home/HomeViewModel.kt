package com.shoppi.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoppi.app.Banner
import com.shoppi.app.model.Title
import com.shoppi.app.repository.home.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel(){

    private val _title = MutableLiveData<Title>() // 외부에서 데이터를 가져오지 못한다는 변수명 컨벤션이  있음
    val title:LiveData<Title> = _title // LiveData로 변환됨

    private val _topBanners = MutableLiveData<List<Banner>>()
    val topBanners: LiveData<List<Banner>> = _topBanners // 할당과 동시에 LiveData로 변환됨

    // => 이 데이터들을 HomeFragment에서 사용할 수 있음!!


    init {
        loadHomeData()
    } // HomeViewModel이 생성됐을때, 초기화되면서 LoadHomeData가 호출되고, 데이터를 요청하게 됨.
    private fun loadHomeData(){
        // TODO Data Layer의 Repository가 처리해야함!
        val homeData = homeRepository.getHomeData()
        homeData?.let{ homeData ->
            _title.value = homeData.title
            _topBanners.value = homeData.topBanners
        }
    } // Data를 요청하는 함수. 데이터를 요청할 경우 모두 뷰모델에서 처리.

}