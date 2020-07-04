# Phone91_SDK
Phone91 SDK for Android

In order to use it add below dependency in your app level gradle

    implementation 'com.phone91.sdk:library:0.1' 
   

Add below line in project gradle 


    maven {
         url  "https://dl.bintray.com/richa/Phone91_SDK"
      }
      

To open a widget, need to use below code

    var intent= Intent(this,DashboardActivity::class.java)
   
    intent.putExtra(DashboardActivity.WIDGETTOKEN,widgetToken)
   
    startActivity(intent) 
    
    
To set more details:
    
      intent.putExtra(DashboardActivity.NAME,"write name")
      intent.putExtra(DashboardActivity.EMAIL,"write email")
      intent.putExtra(DashboardActivity.PHONE,"write pnone number")
