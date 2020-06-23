package com.phone91.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.phone91.sdk.mvvm.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnTest.setOnClickListener{
            var intent= Intent(this,DashboardActivity::class.java)
            intent.putExtra(DashboardActivity.WIDGETTOKEN,"28b43")
            startActivity(intent)
        }
    }
}
