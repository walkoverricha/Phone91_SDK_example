package com.phone91.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.phone91.sdk.mvvm.dashboard.DashboardActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var widgetToken= "Pass your widget token here"

//    Set below values, if you do not ask user for these detail
    /*private var name= "write name"
    private var email= "write email"
    private var phone= "write pnone number"*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnTest.setOnClickListener{
            var intent= Intent(this,DashboardActivity::class.java)
            intent.putExtra(DashboardActivity.WIDGETTOKEN,widgetToken)

    //Uncomment below code, if you want to pass login detail of user

//            intent.putExtra(DashboardActivity.NAME,name)
//            intent.putExtra(DashboardActivity.EMAIL,email)
//           intent.putExtra(DashboardActivity.PHONE,phone)


            startActivity(intent)
        }
    }
}
