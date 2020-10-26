# Phone91_SDK
Phone91 SDK for Android 
## Now available with Audio and Video call feature

In order to use it add below dependency in your app level gradle

    implementation 'com.phone91.sdk:library:4.2' 
   

Add below line in project gradle 


    maven {
         url  "https://dl.bintray.com/richa/Phone91_SDK"
      }
      
      maven {
         
         url "https://github.com/jitsi/jitsi-maven-repository/raw/master/releases"
      
      }
      

To open a widget, need to use below code

    var intent= Intent(this,DashboardActivity::class.java)
   
    intent.putExtra(DashboardActivity.WIDGETTOKEN,widgetToken)
   
    startActivity(intent) 
    
    
To set more details:
    
      intent.putExtra(DashboardActivity.NAME,"write name")
      intent.putExtra(DashboardActivity.EMAIL,"write email")
      intent.putExtra(DashboardActivity.PHONE,"write pnone number")
