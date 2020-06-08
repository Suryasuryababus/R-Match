package com.solodeveloper.daily_matches

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.solodeveloper.r_match.R


class DailyMatchAdopter(dailyMatches:ArrayList<DailyMatch>, context: Context) : RecyclerView.Adapter<DailyMatchAdopter.SliderViewHolder>() {

    private val dailyMatches = dailyMatches
    private val context = context
    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image:ImageView =itemView.findViewById(R.id.image)
        private val shimmer:ShimmerFrameLayout = itemView.findViewById((R.id.shimmer))
        private val channelName:TextView = itemView.findViewById(R.id.channel)
        fun setSlideItem(dailyMatch: DailyMatch, context: Context){
            shimmer.startShimmerAnimation()
            channelName.text = dailyMatch.channel
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        Log.d("Recycler","onCreateViewHolder")

        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.daily_match_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dailyMatches.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        Log.d("recycler","came")
        return holder.setSlideItem(dailyMatches[position],context)
    }
}