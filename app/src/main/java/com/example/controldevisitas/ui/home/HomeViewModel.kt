package com.example.controldevisitas.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.controldevisitas.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Users/$uid/name")
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG(), "loadUser:cancelled", error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue(snapshot.value.toString())
                    }
                }
            })
    }
    val text: LiveData<String> = _text
}