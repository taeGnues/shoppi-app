package com.shoppi.app.ui.home

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.*
import com.shoppi.app.databinding.FragmentHomeBinding
import com.shoppi.app.ui.common.ViewModelFactory

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) } // Fragment나 Activity에서 ViewModel를 사용하기 위해 받아와야함.
    private lateinit var binding: FragmentHomeBinding // 자동으로 만들어진 바인딩 객체
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root // findViewById로 참조를 찾을 필요 없음. binding으로 접근!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar() // 우클릭 드래그 -> refactor -> function
        setTopBanners() // this로 모두 참조를 얻고 있음!

        // HomeFragment에서 view에 직접 데이터를 삽입하는 구문이 모두 없어짐!!!

        // View Model이 StateHolder 역할을 하면서 데이터를 저장하고 관리해야함.

       // 현재의 라이프사이클의 상태를 알고 있는 객체,

        // fragment에서 데이터를 직접 요청하는 것이 아니라,
        // viewModel에서 title을 참조하고, LiveData의 observer 메소드를 통해서 데이터 변경시 어떠한 처리를 할 것인지 Home Fragment에서 관리


        // 뷰의 lifeCycleOwner를 직접 binding의 lifecycleowenr에 할당해야함.
        // databinding을 통해 view에 직접 할당했던 코드들은 다 삭제할 수 있음!


//        val assetLoader = AssetLoader()
//        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")
//
//
//        if (!homeJsonString.isNullOrEmpty()) { // homeData가 null일 수 있음.
//            val gson = Gson()
//            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)
// Asset에서 가져온 Json 파일에서 string을 가져옴.
// 이 부분은 Data Layer로 옮겨줘야함.

// Gson 객체를 선언해주고 Gson의 fromJson 메소드에 Json string과 변환할 java 클래스를 인수로 넘김
// fragment는 라이프 사이클 맞추어 레이아웃을 인플레이트하고, 해당하는 뷰를 보이는 책임만 가질 수 있게 함.
// 따라서 뷰 모델이 데이터를 요청하게 되고, 추후에 그 데이터를 받아서 뷰에 할당함.


        //-------------------
//            toolbarTitle.text = homeData.title.text
//            Glide
//                .with(this)
//                .load(homeData.title.iconUrl)
//                .into(toolbarIcon);
        // -------------------뷰에 할당하는 이 부분만 남겨야함








    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter().apply {
                viewModel.topBanners.observe(viewLifecycleOwner, {
                    submitList(it)
                })  // 인스턴스 생성과 동시에 데이터 전달
            } // adapter가 먼저 초기화돼야함. topBanners의 변경에 따라서 ui가 결정됨.
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin //Device 가로 길이 - 312dp - 16dp


            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offset // 왼쪽으로 이동(-)

            }


            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this) { tab, position ->

            }.attach() // tablayout을 보이게하려면 필요!!
        }
    }

    private fun setToolbar() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.title.observe(
            viewLifecycleOwner
        ) {
            binding.title = it
        } // -> toolbar의 데이터를 변경하는 부분.
    }
}