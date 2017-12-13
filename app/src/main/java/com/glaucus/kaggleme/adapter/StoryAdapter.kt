package com.glaucus.kaggleme.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.glaucus.kaggleme.R
import com.glaucus.kaggleme.entity.Story
import kotlinx.android.synthetic.main.story_item.view.*

/**
 * Created by glaucus on 2017/12/12.
 */
class StoryAdapter(private val context: Context, private val items: List<Story>, private val itemClickListener: (Story) -> Unit) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.story_item, parent, false)
        return ViewHolder(context, view, itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(private val context: Context, val view: View, private val itemClickListener: (Story) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(story: Story) {
            Glide.with(context)
                    .load(story.avatar)
                    .into(view.story_avatar_iv)
            view.story_date_tv.text = story.date
            view.story_name_tv.text = story.name
            view.story_vote_tv.text = story.vote.toString()
            view.story_comment_tv.text = story.comment.toString()
            view.story_competition_tv.text = story.competition
            view.setOnClickListener {
                itemClickListener(story)
            }
        }
    }
}