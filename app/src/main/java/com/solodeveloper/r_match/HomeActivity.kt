package com.solodeveloper.r_match

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.firestore.QuerySnapshot
import com.solodeveloper.daily_matches.DailyMatch
import com.solodeveloper.daily_matches.DailyMatchAdopter
import com.solodeveloper.handler.DBHandler
import com.solodeveloper.tournaments.Tournament
import com.solodeveloper.tournaments.TournamentsAdopter

class HomeActivity : AppCompatActivity() {
    private var TournamentsSlider:ViewPager2? = null
    private var DailyMatchSlider:RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        TournamentsSlider = findViewById(R.id.slider)
        DailyMatchSlider = findViewById(R.id.daily_matches)
        loadDailyMatches()
        loadTournaments()


    }

    fun profile_Clicked(view: View) {
        var intent = Intent(this, Profile::class.java)
        startActivity(intent)

    }

    fun getTornaments(qsh:QuerySnapshot?):ArrayList<Tournament>{
        var tournaments = ArrayList<Tournament>()
        for (document in qsh!!) {

            var tournament = Tournament(
                document["channel"].toString(),
                document["channel_description"].toString(),
                document["image_id"].toString(),
                document["date"].toString()
            )
            Log.d("data", "${document.id} => ${document.data}")
            tournaments.add(tournament)
        }
        return  tournaments
    }

    private fun loadDailyMatches(){
        var dailyMatches = ArrayList<DailyMatch>()
        dailyMatches.add(DailyMatch("bigil","8:40 PM","This is a model Description",86,"iusdhvbiu","iuhdvsiuerhbvu"))
        dailyMatches.add(DailyMatch("bigil2","8:40 PM","This is a model Description",86,"iusdhvbiu","iuhdvsiuerhbvu"))
        DailyMatchSlider!!.adapter = DailyMatchAdopter(dailyMatches,this)
        Log.d("Recycler", "loadDailymatch")
    }
    private fun loadTournaments(){
        DBHandler.getTournaments().addOnSuccessListener { result ->
            var tournaments = getTornaments(result)
            TournamentsSlider!!.adapter =
                TournamentsAdopter(
                    tournaments,
                    this
                )

        }
            .addOnFailureListener { exception ->
                Log.d("data", "Error getting documents: ", exception)
            }
        TournamentsSlider!!.clipToPadding = false
        TournamentsSlider!!.clipChildren = false
        TournamentsSlider!!.offscreenPageLimit = 3
        TournamentsSlider!!.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        var compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer(){ view: View, fl: Float ->

            view.apply {
                val r = 1 - Math.abs(fl)
                view.alpha = 0.5f + r
                view.scaleY = 0.75f + r * 0.25f
            }

        })
        TournamentsSlider!!.setPageTransformer(compositePageTransformer)

    }

}
