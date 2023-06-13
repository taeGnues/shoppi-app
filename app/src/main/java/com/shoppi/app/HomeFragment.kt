package com.shoppi.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

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

        val button = view.findViewById<Button>(R.id.btn_enter_detail_btn)
        button.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction() // 삭제,추가를 위해 beginTransaction() 실행
            transaction.add(R.id.container_main, ProductDetailFragment()) // fragmentcontainer에 ProductdetailFragment()를 추가.
            transaction.commit()
        }
    }
}