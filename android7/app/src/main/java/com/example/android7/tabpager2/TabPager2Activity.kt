package com.example.android7.tabpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.android7.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_pager.*

class TabPager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager2)

        tab_layout.addTab(tab_layout.newTab().setText("ONE"))
        tab_layout.addTab(tab_layout.newTab().setText("TWO"))
        tab_layout.addTab(tab_layout.newTab().setText("THREE"))

        val adapter=ThreePageAdapter(LayoutInflater.from(this@TabPager2Activity))
        view_pager.adapter=adapter
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))


        tab_layout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.currentItem=tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

    }
}

class ThreePageAdapter(
    val layoutInflater: LayoutInflater
): PagerAdapter(){
    override fun getCount(): Int {
        return 3
        //세 장을 만들거니까
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }
    //이건 코틀린 문법입니다. ===를 사용하게 되면 주소값을 비교하게 됩니다.

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        //Any로 되어있던 object를 view로 캐스트 해줘야 한다.


    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when(position){
            0->{
                val view =layoutInflater.inflate(R.layout.fragment1,container,false)
                container.addView(view)
                return view
            }
            1->{
                val view =layoutInflater.inflate(R.layout.fragment2,container,false)
                container.addView(view)
                return view
            }
            2->{
                val view =layoutInflater.inflate(R.layout.fragment3,container,false)
                container.addView(view)
                return view
            }
            else->{
                val view =layoutInflater.inflate(R.layout.fragment1,container,false)
                container.addView(view)
                return view
            }
        }
    }
}
