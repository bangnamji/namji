package com.example.fragment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.fragment1.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityFragmentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentOne: FragmentOne = FragmentOne()

        val bundle:Bundle=Bundle()
        bundle.putString("hello","hello")
        fragmentOne.arguments=bundle

        binding.out.setOnClickListener {
            //프라그먼트를 동적으로 작동하는 방법
            val fragmentManager: FragmentManager = supportFragmentManager

            //Transaction
            //작업의 단위: 시작과 끝이 있다.
            val fragmentTransaction = fragmentManager.beginTransaction() //시작!
            fragmentTransaction.replace(R.id.container, fragmentOne)//container부분과 fragmentOne을 바꾼다.
            fragmentTransaction.commit() //시간 될때 끝!
            //fragmentTransaction.commitNow()//지금 바로해, 하지만 요즘은 별로 차이가 없다.

        }

        binding.vanish.setOnClickListener {
            val fragmentManager:FragmentManager=supportFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()
            fragmentTransaction.detach(fragmentOne)
            fragmentTransaction.commit()
        }
    }
}