package com.solodeveloper.handler

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DBHandler{
    companion object{
        val db = Firebase.firestore
        val Users_Path = "users"
        fun addUsername(userId:String,gameName:String):Task<Void>{
            val user = hashMapOf(
                "user_id" to userId,
                "game_name" to gameName
            )
           return db.collection(
               Users_Path
           ).document(userId).set(user)

        }
        fun setSquardName(userId:String,name:String):Task<Void>{
            val user = hashMapOf(
                "squard_name" to name
            )
            return db.collection(
                Users_Path
            ).document(userId).set(user, SetOptions.merge())
        }
        fun getTournaments(): Task<QuerySnapshot> {
            return db.collection("tournaments").get()
        }
        fun getUserFromUserId(userId: String):Task<DocumentSnapshot>{
          return db.collection(
              Users_Path
          ).document(userId).get()
        }
    }
}