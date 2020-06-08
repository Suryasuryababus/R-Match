package com.solodeveloper.r_match

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mAuth = FirebaseAuth.getInstance()
        var user = mAuth.currentUser
        var intent:Intent? = null
        if(user!=null){
            intent =  Intent(this, HomeActivity::class.java)
        }else{
            intent =  Intent(this, LoginActivity::class.java)
        }
        startActivity(intent)

    }
}
