package com.example.controldevisitas.data

import android.util.Log
import android.widget.Toast
import com.example.controldevisitas.TAG
import com.example.controldevisitas.data.model.LoggedInUser
import com.example.controldevisitas.data.model.User
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
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
        return Result.Error(IOException("Error logging in"))
    }

    private fun displayErrorMessage(errorMsg: String) {
        Log.e(TAG(), errorMsg)
        //Toast.makeText(, errorMsg, Toast.LENGTH_LONG).show()
    }

    fun logout() {
        // TODO: revoke authentication
    }
}