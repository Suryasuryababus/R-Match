package com.solodeveloper.handler

import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class StorageHandler{
    companion object{
        val storage = Firebase.storage
        val storageRef = storage.reference

        fun getTournamentCoverpic(name:String):Task<ByteArray>{
            val pathReference = storageRef.child("tournaments/$name.jpg")
            val ONE_MEGABYTE: Long = 1024 * 1024
            return pathReference.getBytes(ONE_MEGABYTE)
        }

    }
}