package com.melbourne.webster.nit3213

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val p1 = intent.getStringExtra("property1") ?: ""
        val p2 = intent.getStringExtra("property2") ?: ""
        val desc = intent.getStringExtra("description") ?: ""

        findViewById<TextView>(R.id.tvProp1).text = "Property1: $p1"
        findViewById<TextView>(R.id.tvProp2).text = "Property2: $p2"
        findViewById<TextView>(R.id.tvDesc).text = desc
    }
}
