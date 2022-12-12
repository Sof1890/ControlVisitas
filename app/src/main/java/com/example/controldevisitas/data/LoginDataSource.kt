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
        /*
        try {
            val config = RealmConfiguration.Builder(schema = setOf(User::class)).build()
            val realm = Realm.open(config)
            val user:RealmResults<User> = realm.query<User>("name == $0",username).find()
            val loguser = LoggedInUser(user[0]._id.toString(), user[0].name)
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            realm.close()
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
         */
        /*
        var loguser:LoggedInUser? = null
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                val creds = Credentials.emailPassword(username, password)
                realmApp.login(creds)
            }.onSuccess {
                withContext(Dispatchers.Main) {
                    Log.v(TAG(), "user logged in")
                    loguser = LoggedInUser(java.util.UUID.randomUUID().toString(), username, "")
                }
            }.onFailure { ex: Throwable ->
                loguser = LoggedInUser("","",ex.message.toString())
                when(ex) {
                    is InvalidCredentialsException -> {
                        Log.v(TAG(), "User failed to log in with: ${ex.message}")
                        withContext(Dispatchers.Main) {
                            displayErrorMessage("Invalid username or password. Check your credentials and try again.")
                        }
                    }
                    is ConnectionException -> {
                        Log.v(TAG(), "User failed to log in with: ${ex.message}")
                        withContext(Dispatchers.Main) {
                            displayErrorMessage("Could not connect to the authentication provider. Check your internet connection and try again.")
                        }
                    }
                    else -> {
                        Log.e(TAG(), ex.toString())
                        withContext(Dispatchers.Main) {
                            displayErrorMessage("Error: $ex")
                        }
                    }
                }
            }
        }
        if (loguser != null)
            return Result.Success<LoggedInUser>(loguser!!)
         */

        /*
        return withContext(Dispatchers.IO) {
            try {
                val regResult = FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password).await()
                val userId = regResult.user?.uid!!
                val newUser = LoggedInUser(userId, username, "")
                Result.Success(newUser)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
         */


/*

        Log.v(TAG(), "Logging in. ${username},${password}")
        var loggedUser = LoggedInUser("","","Empty User")

        FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.v(TAG(), "user logged in")
                    loggedUser = LoggedInUser(FirebaseAuth.getInstance().currentUser!!.uid.toString(), username,"")
                } else {
                    Log.w(TAG(), "signIn failed. ${task.exception}")
                    loggedUser = LoggedInUser("","",task.exception.toString())
                }
            }

        if (loggedUser.exMessage == "") {
            Log.v(TAG(), "Returning user. ${loggedUser.userId}:${loggedUser.displayName}")
            return Result.Success<LoggedInUser>(loggedUser)
        }

        Log.w(TAG(), "Returning error. ${loggedUser.exMessage}")


 */
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

        //return Result.Error(IOException("Error logging in"))
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