package com.glaucus.kaggleme.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glaucus.kaggleme.R
import com.glaucus.kaggleme.ui.activity.CompetitionDetailActivity


class MyFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onStart() {
        super.onStart()
        startActivity(Intent(activity, CompetitionDetailActivity::class.java))
    }
}
