package com.shoppi.app

import android.content.Context

class AssetLoader(private val context: Context) {

    fun getJsonString(filename: String):String?{
        return kotlin.runCatching {
            loadAsset(filename)
        }.getOrNull()
    }


    private fun loadAsset(filename:String): String {
        return context.assets.open(filename).use{ inputStream ->
            val size = inputStream.available()
// inputStream에 실제 데이터가 있는지 확인.
            val bytes = ByteArray(size)
            inputStream.read(bytes)
//inputStream 객체에 byte라는 byteArray를 넘겨줌.
//bytes를 string으로 변환하면 home.json 파일을 확인할 수 있음
            String(bytes)
        }
    }
}