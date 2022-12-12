package com.example.controldevisitas.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.controldevisitas.R
import com.example.controldevisitas.TAG
import com.example.controldevisitas.data.model.User
import com.example.controldevisitas.databinding.ActivityCreateUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class CreateUser : AppCompatActivity() {
    private lateinit var binding: ActivityCreateUserBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        auth = Firebase.auth
        db = FirebaseDatabase.getInstance().getReference("Users")

        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val create = binding.createButton

        //TODO: Add observer to enable button if data is valid.
        create.isEnabled = true
    }

    override fun onStart() {
        super.onStart()
    }

    fun myOnClick(view: View) {
        val username = binding.username.text.toString()
        val name = binding.name.text.toString()
        val password = binding.password.text.toString()
        val phone = binding.phone.text.toString()
        val email = binding.email.text.toString()
        val newUser = User(username, name, phone, email)
        createUserClick(email, password, newUser)
    }

    private fun createUserClick(email: String, password: String, newUser: User) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG(), "createUser:success")
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        //createNewUser(user)
                        newUser.id = currentUser.uid
                        db.child(currentUser.uid).setValue(newUser).addOnSuccessListener {
                            binding.username.text.clear()
                            binding.password.text.clear()
                            binding.email.text.clear()
                            binding.name.text.clear()
                            binding.phone.text.clear()
                            Log.d(TAG(), "createUser:data ${newUser.username},${email}")
                            Toast.makeText(
                                baseContext, this.getString(R.string.action_create_success),
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        }.addOnFailureListener {
                            Log.w(TAG(), "createUser:failure", task.exception)
                            Toast.makeText(baseContext, this.getString(R.string.action_register_failed),
                            Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Log.w(TAG(), "createUser:failure", task.exception)
                    Toast.makeText(baseContext, this.getString(R.string.action_create_failed),
                        Toast.LENGTH_LONG).show()
                }
            }

    }
}