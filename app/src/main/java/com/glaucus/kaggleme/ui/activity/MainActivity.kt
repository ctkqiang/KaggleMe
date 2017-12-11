package com.glaucus.kaggleme.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.glaucus.kaggleme.R
import com.glaucus.kaggleme.adapter.SectionsPagerAdapter
import com.glaucus.kaggleme.extension.disableShiftMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        navigation.menu.getItem(position).isChecked = true
    }

    private lateinit var manager: FragmentManager
    private lateinit var adapter: SectionsPagerAdapter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        viewPager.currentItem = item.order
        return@OnNavigationItemSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        manager = supportFragmentManager
        adapter = SectionsPagerAdapter(manager)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)
        navigation.disableShiftMode()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
