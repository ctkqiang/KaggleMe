package com.glaucus.kaggleme.network

import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by glaucus on 2017/12/9.
 */
object RetrofitWrapper {
    private val client: OkHttpClient = OkHttpClient.Builder().followRedirects(false)  //禁制OkHttp的重定向操作，我们自己处理重定向
            .followSslRedirects(false)
            .cookieJar(LocalCookieJar())
            .build();
    private val retrofit: Retrofit = Retrofit.Builder().client(client).baseUrl(KAGGLE_LOGIN_URL).build()
    fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }
}