package com.solodeveloper.r_match

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var temail:EditText? = null
    private var tpassword:EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
        mAuth = FirebaseAuth.getInstance()
        temail = findViewById(R.id.emailid)
        tpassword = findViewById(R.id.password)
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
    }




    fun newUserButtonClicked(view: View) {
        var intent =  Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    fun login_clicked(view: View) {
        val email = temail!!.text.toString()
        val password = tpassword!!.text.toString()
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                var intent =  Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
            }
    }

}
