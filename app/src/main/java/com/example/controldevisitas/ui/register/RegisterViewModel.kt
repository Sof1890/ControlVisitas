package com.example.controldevisitas.ui.register

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

class RegisterViewModel : ViewModel() {

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
                    }
                }
            })
    }

    private val _uid = MutableLiveData<String>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/uid")
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
    private val _visitor = MutableLiveData<String>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/visitor")
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
    private val _floor = MutableLiveData<Long>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/floor")
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG(), "loadUser:cancelled", error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue(snapshot.value.toString().toLong())
                    }
                }
            })
    }
    private val _requireParking = MutableLiveData<Boolean>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/requireParking")
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG(), "loadUser:cancelled", error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue(snapshot.value.toString().toBoolean())
                    }
                }
            })
    }
    private val _purpose= MutableLiveData<String>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/purpose")
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
    private val _isInBuilding = MutableLiveData<Boolean>().apply {
        val uid = FirebaseAuth.getInstance().uid
        FirebaseDatabase.getInstance()
            .getReference("Registers/$uid/inBuilding")
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG(), "loadUser:cancelled", error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue(snapshot.value.toString().toBoolean())
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
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        postValue(snapshot.value.toString().toLong())
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
                    }
                }
            })
    }

    val username: LiveData<String> = _username
    val uid:LiveData<String> = _uid
    val visitor:LiveData<String> = _visitor
    val floor:LiveData<Long> = _floor
    val requireParking:LiveData<Boolean> = _requireParking
    val purpose:LiveData<String> = _purpose
    val isInBuilding:LiveData<Boolean> = _isInBuilding
    val date:LiveData<Long> = _date
    val time:LiveData<String> = _time
}