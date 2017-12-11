package com.glaucus.kaggleme.network

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by glaucus on 2017/12/10.
 */
class LocalCookieJar : CookieJar {
    private var cookies: MutableList<Cookie>? = null
    override fun saveFromResponse(url: HttpUrl?, cookies: MutableList<Cookie>?) {
        this.cookies = cookies
    }

    override fun loadForRequest(url: HttpUrl?): MutableList<Cookie> {
        return cookies ?: arrayListOf()
    }
}