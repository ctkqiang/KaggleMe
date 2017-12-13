package com.glaucus.kaggleme.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.glaucus.kaggleme.R
import com.glaucus.kaggleme.entity.Competition
import kotlinx.android.synthetic.main.competition_item.view.*

/**
 * Created by glaucus on 2017/12/12.
 */
class CompetitionAdapter(private val context: Context, private val items: List<Competition>, private val itemClickListener: (Competition) -> Unit) : RecyclerView.Adapter<CompetitionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.competition_item, parent, false)
        return ViewHolder(context, view, itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(private val context: Context, val view: View, private val itemClickListener: (Competition) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(competition: Competition) {
            Glide.with(context)
                    .load(competition.imageUrl)
                    .into(view.competition_image_iv)
            view.competition_title_tv.text = competition.title
            view.competition_desc_tv.text = competition.desc
            view.competition_quantity.text = competition.rewardQuantity.toString()
            view.competition_totalTeams.text = competition.totalTeams.toString()

            view.setOnClickListener {
                Log.d("click", "clicked!" + competition.id)
                itemClickListener(competition)
            }
        }
    }
}