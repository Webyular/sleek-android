package com.melbourne.webster.nit3213

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import com.melbourne.webster.nit3213.repo.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    @Inject lateinit var repo: Repository
    private lateinit var rv: RecyclerView
    private lateinit var adapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        rv = findViewById(R.id.rvEntities)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = EntityAdapter { entity ->
            val i = Intent(this, DetailsActivity::class.java)
            i.putExtra("property1", entity.property1)
            i.putExtra("property2", entity.property2)
            i.putExtra("description", entity.description)
            startActivity(i)
        }
        rv.adapter = adapter

        val key = intent.getStringExtra("KEYPASS") ?: ""
        if (key.isEmpty()) {
            Toast.makeText(this, "Missing keypass", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        lifecycleScope.launch {
            try {
                val resp = repo.getDashboard(key)
                if (resp.isSuccessful) {
                    val list = resp.body()?.entities ?: emptyList()
                    adapter.submitList(list)
                } else {
                    Toast.makeText(this@DashboardActivity, "Failed: "+resp.code(), Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DashboardActivity, "Error: "+e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
