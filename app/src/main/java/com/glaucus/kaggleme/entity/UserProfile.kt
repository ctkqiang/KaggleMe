package com.glaucus.kaggleme.entity

import com.glaucus.kaggleme.annotation.Bean

/**
 * Created by glaucus on 2017/12/10.
 */
@Bean
data class UserProfile(val discussion_tier_attained_at: Int,
                       val user_name: String,
                       val newsletter_subscriber: Boolean,
                       val created_at: Int,
                       val discussion_tier: Int,
                       val points: Int,
                       val highest_ranking: Int,
                       val is_admin: Boolean,
                       val performance_tier: Int,
                       val tier: Int,
                       val competitions_tier: Int,
                       val kernels_tier: Int,
                       val kernels_tier_attained_at: Int,
                       val last_visit_date_at: Int,
                       val competitions_tier_attained_at: Int,
                       val app_id: String,
                       val email: String,
                       val experiment_group: Int,
                       val delete_account_reason: String,
                       val is_locked_out: Boolean,
                       val block_emails: Boolean,
                       val display_name: String,
                       val datasets_count: Int,
                       val is_activated: Boolean,
                       val user_hash: String,
                       val name: String,
                       val ranking: Int,
                       val host_page_visits: Int)