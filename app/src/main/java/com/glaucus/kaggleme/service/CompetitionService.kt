package com.glaucus.kaggleme.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by glaucus on 2017/12/13.
 */
interface CompetitionService {
    @GET("/competitions")
    fun getCompetitions(): Call<ResponseBody>
}