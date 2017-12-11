package com.glaucus.kaggleme.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.glaucus.kaggleme.R
import com.glaucus.kaggleme.extension.toNewsFeedList
import com.glaucus.kaggleme.network.RetrofitWrapper
import com.glaucus.kaggleme.service.TimelineService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        RetrofitWrapper.getService(TimelineService::class.java).getTimeline().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                val result = response?.body()?.string() ?: ""
                result.toNewsFeedList().forEach(::println)
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

            }

        })
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
