package com.glaucus.kaggleme.extension

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.glaucus.kaggleme.entity.Competition
import com.glaucus.kaggleme.entity.Story
import com.glaucus.kaggleme.entity.UserProfile
import org.jsoup.Jsoup

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

fun String.toStoryList(): List<Story> {
    val array = JSON.parseObject(this).getJSONArray("stories").toJavaList(JSONObject::class.java)
    return array.map { item -> parseStory(item) }
}

fun String.toCompetitionList(): List<Competition> {
    val array = JSON.parseObject(this).getJSONArray("fullCompetitionGroups").getJSONObject(1).getJSONArray("competitions").toJavaList(JSONObject::class.java)
    return array.map { item -> parseCompetition(item) }
}

fun parseCompetition(item: JSONObject): Competition {
    val id = item.getInteger("competitionId")
    val title = item.getString("competitionTitle")
    val desc = item.getString("competitionDescription")
    val url = item.getString("competitionUrl")
    val imageUrl = item.getString("thumbnailImageUrl")
    val deadLine = item.getString("deadline")
    val totalTeams = item.getInteger("totalTeams")
    val totalKernels = item.getInteger("totalKernels")
    val rewardQuantity = item.getInteger("rewardQuantity") ?: 0
    val rewardTypeName = item.getString("rewardTypeName") ?: "N/A"
    val organizationName = item.getString("organizationName") ?: "N/A"
    val categories = item.getJSONObject("categories").getJSONArray("categories").toJavaList(JSONObject::class.java).map { i -> i.getString("name") }

    return Competition(id, title, desc, url, imageUrl, deadLine, totalTeams, totalKernels, rewardQuantity, rewardTypeName, organizationName, categories)

}

fun parseStory(item: JSONObject): Story {
    val id = item.getInteger("storyItemId")
    val date = item.getString("date")
    val name = item.getJSONObject("user").getString("userDisplayName")
    val avatar = item.getJSONObject("user").getString("userAvatarUrl")
    val vote = item.getJSONObject("vote").getInteger("voteCount")
    val comment = if (item.getJSONObject("comments") == null) 0 else item.getJSONObject("comments").getInteger("commentCount")
    val competition = if (item.getJSONObject("competition") == null) "" else item.getJSONObject("competition").getString("competitionName")

    return Story(id, date, avatar, name, vote, comment, competition)
}


fun String.getScripts(index: Int): String {
    val document = Jsoup.parse(this)
    return document.getElementsByTag("script")[index].data()
}

fun String.bracketMatch(): List<Pair<Int, Int>> {
    val originIndex = arrayListOf<Int>()
    val match = arrayListOf<Pair<Int, Int>>()

    for (index in this.indices) {
        val char = this[index]
        if (char == '(') originIndex.add(index)
        if (char == ')') {
            if (originIndex.isEmpty()) continue
            val curIndex = originIndex.last()
            match.add(curIndex to index)
            originIndex.remove(curIndex)
        }
    }
    return match
}

fun String.bracketExtract(): List<String> {
    val matchList = this.bracketMatch()
    return matchList.map { item -> this.substring(item.first + 1, item.second) }
}

fun List<String>.getStoryJson(): String {
    return this.find { item -> item.startsWith("{\"stories\"") }!!
}

fun List<String>.getCompetitionJson(): String {
    return this.find { item -> item.startsWith("{\"sortByOptions\"") }!!
}




