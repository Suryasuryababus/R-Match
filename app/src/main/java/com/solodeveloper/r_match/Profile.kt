package com.solodeveloper.r_match

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.solodeveloper.handler.DBHandler

class Profile : AppCompatActivity() {
    var squardName:TextView? =null
    var commander:TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        squardName = findViewById(R.id.sqard_name)
        commander = findViewById(R.id.commander_name)
        var userinfo = DBHandler.getUserFromUserId(FirebaseAuth.getInstance().currentUser!!.uid)
        userinfo.addOnSuccessListener {
            squardName!!.text = it["squard_name"].toString()
            commander!!.text = it["game_name"].toString()
        }
    }

    fun signOutClicked(view: View) {
        FirebaseAuth.getInstance().signOut();
        var intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}
