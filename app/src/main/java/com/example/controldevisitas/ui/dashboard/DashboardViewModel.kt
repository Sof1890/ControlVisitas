package com.example.controldevisitas.ui.dashboard

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

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid")
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG(), "loadUser:cancelled", error.toException())
                    postValue("No Visitor registered")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue("")
                    } else {
                        postValue("No Visitor registered")
                    }
                }
            })
    }

    private val _username = MutableLiveData<String>().apply {
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
                    } else {
                        postValue("")
                    }
                }
            })
    }
    private val _time = MutableLiveData<String>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/time")
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG(), "loadUser:cancelled", error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue(snapshot.value.toString())
                    } else {
                        postValue("")
                    }
                }
            })
    }
    private val _date = MutableLiveData<Long>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/date")
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG(), "loadUser:cancelled", error.toException())
                    postValue(0)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue(snapshot.value.toString().toLong())
                    } else {
                        postValue(0)
                    }
                }
            })
    }
    private val _visitor = MutableLiveData<String>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/visitor")
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG(), "loadUser:cancelled", error.toException())
                    postValue("")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue(snapshot.value.toString())
                    }else {
                        postValue("")
                    }
                }
            })
    }
    private val _floor = MutableLiveData<Long>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/floor")
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG(), "loadUser:cancelled", error.toException())
                    postValue(-1)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue(snapshot.value.toString().toLong())
                    } else {
                        postValue(-1)
                    }
                }
            })
    }

    val text: LiveData<String> = _text
    val username: LiveData<String> = _username
    val time: LiveData<String> = _time
    val date: LiveData<Long> = _date
    val visitor: LiveData<String> = _visitor
    val floor: LiveData<Long> = _floor
}