package com.glaucus.kaggleme.entity

/**
 * Created by glaucus on 2017/12/13.
 */
data class Competition(val id: Int, val title: String, val desc: String, val url: String, val imageUrl: String, val deadLine: String, val totalTeams: Int, val totalKernels: Int, val rewardQuantity: Int, val rewardTypeName: String, val organizationName: String, val categories: List<String>)