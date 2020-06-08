package com.solodeveloper.tournaments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.solodeveloper.r_match.R
import com.solodeveloper.handler.StorageHandler


class TournamentsAdopter(slideItems:ArrayList<Tournament>, context: Context) :
    RecyclerView.Adapter<TournamentsAdopter.SliderViewHolder>() {

    private val slideItems = slideItems
    private val context = context
    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image:ImageView =itemView.findViewById(R.id.slideimage)
        private val shimmer:ShimmerFrameLayout = itemView.findViewById((R.id.shimmer))
        private val channelName:TextView = itemView.findViewById(R.id.channel)
        fun setSlideItem(slideItem: Tournament, context: Context){
            shimmer.startShimmerAnimation()
            channelName.text = slideItem.tournamentName
            StorageHandler.getTournamentCoverpic(
                slideItem.imgaeid
            ).addOnSuccessListener {
                var bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
//                bmp = ImageHandler.blur(context,bmp)
                image.setImageBitmap(
                    Bitmap.createScaledBitmap(
                        bmp, image.width,
                        image.height, false
                    )
                )
                image.alpha =0.5f
                shimmer.stopShimmerAnimation()
            }.addOnFailureListener {
                Toast.makeText(context,it.message.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tournament_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return slideItems.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        return holder.setSlideItem(slideItems[position],context)
    }
}