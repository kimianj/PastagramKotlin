package com.example.parsgramkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(ParseUser.getCurrentUser()!= null){
            goToMainActivity()
        }
        findViewById<Button>(R.id.btn_login).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_Username).toString()
            val password = findViewById<EditText>(R.id.et_Password).toString()
            loginUser(username, password)
        }
        findViewById<Button>(R.id.btn_singup).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_Username).toString()
            val password = findViewById<EditText>(R.id.et_Password).toString()
           signUpUser(username, password)
        }
    }
    private fun signUpUser(username:String, password: String){
        // Create the ParseUser
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                Log.i (TAG, "Successfully Signed in in")
                goToMainActivity()
            } else {
               e.printStackTrace()
                Toast.makeText(this, "Error Signing in", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i (TAG, "Successfully logged in")
                goToMainActivity()
            } else {
               e.printStackTrace()
                Toast.makeText(this, "Error Logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun goToMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    companion object{
        const val TAG = "LoginActivity"
    }
}