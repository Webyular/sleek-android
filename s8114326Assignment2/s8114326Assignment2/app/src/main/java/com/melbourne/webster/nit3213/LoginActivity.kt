package com.melbourne.webster.nit3213

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.melbourne.webster.nit3213.repo.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import retrofit2.Response

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject lateinit var repo: Repository

    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val u = etUsername.text.toString().trim()
            val p = etPassword.text.toString().trim()
            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.d(TAG, "Login attempt for username: $u")  // Log username (avoid logging password for security)
            lifecycleScope.launch {
                try {
                    Log.d(TAG, "Sending login request...")
                    val resp: Response<com.melbourne.webster.nit3213.network.LoginResponse> = repo.login(u, p)
                    Log.d(TAG, "Response received: code=${resp.code()}, isSuccessful=${resp.isSuccessful}")
                    Log.d(TAG, "Full response: ${resp.raw()}")  // Logs headers, body, etc., for debugging
                    if (resp.isSuccessful) {
                        val body = resp.body()
                        Log.d(TAG, "Response body: $body")
                        val key = resp.body()?.keypass
                        
                        Log.d(TAG, "Key from response: $key")
                        if (key != null) {
                            val i = Intent(this@LoginActivity, DashboardActivity::class.java)
                            i.putExtra("KEYPASS", key)
                            startActivity(i)
                            finish()
                        } else {
                            Log.w(TAG, "Successful response but keypass is null")
                            Toast.makeText(this@LoginActivity, "Invalid response", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.e(TAG, "Login failed with code: ${resp.code()}")
                        if (resp.errorBody() != null) {
                            Log.e(TAG, "Error body: ${resp.errorBody()!!.string()}")
                        }
                        Toast.makeText(this@LoginActivity, "Login failed: "+resp.code(), Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Exception during login: ${e.message}", e)
                    Toast.makeText(this@LoginActivity, "Error: "+e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}