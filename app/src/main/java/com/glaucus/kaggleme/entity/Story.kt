package com.glaucus.kaggleme.entity

import com.glaucus.kaggleme.annotation.Bean

/**
 * Created by glaucus on 2017/12/12.
 */
@Bean
data class Story(val id: Int, val date: String, val avatar: String, val name: String, val vote: Int, val comment: Int, val competition: String?)