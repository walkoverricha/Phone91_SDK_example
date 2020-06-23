package com.phone91.sdk.mvvm.dashboard.home


//import com.phone91.sdk.mvvm.dashboard.postdetails.PostDetailsFragment
//import kotlinx.android.synthetic.main.fragment_home_new.*

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fasterxml.jackson.databind.JsonNode
import com.phone91.sdk.R
import com.phone91.sdk.data.AppDataManager
import com.phone91.sdk.di.module.AppViewModelFactory
import com.phone91.sdk.model.ChatObject
import com.phone91.sdk.model.WidgetObject
import com.phone91.sdk.mvvm.base.BaseFragment
import com.phone91.sdk.mvvm.dashboard.DashboardActivity
import com.phone91.sdk.utils.JsonUtil
import com.phone91.sdk.utils.PathUtil
import com.phone91.sdk.utils.toolbar.FragmentToolbar
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.callbacks.PNCallback
import com.pubnub.api.models.consumer.PNPublishResult
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.history.PNHistoryResult
import kotlinx.android.synthetic.main.fragment_home_new.*
import java.io.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment<HomeViewModel>() {


    private lateinit var progressDialog: Dialog

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var chatAdapter: ChatAdapter
    @Inject
    lateinit var appDataManager: AppDataManager
    var PUBSUB_CHANNEL: List<String>?=null


    private var mPubnub_DataStream: PubNub? = null
    companion object {
//        @JvmStatic
        fun newInstance() = HomeFragment()


        var widgetObject: WidgetObject? = null

//
        @JvmStatic
        fun newInstance(widgetObject: WidgetObject): HomeFragment {
            this.widgetObject = widgetObject
            var fragment = HomeFragment()
            return fragment
        }

    }

        override fun builder(): FragmentToolbar? {

        return FragmentToolbar.Builder()
                    .withId(R.id.toolBarL)
                    .withTitle(widgetObject?.name!!)
                    .withDescription(widgetObject?.tagline!!)
            .withClose()
                    .build()
    }



    override fun getFragmentTAG(): FragmentTAG = FragmentTAG.HOME_FRAGMENT

    override fun getViewModel(): HomeViewModel {
        homeViewModel = ViewModelProviders.of(this@HomeFragment, appViewModelFactory)
            .get(HomeViewModel::class.java)
        return homeViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home_new, container, false)
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           homeViewModel.getNameByChannel(appDataManager.getChannel()!!)

        PUBSUB_CHANNEL =
            Arrays.asList<String>(appDataManager.getChannel())

        progressDialog = Dialog(activity!!)
        progressDialog.setCancelable(false)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog.setContentView(R.layout.dialog_progress)


        chatAdapter=ChatAdapter(activity, ArrayList())
//        teamAdapter.setOnItemSelectionListener(this)
        var layoutManager = LinearLayoutManager(activity as DashboardActivity, RecyclerView.VERTICAL, false)
        rvPosts.layoutManager=layoutManager
        rvPosts.apply {
            adapter = chatAdapter
        }

        initPubNub()
        initChannels()

        observeError()
        observeLoading()
        observeSuccess()

        imgSend.setOnClickListener {
            if (!editMessage.text.toString().trim().equals("")) {
                var message = HashMap<String, String>()
//                message.put("type", "chat")
//                message.put("content", editMessage.text.toString().trim())
//                message.put("sender", appDataManager.getUUID()!!)
                message.put("content", editMessage.text.toString().trim())
                message.put("type", "chat")
//            val message: Map<String, String> =
//                Map.< String, String>of<kotlin.String?, kotlin.String?>("sender", this@MainActivity .mUsername, "message", mMessage.getText().toString(), "timestamp", DateTimeUtil.getTimeStampUtc())

                mPubnub_DataStream?.publish()?.channel(appDataManager.getChannel())
                    ?.message(message)?.async(
                        object : PNCallback<PNPublishResult?>() {
                            override fun onResponse(result: PNPublishResult?, status: PNStatus) {
                                try {
                                    if (!status.isError) {
                                        editMessage.setText("")
                                        Log.v(
                                            "message1",
                                            "publish(" + result?.timetoken + ")"
                                        )
                                    } else {
                                        Log.v(
                                            "message1",
                                            "publishErr(" + result?.timetoken + ")"
                                        )
                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    )
            }

        }


        imgBack.setOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
        }

        attachment.setOnClickListener{
            if (checkPermission())
                openCameraAndGalley()
        }
    }

    private val CAMERA_PERMISSION_REQUEST_CODE = 88
    private fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        // request camera permission if it has not been grunted.
        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CAMERA
            ) !== android.content.pm.PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) !== android.content.pm.PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) !== android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), CAMERA_PERMISSION_REQUEST_CODE
            )
            return false
        } else
            return true
    }


    val OPEN_CAMERA = 2
    val OPEN_GALLARY = 3
    private var selectedImage: String? = null
    private fun openCameraAndGalley() {

        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.select_option_new)

        val select_from_gallery = dialog.findViewById(R.id.select_from_gallery) as Button
        val select_from_camera = dialog.findViewById(R.id.select_from_camera) as Button
        val cancel = dialog.findViewById(R.id.cancel) as Button

        select_from_gallery.setOnClickListener(View.OnClickListener {
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(
                pickPhoto,
                OPEN_GALLARY
            )//one can be replaced with any action code
            dialog.dismiss()
        })

        select_from_camera.setOnClickListener(View.OnClickListener {
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture, OPEN_CAMERA)
            dialog.dismiss()
        })

        cancel.setOnClickListener(View.OnClickListener { dialog.dismiss() })
        dialog.show()
    }


    private fun initPubNub() {
        val config = PNConfiguration()
        config.publishKey =/* "pub-c-81312503-4271-4283-9bf9-3ecd6d9254a2"*/appDataManager.getPubkey()
        config.subscribeKey =/*"sub-c-2b6260c4-7640-11ea-808e-bad180999bc3"*/ appDataManager.getSubkey() //Replace with your subscribe key
        config.authKey=appDataManager.getAuth()
//appDataManager.getSubkey()
        config.uuid = appDataManager.getUUID()
        config.isSecure = true
        this.mPubnub_DataStream = PubNub(config)
    }

    private fun initChannels() {
Log.d("Chneel_id",PUBSUB_CHANNEL?.get(0))
        this.mPubnub_DataStream?.addListener(ChatCallback(this.chatAdapter,rvPosts))

        this.mPubnub_DataStream?.history()
            ?.channel(PUBSUB_CHANNEL?.get(0))
//            ?.reverse(false)
            ?.count(100)
            ?.includeTimetoken(true)
            ?.async(object : PNCallback<PNHistoryResult>() {
                override fun onResponse(result: PNHistoryResult, status: PNStatus) {
                    if (!status.isError && !result.messages.isEmpty()) {
                        for (message in result.messages) {
                            val jsonMsg: JsonNode = message.entry
                            val chatObject: ChatObject = JsonUtil.convert(jsonMsg, ChatObject::class.java)
//           var  messageObject=Gson().fromJson(message!!.message.asText(), MessageObject::class.java)
                            chatAdapter.add(chatObject)

                        }
                    }
                    rvPosts.scrollToPosition(chatAdapter.itemCount-1)
                }
            })

//        this.mPubnub_DataStream?.addListener(this.mPresencePnCallback)
        this.mPubnub_DataStream?.subscribe()
            ?.channels(PUBSUB_CHANNEL)
            ?.withPresence()?.execute()
//        this.mPubnub_DataStream?.hereNow()
//            ?.channels(PUBSUB_CHANNEL)
//            ?.async(object : PNCallback<PNHereNowResult>() {
//                override fun onResponse(result: PNHereNowResult, status: PNStatus) {
//                    if (status.isError) {
//                        return
//                    }
//                    try {
//                        Log.v(
//                            com.pubnub.example.android.datastream.pubnubdatastreams.MainActivity.TAG,
//                            JsonUtil.asJson(result)
//                        )
//                        for ((_, value) in result.channels) {
//                            for (occupant in value.occupants) {
//                                this@MainActivity.mPresence.add(
//                                    PresencePojo(
//                                        occupant.uuid,
//                                        "join",
//                                        DateTimeUtil.getTimeStampUtc()
//                                    )
//                                )
//                            }
//                        }
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                }
//            })

    }



    private fun observeError() {
        homeViewModel.error.observeForever {
            showError(it!!)
        }
    }

    private fun showError(error: String) {
        (activity as DashboardActivity).showErrorToast(error)
    }

    private fun observeLoading() {

        homeViewModel.loading.observeForever {
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
//        homeViewModel.sucesss.observeForever {
//
//        }

        homeViewModel.channelObjectData.observeForever {
            if (it!=null && it.name!=null && !it.name.equals("")) {
                txtName.text=it.name
            }

        }

        homeViewModel.urlData.observeForever {
//            if (!editMessage.text.toString().trim().equals("")) {
                var message = HashMap<String, String?>()
//                message.put("type", "chat")
//                message.put("content", editMessage.text.toString().trim())
//                message.put("sender", appDataManager.getUUID()!!)
            if(!editMessage.text.toString().trim().equals(""))
                message.put("content", editMessage.text.toString().trim())

                message.put("type", "chat")
            message.put("mime_type", "image/*")
            message.put("attachment_url", it)

                mPubnub_DataStream?.publish()?.channel(appDataManager.getChannel())
                    ?.message(message)?.async(
                        object : PNCallback<PNPublishResult?>() {
                            override fun onResponse(result: PNPublishResult?, status: PNStatus) {
                                try {
                                    if (!status.isError) {
                                        editMessage.setText("")
                                        Log.v(
                                            "message1",
                                            "publish(" + result?.timetoken + ")"
                                        )
                                    } else {
                                        Log.v(
                                            "message1",
                                            "publishErr(" + result?.timetoken + ")"
                                        )
                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    )
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {

                OPEN_GALLARY -> {
//                    selectedImage = data?.data
                    selectedImage = PathUtil.getPath(activity, data?.data)
//                    img.setImageURI(data?.data)
//                    if (selectedImage == null) {
//                        showError(getString(R.string.cannot_use))

//                    } else {
//                        val requestOptions = RequestOptions()
//                        requestOptions.placeholder(R.mipmap.my_profile)
//                        requestOptions.error(R.mipmap.my_profile)
//                        Glide.with(activity!!)
//                            .setDefaultRequestOptions(requestOptions)
//                            .load(data?.data)
//                            .into(img)
//                    }
                }
                OPEN_CAMERA -> {
//                    selectedImage = data?.data

                    captureImageResult(/*selectedImage, */data)
                }
            }

           homeViewModel.shareFile(selectedImage!!)

        }
    }

    private fun captureImageResult(/*selectedImage: Uri?, */data: Intent?) {
        val thumbnail = data?.extras!!.get("data") as Bitmap
        val bytes = ByteArrayOutputStream()
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val destination = File(
            Environment.getExternalStorageDirectory(),
            System.currentTimeMillis().toString() + ".jpg"
        )
        val fo: FileOutputStream
        try {
            destination.createNewFile()
            fo = FileOutputStream(destination)
            fo.write(bytes.toByteArray())
            fo.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        var uri = Uri.fromFile(destination);
//        img.setImageURI(uri)

//        val requestOptions = RequestOptions()
//        requestOptions.placeholder(R.mipmap.my_profile)
//        requestOptions.error(R.mipmap.my_profile)
//        Glide.with(activity!!)
//            .setDefaultRequestOptions(requestOptions)
//            .load(uri)
//            .into(img)

//        imgProfile.setImageBitmap(thumbnail)
        selectedImage = uri.path
    }
}