package com.glaucus.kaggleme.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glaucus.kaggleme.R
import com.glaucus.kaggleme.adapter.StoryAdapter
import com.glaucus.kaggleme.extension.bracketExtract
import com.glaucus.kaggleme.extension.getScripts
import com.glaucus.kaggleme.extension.getStoryJson
import com.glaucus.kaggleme.extension.toStoryList
import com.glaucus.kaggleme.network.RetrofitWrapper
import com.glaucus.kaggleme.service.TimelineService
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        RetrofitWrapper.getService(TimelineService::class.java).getTimeline().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                val result = response?.body()?.string() ?: ""
                val storyList = result.getScripts(10).bracketExtract().getStoryJson().toStoryList()
                val adapter = StoryAdapter(context, storyList, { story -> println(story.avatar) })
                story_rv.layoutManager = LinearLayoutManager(context)
                story_rv.adapter = adapter
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }
}
