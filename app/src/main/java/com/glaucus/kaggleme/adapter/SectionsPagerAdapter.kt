package com.glaucus.kaggleme.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.glaucus.kaggleme.ui.fragment.CompetitionFragment
import com.glaucus.kaggleme.ui.fragment.DiscussionFragment
import com.glaucus.kaggleme.ui.fragment.HomeFragment
import com.glaucus.kaggleme.ui.fragment.MyFragment

/**
 * Created by glaucus on 2017/12/11.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> HomeFragment()
            1 -> CompetitionFragment()
            2 -> DiscussionFragment()
            3 -> MyFragment()
            else -> {
                null
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }
}