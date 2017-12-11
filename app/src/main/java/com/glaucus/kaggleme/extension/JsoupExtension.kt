package com.glaucus.kaggleme.extension

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.glaucus.kaggleme.entity.NewsFeed
import com.glaucus.kaggleme.entity.UserProfile
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

/**
 * Created by glaucus on 2017/12/10.
 */
fun String.parseUserProfile(): UserProfile {
    val document = Jsoup.parse(this)
    val scripts = document.select("script")[5].toString().between('{', '}')
    return scripts.toObject(UserProfile::class.java)
}

fun String.between(start: Char, end: Char, containSelf: Boolean = true): String {
    return if (containSelf) this.substring(this.indexOf(start), this.lastIndexOf(end) + 1) else this.substring(this.indexOf(start) + 1, this.lastIndexOf(end))
}

fun <T> String.toObject(clazz: Class<T>): T {
    return JSON.parseObject(this, clazz)
}

fun Element.toNewsFeed(): NewsFeed {
    val vote = this.select("body > div.site-layout > div.site-layout__main-content > div > div > div > div.newsfeed__story-list > div > div > div.smart-list__content > div > div:nth-child(1) > div > div.newsfeed-story-header.newsfeed-story-header--bordered > div.newsfeed-story-header__vote-button-container > div > div > div:nth-child(2) > span").text()
    val avatar = this.select("body > div.site-layout > div.site-layout__main-content > div > div > div > div.newsfeed__story-list > div > div > div.smart-list__content > div > div:nth-child(1) > div > div.newsfeed-story-header.newsfeed-story-header--bordered > div.newsfeed-story-header__avatar-container > span > a > img.avatar__thumbnail").attr("src")
    val label = this.select("body > div.site-layout > div.site-layout__main-content > div > div > div > div.newsfeed__story-list > div > div > div.smart-list__content > div > div:nth-child(1) > div > div.newsfeed-story-header.newsfeed-story-header--bordered > div.newsfeed-story-header__text-container > div.newsfeed-story-header__label").text()
    val subLabel = this.select("body > div.site-layout > div.site-layout__main-content > div > div > div > div.newsfeed__story-list > div > div > div.smart-list__content > div > div:nth-child(1) > div > div.newsfeed-story-header.newsfeed-story-header--bordered > div.newsfeed-story-header__text-container > div.newsfeed-story-header__sublabel > span").text()
    val content = this.select("body > div.site-layout > div.site-layout__main-content > div > div > div > div.newsfeed__story-list > div > div > div.smart-list__content > div > div:nth-child(1) > div > div.newsfeed__story-content > div > div").html()
    return NewsFeed(vote, avatar, label, subLabel, content)
}

fun String.toNewsFeedList(): List<NewsFeed> {
    val document = Jsoup.parse(this)
    val stories = document.getElementsByTag("script")[10].data()
    println(stories.between('(', ')', false))
    val feedList = document.select("body > div.site-layout > div.site-layout__main-content > div > div > div > div.newsfeed__story-list > div > div > div.smart-list__content > div")
    return feedList.map { item -> item.toNewsFeed() }
}


