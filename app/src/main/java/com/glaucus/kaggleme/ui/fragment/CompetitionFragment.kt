package com.glaucus.kaggleme.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glaucus.kaggleme.R
import com.glaucus.kaggleme.adapter.CompetitionAdapter
import com.glaucus.kaggleme.extension.bracketExtract
import com.glaucus.kaggleme.extension.getCompetitionJson
import com.glaucus.kaggleme.extension.getScripts
import com.glaucus.kaggleme.extension.toCompetitionList
import com.glaucus.kaggleme.network.RetrofitWrapper
import com.glaucus.kaggleme.service.CompetitionService
import com.glaucus.kaggleme.ui.activity.CompetitionDetailActivity
import kotlinx.android.synthetic.main.fragment_competition.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CompetitionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_competition, container, false)
    }

    override fun onStart() {
        super.onStart()
        RetrofitWrapper.getService(CompetitionService::class.java).getCompetitions().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                val result = response?.body()?.string() ?: ""
                val competitionList = result.getScripts(10).bracketExtract().getCompetitionJson().toCompetitionList()
                val adapter = CompetitionAdapter(context, competitionList, { competition -> startActivity(Intent(activity, CompetitionDetailActivity::class.java)) })
                competition_rv.layoutManager = LinearLayoutManager(context)
                competition_rv.adapter = adapter
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }
}
