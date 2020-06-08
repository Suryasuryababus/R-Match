package com.solodeveloper.r_match

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.solodeveloper.handler.DBHandler


class SignupActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var gameName : EditText? = null
    private var emailId : EditText? = null
    private var password : EditText? = null
    private var confirmPassword : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mAuth = FirebaseAuth.getInstance()
        gameName = findViewById(R.id.game_name)
        emailId = findViewById(R.id.emailid)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirm_password)

    }
    fun CreateNewUser( email:String ,password :String,gameName:String){
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = mAuth!!.currentUser
                user!!.sendEmailVerification()
                DBHandler.addUsername(user.uid,gameName).addOnSuccessListener{
                    Toast.makeText(this,"Created",Toast.LENGTH_LONG).show()
                    var intent =  Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener{
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                }

            }.addOnFailureListener{
                Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
            }
    }
    fun signUpClicked(view: View) {
        val tGameName = gameName!!.text
        val tEmailId = emailId!!.text
        val tPassword = password!!.text
        val tConfirmPassword = confirmPassword!!.text
        var info = ""

        if(tGameName.isEmpty()){
            info = "Game name cannot be empty"
        }else if(tEmailId.isEmpty()){
            info = "EmailId cannot be empty"
        }else if(tPassword.isEmpty()){
            info = "password cannot be empty"
        }else if(tConfirmPassword.isEmpty()){
            info = "Confirm password cannot be empty"
        }else if(!tPassword.toString().equals(tConfirmPassword.toString())){
            info = "password doesn't match "+tPassword+":"+tConfirmPassword
        }
        if(!info.equals("")){
            Toast.makeText(this,info,Toast.LENGTH_LONG).show()
            return
        }
        CreateNewUser(tEmailId.toString(),tPassword.toString(),tGameName.toString())
    }
}
