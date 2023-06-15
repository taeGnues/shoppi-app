package com.shoppi.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.json.JSONObject

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val assetLoader = AssetLoader()
        val homeData = assetLoader.getJsonString(requireContext(), "home.json")

        if (!homeData.isNullOrEmpty()) { // homeData가 null일 수 있음.
            val jsonObject = JSONObject(homeData)
            // json 형식의 string을 jsonObject로 변환
            val title = jsonObject.getJSONObject("title")
            //getJSONObject로 "title"에 해당하는 객체를 가져올 수 있음.
            val text = title.getString("text") // title의 text에 할당된 값
            val iconUrl = title.getString("icon_url")
            val titleValue = Title(text, iconUrl)


            val tv_home_appbar_title = view.findViewById<TextView>(R.id.tv_toolbar_home_title)
            val iv_home_appbar_icon = view.findViewById<ImageView>(R.id.iv_toolbar_home_icon)

            tv_home_appbar_title.text = titleValue.text
            Glide
                .with(this)
                .load(titleValue.iconUrl)
                .into(iv_home_appbar_icon);


        }
    }
}