package com.glaucus.kaggleme.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by glaucus on 2017/12/9.
 */
interface LoginService {
    @FormUrlEncoded
    @POST("/account/login")
//    @Headers("Host:www.kaggle.com", "Origin:https://www.kaggle.com", "Referer:https://www.kaggle.com/")
    fun login(@Field("username")username: String, @Field("password")password: String): Call<ResponseBody>
}