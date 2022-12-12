package com.example.controldevisitas.data

import android.util.Log
import com.example.controldevisitas.TAG
import com.example.controldevisitas.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private var result:Result<LoggedInUser>? = null

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        Log.d(TAG(), "Trying to login. $username")
        result = try {
            val regResult = FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password).await()
            val userId = regResult.user?.uid!!
            val newUser = LoggedInUser(userId, username)
            Result.Success(newUser)
        } catch (e: Exception) {
            if (FirebaseAuth.getInstance().currentUser != null) {
                logout()
            }
            Log.e(TAG(),"${e.message}")
            Result.Error(e)
        }

        val returnResult = result
        result = null

        return returnResult!!
    }

    fun logout() {
        try {
            FirebaseAuth.getInstance().signOut()
        } catch (e: Exception) {
            Log.e(TAG(), "Could not logout. ${e.message}")
        }
    }
}