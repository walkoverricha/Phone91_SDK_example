package com.phone91.sdk.mvvm.dashboard

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.phone91.sdk.R
import com.phone91.sdk.data.AppDataManager
import com.phone91.sdk.model.PubNubObject
import com.phone91.sdk.model.WidgetObject
import com.phone91.sdk.mvvm.base.BaseActivity
import com.phone91.sdk.mvvm.dashboard.home.HomeFragment
import com.phone91.sdk.mvvm.dashboard.teams.TeamFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import java.util.*
import javax.inject.Inject


class DashboardActivity : BaseActivity<DashboardActivityVM>(), HasSupportFragmentInjector
  /*  ,NavigationView.OnNavigationItemSelectedListener*/ {


    companion object {
         var WIDGETTOKEN="WidgetToken"
        fun startInstanceWithBackStackCleared(context: Context?) {
            val intent = Intent(context, DashboardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(intent)
        }
    }

    @Inject
    lateinit var dashboardActivityVM: DashboardActivityVM

    var barcode = ""
    /*@Inject
    lateinit var navigationController: NavigationController*/

    @Inject
    lateinit var appDataManager: AppDataManager

//    @Inject
//    lateinit var appDataManager: AppDataManager

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private var k = 0
    private var widgetObject:WidgetObject?=null
//    lateinit var activity: DashboardActivity
//    lateinit var tvUser: TextView

    val RETURN_BACK: Int = 1;
    private lateinit var progressDialog: Dialog

    override fun getViewModel(): DashboardActivityVM = dashboardActivityVM


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }


    //    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_dashboard)
        var widgetToken=intent.getStringExtra(WIDGETTOKEN);
//        dashboardActivity = this
//        activity = this
        if(widgetToken==null || widgetToken.trim().equals("")) {
            showShortToast("Please pass WidgetToken")
            return
        }
        initializeProgressLoader()
        observeError()
        observeLoading()
        observeSuccess()

        appDataManager.setWidgetToken(widgetToken)

        dashboardActivityVM.getClientDetail()

//        navigationController.openHomeFragment(this, R.id.fl_dashboard_container, HomeFragment.newInstance())
    }

     public fun getChannelDetail(team_id: String?){
//         var channelObject= appDataManager.getChannelByTeamID(team_id!!)

//         var channelObject = ChannelObject(-1,"csdjfhg","csdjfhg","csdjfhg",team_id,"csdjfhg","csdjfhg","csdjfhg","csdjfhg"/*,false,null*/)
//         dashboardActivityVM.insertOnDB(channelObject)
         dashboardActivityVM.getChannelDetailFromDB(team_id)
     }

    public fun getPubnubDetail(teamId: String?) {
        dashboardActivityVM.getPubnubDetail(teamId)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }





    private fun observeError() {
        dashboardActivityVM.error.observeForever {
            showErrorToast(it!!)
        }
    }


    private fun observeLoading() {

        dashboardActivityVM.loading.observeForever {
            showProgressLoader(it!!)
        }
    }

    private fun showProgressLoader(visible: Boolean) {
        if (visible) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }

    private fun observeSuccess() {
        dashboardActivityVM.widgetInfo.observeForever {
            if (it!=null) {
                 widgetObject = Gson().fromJson(it, WidgetObject::class.java)
                if(widgetObject?.teams!=null && widgetObject?.teams?.size!! >0){
                    navigationController.openTeamFragment(this, R.id.fl_dashboard_container, TeamFragment.newInstance(widgetObject!!))
                }else{
                    getChannelDetail(null)
                }
//                    navigationController.openHomeFragment(this, R.id.fl_dashboard_container, HomeFragment.newInstance(widgetObject))
            }

        }


        dashboardActivityVM.channelInfo.observeForever {channelObject->
            if (channelObject!=null) {
//                var channelObject = Gson().fromJson(it, ChannelObject::class.java)
                if(channelObject.channel!=null && channelObject.channel?.trim()?.length!! >0){
                    if(channelObject.team_id==null)
                        channelObject.team_id="default_team"
                    dashboardActivityVM.insertOnDB(channelObject)
                    appDataManager.setChannel(channelObject.channel!!);
                    appDataManager.setUUID(channelObject.uuid!!);
                    getPubnubDetail(channelObject.team_id)
//                    navigationController.openTeamFragment(this, R.id.fl_dashboard_container, TeamFragment.newInstance(widgetObject))
                }else{
                    showErrorToast(getString(R.string.error_message_invalid))
                }/*else{
                    getChannelDetail(null)
                }
                    navigationController.openHomeFragment(this, R.id.fl_dashboard_container, HomeFragment.newInstance(widgetObject))*/
            }

        }

        dashboardActivityVM.pubnubInfo.observeForever {
            if (it!=null) {
                //                    {"pubkey":"pub-c-7efeae95-f505-4c40-99c4-04015a415abe",
//                        "subkey":"sub-c-41ea6378-7d3f-11e9-945c-2ea711aa6b65","authkey":"a6cfa71a1c774a5ab08e168fe17a0127"}
                var pubNubObject = Gson().fromJson(it.get(0), PubNubObject::class.java)
                if(pubNubObject.authkey!=null){
                    appDataManager.setPubkey(pubNubObject.pubkey!!);
                    appDataManager.setSubkey(pubNubObject.subkey!!);
                    appDataManager.setAuth(pubNubObject.authkey!!);
                    var homeFragment= HomeFragment.newInstance(widgetObject!!)
//                    var bundle= Bundle()
//                    bundle.putParcelable("pubnub",pubNubObject)
//                    homeFragment.arguments=bundle
                    if(it.get(1)!=null)
                    navigationController.openHomeFragmentR(this, R.id.fl_dashboard_container, homeFragment)
                    else
                    navigationController.openHomeFragment(this, R.id.fl_dashboard_container, homeFragment)
                }else{
                    showErrorToast(getString(R.string.error_message_auth))
                }/*else{
                    getChannelDetail(null)
                }
                    navigationController.openHomeFragment(this, R.id.fl_dashboard_container, HomeFragment.newInstance(widgetObject))*/
            }

        }

        dashboardActivityVM.channelObjectData.observeForever {
            if (it!=null) {
                if(it.channel==null) {
                    dashboardActivityVM.getChannelDetail(
                        null,
                        null,
                        null,
                        null,
                        UUID.randomUUID().toString(),
                        it.team_id
                    )
                }else{
                    appDataManager.setChannel(it.channel!!);
                    appDataManager.setUUID(it.uuid!!);
                    getPubnubDetail(it.team_id)
                }
            }

        }
    }

    public fun logout(){
        finish()
    }

    private fun initializeProgressLoader() {
        progressDialog = Dialog(this)
        progressDialog.setCancelable(false)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog.setContentView(R.layout.dialog_progress)
    }


    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount <= 0) {
//            ++k
//            when (k) {
//                1 -> {
//                    Toast.makeText(this, getString(R.string.back_press_message), Toast.LENGTH_SHORT).show()
//                    Handler().postDelayed({ k = 0 }, 2000)
//                }
//                2 -> super.onBackPressed()
//                else -> super.onBackPressed()
//            }
//        } else {
//
//            super.onBackPressed()
//        }

    }




    override fun onPause() {

        super.onPause()
        Log.d("onPauseDashboard", "onPauseDashboard")
//        MyApplication.activityPaused()// On Pause notify the Application

    }

    override fun onResume() {
        super.onResume()
//        checkPermission()
        Log.d("onResumeDashboard", "onResumeDashboard")
//        MyApplication.activityResumed()// On Resume notify the Application
//        setImageAndName()

    }


    public fun removeAllFragments() {
        val fragments = supportFragmentManager.fragments
        if (fragments != null) {
            for (fragment in fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }
    }




    fun removeFragment() {
//        supportFragmentManager.popBackStackImmediate()
        supportFragmentManager.popBackStack()
        supportFragmentManager.popBackStack()
        supportFragmentManager.popBackStack()

    }


    fun removeFragment1() {
//        supportFragmentManager.popBackStackImmediate()
        supportFragmentManager.popBackStack()

    }


    fun removeFragmentFromS(category: String?, subcategory: String?, zipcode: String?) {
//        supportFragmentManager.popBackStackImmediate()
        supportFragmentManager.popBackStack()
        supportFragmentManager.popBackStack()
//        supportFragmentManager.popBackStack()
//        showChooseUserFragmentS(category, subcategory, zipcode)

    }



   public fun close(){
       finish()
   }







    override fun onStop() {
        super.onStop()
    }







}
