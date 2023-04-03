package com.educationalapp.network

import com.educationalapp.network.model.repository.Video
import com.educationalapp.network.model.repository.VideoResponse
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseAPI(private val database: FirebaseDatabase) {

    suspend fun getUser(id: String): DataSnapshot {
        return withContext(Dispatchers.IO) {
            database.reference.child("users").child(id).get().await()
        }
    }

    suspend fun getVideo(id: String): DataSnapshot {
        return withContext(Dispatchers.IO) {
            database.reference.child("videos").child(id).get().await()
        }
    }

    suspend fun getVideos(): DataSnapshot {
        return withContext(Dispatchers.IO) {
            database.reference.child("videos").get().addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result.children.mapNotNull { doc ->
                        doc.getValue(Video::class.java)
                    }
                }
            }.await()
        }
    }
}
