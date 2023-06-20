package com.shoppi.app.repository

import com.shoppi.app.model.HomeData

interface HomeDataSource {

    fun getHomeData(): HomeData? // nullable. 데이터가 없는 경우에도 ui처리를 해줘야함
// 인터페이스가 돼야함. 데이터소스는 여러 유형이 존재할 수 있음. (네트워크 통신의 결과, DB에 저장된 data 등) 이 요청의 원본 데이터가 필요함.
// 따라서 각 데이터의 유형의 원본인 인터페이스를 구현해야함
}