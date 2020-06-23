package com.phone91.sdk.data.remote


//import com.phone91.sdk.model.ProfileObject
import android.util.Log
import com.google.gson.JsonObject
import com.phone91.sdk.data.preference.AppPreferenceManager
import io.reactivex.Observable
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RemoteDataManager @Inject constructor(private val appPreferenceManager: AppPreferenceManager) :
    RemoteSource {


//    private lateinit var iApiCreateAccessToken: IAuthApiService
//    private var iApiAuthService: IAuthApiService? = null
    private var iApiService: IAuthApiService? = null

//
//    private lateinit var webServCreateCus: Retrofit
//    private lateinit var webServCharge: Retrofit
    private lateinit var webServiceInstance: Retrofit

    init {
//        initializeWebServer()
    }


//    private fun initCreateCusServer() {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor(logging)
//        httpClient.connectTimeout(30, TimeUnit.SECONDS)
//        httpClient.readTimeout(30, TimeUnit.SECONDS)
//        httpClient.writeTimeout(30, TimeUnit.SECONDS)
//
//        webServCreateCus = Retrofit.Builder()
//
//               // .baseUrl("https://kr.cisinlive.com/amen/api/")
//                .baseUrl("https://adorationministry.com/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(httpClient.build())
//                .build()
//
//        iApiCreateAccessToken = webServCreateCus.create(IAuthApiService::class.java)
//    }


//    private fun initializeAuthWebServer() {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor(logging)
//        httpClient.connectTimeout(30, TimeUnit.SECONDS)
//        httpClient.readTimeout(30, TimeUnit.SECONDS)
//        httpClient.writeTimeout(30, TimeUnit.SECONDS)
//        if (appPreferenceManager.getWidgetToken() != null) {
//            httpClient.addInterceptor(Interceptor() { chain: Interceptor.Chain ->
//                var original = chain.request()
//                var request = original.newBuilder()
//                    .header("API-ACCESS-TOKEN", appPreferenceManager.getWidgetToken())
//                    .method(original.method(), original.body())
//                    .build();
//                chain.proceed(request)
//            })
//        }
//
//        webServiceInstance = Retrofit.Builder()
//
//               // .baseUrl("https://kr.cisinlive.com/amen/api/" /*+"" *//*MyApplication.user!!.stripeCusId*//* + "/"*/)
//                .baseUrl("https://adorationministry.com/api/" /*+"" *//*MyApplication.user!!.stripeCusId*//* + "/"*/)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(httpClient.build())
//                .build()
//
//        iApiAuthService = webServiceInstance.create(IAuthApiService::class.java)
//
//    }

    //    private fun initializeWebServer() {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor(logging)
//        httpClient.connectTimeout(30, TimeUnit.SECONDS)
//        httpClient.readTimeout(30, TimeUnit.SECONDS)
//        httpClient.writeTimeout(30, TimeUnit.SECONDS)
////        https@ //kr.cisinlive.com/amen/api/login
//        webServiceInstance = Retrofit.Builder()
//            .baseUrl("https://kr.cisinlive.com/amen/api/" /*+"" *//*MyApplication.user!!.stripeCusId*//* + "/"*/)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(httpClient.build())
//            .build()
//        iApiAuthService = webServiceInstance.create(IAuthApiService::class.java)
//
//    }
    private fun initializeWebServer() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)


        if (appPreferenceManager.getWidgetToken() != null) {
            Log.d("header",appPreferenceManager.getWidgetToken())
            httpClient.addInterceptor(Interceptor() { chain: Interceptor.Chain ->
                var original = chain.request()
                var request = original.newBuilder()
//                Authorization
//                 .addHeader("Authorization", appPreferenceManager.getWidgetToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Connection", "keep-alive")
                    //.addHeader("Authorization", appPreferenceManager.getWidgetToken())
                    .method(original.method(), original.body())
                    .build();
                chain.proceed(request)
            })

        }

        webServiceInstance = Retrofit.Builder()

            // .baseUrl("https://kr.cisinlive.com/amen/api/" /*+"" *//*MyApplication.user!!.stripeCusId*//* + "/"*/)
            .baseUrl("https://testapi.phone91.com" /*+"" *//*MyApplication.user!!.stripeCusId*//* + "/"*/)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())

            .build()
        iApiService = webServiceInstance.create(IAuthApiService::class.java)

    }

    override fun getClientDetail(): Observable<Response<JsonObject>> {


                if (iApiService == null) {
                  initializeWebServer()
        }
        return iApiService!!.getClientDetail(appPreferenceManager.getWidgetToken()!!)
    }


//    "channel": <channel-name-if-already present>, // other parameters are ignored if channel passed
//    "name": <name>,
//    "number": <number>,
//    "mail": <mail>,
//    "unique_id": <unique_id>,
//    "team_id": <team_id>


//    override fun getAPIAccessToken(): Observable<Response<JsonObject>> {
//        if (iApiCreateAccessToken == null) {
//            initCreateCusServer()
//        }
//        return iApiCreateAccessToken!!.getAPIAccessToken()
//    }
//    override fun getReceipent(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        return iApiService!!.getReceipent()
//    }
//
//    override fun getRecipientHistory(recipientId: String?): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        return iApiService!!.getRecipientHistory(recipientId!!)
//    }
//
//    override fun getHistory(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        return iApiService!!.getHistory()
//    }
//
//    override fun getNotification(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        return iApiService!!.getNotification()
//    }
//
//    override fun cardDetails(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        return iApiService!!.cardDetails()
//    }
//
//    override fun accountDetails(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        return iApiService!!.accountDetails()
//    }
//    override fun clearNotification(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        return iApiService!!.clearNotification()
//    }
//
//    override fun getSearchUsers(query: String): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        var query1 = RequestBody.create(MediaType.parse("text/plain"), query)
//        return iApiService!!.getSearchUsers(query)
//    }
//    override fun sendMoney(id: Int?, amount: String, transactionType: String, address: String): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        var id1 = RequestBody.create(MediaType.parse("text/plain"), id.toString())
//        var amount1 = RequestBody.create(MediaType.parse("text/plain"), amount)
//        var transactionType1 = RequestBody.create(MediaType.parse("text/plain"), transactionType)
//        var address1 = RequestBody.create(MediaType.parse("text/plain"), address)
//        return iApiService!!.sendMoney(id1,amount1,transactionType1,address1)
//    }
//
//    override fun sendMessage(msg: String): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeHomeWebServer()
//        }
//        var msg1 = RequestBody.create(MediaType.parse("text/plain"), msg)
//        return iApiService!!.sendMessage(msg1)
//    }

//    override fun logout(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        return iApiService!!.logout()
//    }

    override fun getChannelDetail(channel: String?, name : String?, number : String?, mail:String?, unique_id : String, team_id : String?): Observable<Response<JsonObject>> {
        if (iApiService== null) {
            initializeWebServer()
        }


//Sss
        var channel1: RequestBody? = null
        var name1: RequestBody? = null
        var number1: RequestBody? = null
        var mail1: RequestBody? = null
        var team_id1: RequestBody? = null


        if (channel != null)
            channel1 = RequestBody.create(MediaType.parse("text/plain"), channel)

        if (name != null)
            name1 = RequestBody.create(MediaType.parse("text/plain"), name)

        if (number != null)
            number1 = RequestBody.create(MediaType.parse("text/plain"), number)

        if (mail != null)
            mail1 = RequestBody.create(MediaType.parse("text/plain"), mail)


         var unique_id1 = RequestBody.create(MediaType.parse("text/plain"), unique_id)

        if (team_id != null)
            team_id1 = RequestBody.create(MediaType.parse("text/plain"), team_id)



        return iApiService!!.getChannelDetail(channel1,name1,number1,mail1,unique_id1,team_id1,appPreferenceManager.getWidgetToken()!!)
    }

override fun getPubnubDetail(): Observable<Response<JsonObject>> {
        if (iApiService== null) {
            initializeWebServer()
        }
        return iApiService!!.getPubnubDetail(appPreferenceManager.mWidgetToken+":"+appPreferenceManager.mUUID)
    }
override fun sendImage(imagePath :String): Observable<Response<JsonObject>> {
        if (iApiService== null) {
            initializeWebServer()
        }
    var image: MultipartBody.Part? = null
    if (imagePath != null) {
            val file = File(imagePath)
            val fbody = RequestBody.create(MediaType.parse("image/*"), file)
            image = MultipartBody.Part.createFormData("attachment", file.name, fbody)


        }
        return iApiService!!.sendImage(appPreferenceManager.mWidgetToken+":"+appPreferenceManager.mUUID,image)
    }


//    override fun fbuserMobileVerification(
//        profileObject: ProfileObject,
//        name: JsonObject,
//        code: String,
//        apnstoken: String
//    ): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//
////        {"name":"tester", "email":"dtbgkntwqf_1574853265@tfbnw.net", "mobile":"+911234567890",
////            "verification_code":"9996", "apnstoken":"dfsdfsdfsdfsdfsdf1", "device_type":"IOS",
////            "referral_code":"5TdHDdf5"}
//
//
//        var name1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.name)
//        var email1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.email)
//        var mobile1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.mobile)
//        var verification_code1 = RequestBody.create(MediaType.parse("text/plain"), code)
//        var apnstoken = RequestBody.create(MediaType.parse("text/plain"), apnstoken)
//        var device_type = RequestBody.create(MediaType.parse("text/plain"), "Android")
//
//
//        var reff_code1: RequestBody? = null
//
//        if (profileObject.referral_code != null && !profileObject.referral_code.equals(""))
//            reff_code1 =
//                RequestBody.create(MediaType.parse("text/plain"), profileObject.referral_code)
//
//
//
//        return iApiService!!.fbuserMobileVerification(
//            name1,
//            email1,
//            mobile1,
//            verification_code1,
//            apnstoken,
//            device_type,
//            reff_code1
//        )
//    }


//    override fun VerifyOTPAndResetPassword(
//        mobile: String,
//        otp: String,
//        password: String
//    ): Observable<Response<JsonObject>> {
//        if (iApiAuthService == null) {
//            initializeAuthWebServer()
//        }
//
//        var mobile1 = RequestBody.create(MediaType.parse("text/plain"), mobile)
//        var otp1 = RequestBody.create(MediaType.parse("text/plain"), otp)
//        var password1 = RequestBody.create(MediaType.parse("text/plain"), password)
//
//        return iApiAuthService!!.VerifyOTPAndResetPassword(mobile1, otp1, password1)
//    }
//
//
//
//
//    override fun sendOTP(mobile: String, otp: String): Observable<Response<JsonObject>> {
//        if (iApiAuthService == null) {
//            initializeAuthWebServer()
//        }
//
//        var otp1 = RequestBody.create(MediaType.parse("text/plain"), otp)
//        var mobile1 = RequestBody.create(MediaType.parse("text/plain"), mobile)
//
//        return iApiAuthService!!.sendOTP(mobile1, otp1)
//    }
//
//    override fun callForgotPassword(email: String): Observable<Response<JsonObject>> {
//        if (iApiAuthService == null) {
//            initializeAuthWebServer()
//        }
//
//        var email1 = RequestBody.create(MediaType.parse("text/plain"), email)
//
//        return iApiAuthService!!.callForgotPassword(email1)
//    }
//
//    override fun callForgotPasswordPhone(phone: String): Observable<Response<JsonObject>> {
//        if (iApiAuthService == null) {
//            initializeAuthWebServer()
//        }
//
//        var phone1 = RequestBody.create(MediaType.parse("text/plain"), phone)
//
//        return iApiAuthService!!.callForgotPasswordPhone(phone1)
//    }

//    override fun reSendOTP(profileObject: ProfileObject): Observable<Response<JsonObject>> {
//        if (iApiAuthService == null) {
//            initializeAuthWebServer()
//        }
//
//        var name1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.name)
//        var email1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.email)
//        var password1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.password)
//        var device_type1 = RequestBody.create(MediaType.parse("text/plain"), "Android")
//        var mobile1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.mobile)
//        var refcode1: RequestBody? = null
//
//        if (profileObject.referral_code != null)
//            refcode1 =
//                RequestBody.create(MediaType.parse("text/plain"), profileObject.referral_code)
//        if (profileObject.referral_code != null)
//            refcode1 =
//                RequestBody.create(MediaType.parse("text/plain"), profileObject.referral_code)
////        dob, gender, location, state_id, lga_id
//        var dob1: RequestBody? = null
//
//        if (profileObject.dob != null)
//            dob1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.dob)
//
//        var gender1: RequestBody? = null
//
//        if (profileObject.gender != null)
//            gender1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.gender)
//
//        var location1: RequestBody? = null
//
//        if (profileObject.location != null)
//            location1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.location)
//
//        var state_id1: RequestBody? = null
//
//        if (profileObject.state_id != null)
//            state_id1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.state_id)
//
//        var lga_id1: RequestBody? = null
//
//        if (profileObject.lga_id != null)
//            lga_id1 = RequestBody.create(MediaType.parse("text/plain"), profileObject.lga_id)
//
//        return iApiAuthService!!.reSendOTP(
//            name1, email1, mobile1, device_type1, password1, refcode1,
//            dob1, gender1, location1, state_id1, lga_id1
//        )
//    }

//    override fun fbUserRequestMobileOTP(mobile: String): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//
//        var mobile1 = RequestBody.create(MediaType.parse("text/plain"), mobile)
//        var device_type1 = RequestBody.create(MediaType.parse("text/plain"), "Android")
//
//
//        return iApiService!!.fbUserRequestMobileOTP(mobile1, device_type1)
//    }
//
//
//
//    override fun callSignin(
//        email: String,
//        password: String,
//        token: String?
//    ): Observable<Response<JsonObject>> {
//        if (iApiAuthService == null) {
//            initializeAuthWebServer()
//        }
//
//        var email1 = RequestBody.create(MediaType.parse("text/plain"), email)
//        var password1 = RequestBody.create(MediaType.parse("text/plain"), password)
//        var device_type1 = RequestBody.create(MediaType.parse("text/plain"), "Android")
//        var apnstoken1 = RequestBody.create(MediaType.parse("text/plain"), token)
//
//        return iApiAuthService!!.callSignin(email1, password1, device_type1, apnstoken1)
//    }
//
//    override fun getStateList(): Observable<Response<JsonObject>> {
//        if (iApiAuthService == null) {
//            initializeAuthWebServer()
//        }
//
//        return iApiAuthService!!.getStateList()
//    }
//
//    override fun getLgaByStateId(id: String): Observable<Response<JsonObject>> {
//        if (iApiAuthService == null) {
//            initializeAuthWebServer()
//        }
//        var id1 = RequestBody.create(MediaType.parse("text/plain"), id)
//        return iApiAuthService!!.getLgaByStateId(id1)
//    }
//
//    override fun callFacebookAPI(
//        userObj: JSONObject,
//        token: String?
//    ): Observable<Response<JsonObject>> {
//        if (iApiAuthService == null) {
//            initializeAuthWebServer()
//        }
//
//
//
//        var picture = userObj.getJSONObject("picture").getJSONObject("data").getString("url")
//        var name = userObj.getString("name");
//        var id = userObj.getString("id");
//        var email: String? = null
//        if (userObj.has("email")) {
//            email = userObj.getString("email");
//        }
//
//
//        var email1 = RequestBody.create(MediaType.parse("text/plain"), email)
//        var name1 = RequestBody.create(MediaType.parse("text/plain"), name)
//        var id1 = RequestBody.create(MediaType.parse("text/plain"), id)
//        var provider1 = RequestBody.create(MediaType.parse("text/plain"), "FACEBOOK")
//        var photoUrl1 = RequestBody.create(MediaType.parse("text/plain"), picture)
//        var device_type1 = RequestBody.create(MediaType.parse("text/plain"), "Android")
//        var apnstoken1 = RequestBody.create(MediaType.parse("text/plain"), token)
//
//        return iApiAuthService!!.callFacebookAPI(
//            name1,
//            email1,
//            id1,
//            provider1,
//            photoUrl1,
//            device_type1,
//            apnstoken1
//        )
//    }
//
//    override fun changePassword(
//        oldPassword: String,
//        newPassword: String
//    ): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//
//        var oldPassword1 = RequestBody.create(MediaType.parse("text/plain"), oldPassword)
//        var newPassword1 = RequestBody.create(MediaType.parse("text/plain"), newPassword)
//
//        return iApiService!!.changePassword(oldPassword1, newPassword1)
//    }
//
//
//    override fun getUserDetails(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        return iApiService!!.getUserDetails()
//    }
//
//    override fun getCoinsHistory(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        return iApiService!!.getCoinsHistory()
//    }
//
//    override fun getSubscription(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        return iApiService!!.getSubscription("1", "1")
//    }
//
//    override fun getMonthlyPlanDetails(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        return iApiService!!.getMonthlyPlanDetails()
//    }
//
//    override fun getProjectDetails(id: String?): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        var id1 = RequestBody.create(MediaType.parse("text/plain"), id)
//        return iApiService!!.getProjectDetails(id1)
//    }

//    override fun saveSubscription(purchase: Purchase): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//
//
//        var orderId1 = RequestBody.create(MediaType.parse("text/plain"), purchase.orderId)
//        var packageName1 = RequestBody.create(MediaType.parse("text/plain"), purchase.packageName)
//        var productId1 = RequestBody.create(MediaType.parse("text/plain"), purchase.sku)
//        var purchaseTime1 =
//            RequestBody.create(MediaType.parse("text/plain"), purchase.purchaseTime.toString())
//        var purchaseToken1 =
//            RequestBody.create(MediaType.parse("text/plain"), purchase.purchaseToken)
//        var autoRenewing1 = /*purchase.isAutoRenewing*/
//            RequestBody.create(MediaType.parse("text/plain"), purchase.isAutoRenewing.toString())
//        var device_type1 = RequestBody.create(MediaType.parse("text/plain"), "Android")
//        return iApiService!!.saveSubscription(
//            orderId1, packageName1, productId1, purchaseTime1, purchaseToken1,
//            autoRenewing1, device_type1
//        )
//    }

//    override fun verifyAndSaveAppAccessMonthlySubscription(refence: String): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        var reference1 = RequestBody.create(MediaType.parse("text/plain"), refence)
//        var trxref1 = RequestBody.create(MediaType.parse("text/plain"), refence)
//        var device_type1 = RequestBody.create(MediaType.parse("text/plain"), "Android")
//        return iApiService!!.verifyAndSaveAppAccessMonthlySubscription(
//            reference1,
//            trxref1,
//            device_type1
//        )
//    }
//
//    override fun verifyAndSaveProjectMonthlySubscription(refence: String): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        var reference1 = RequestBody.create(MediaType.parse("text/plain"), refence)
//        var trxref1 = RequestBody.create(MediaType.parse("text/plain"), refence)
//        var device_type1 = RequestBody.create(MediaType.parse("text/plain"), "Android")
//        return iApiService!!.verifyAndSaveProjectMonthlySubscription(
//            reference1,
//            trxref1,
//            device_type1
//        )
//    }
//
//    override fun getQuotes(): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        return iApiService!!.getQuotes("1", "1")
//    }
//
//    override fun getMusicList(page: Int): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        return iApiService!!.getMusicList(page.toString(), "10")
//    }
//
//    override fun getNotificationList(page: Int): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        return iApiService!!.getNotificationList(page.toString(), "10")
//    }
//
//    override fun getProjectList(page: Int): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        return iApiService!!.getProjectList(page.toString(), "10")
//    }
//
//    override fun updateUserProfile(
//        name: String,
//        mobile: String?,
//        user_img: String?
//    ): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        var image: MultipartBody.Part? = null
//        var mobile1: RequestBody? = null
//
//        var name1 = RequestBody.create(MediaType.parse("text/plain"), name)
//
//
//        if (mobile != null) {
//            mobile1 = RequestBody.create(MediaType.parse("text/plain"), mobile)
//        }
//
//        if (user_img != null) {
//            val file = File(user_img)
//            val fbody = RequestBody.create(MediaType.parse("image/*"), file)
//            image = MultipartBody.Part.createFormData("user_img", file.name, fbody)
//        }
//        return iApiService!!.updateUserProfile(name1, mobile1, image)
//    }


//    fun addPost(
//        caption: String,
//        location: String,
//        user_img: String,
//        publish_date: String,
//        file_type: String,
//        device_type: String,
//        tagged_ids: String
//    ): Observable<Response<JsonObject>> {
//        if (iApiService == null) {
//            initializeWebServer()
//        }
//        var image: MultipartBody.Part? = null
//        var mobile1: RequestBody? = null
//
//        var caption1 = RequestBody.create(MediaType.parse("text/plain"), caption)
//        var location1 = RequestBody.create(MediaType.parse("text/plain"), location)
//        var publish_date1 = RequestBody.create(MediaType.parse("text/plain"), publish_date)
//        var file_type1 = RequestBody.create(MediaType.parse("text/plain"), file_type)
//        var device_type1 = RequestBody.create(MediaType.parse("text/plain"), device_type)
//        var tagged_ids1 = RequestBody.create(MediaType.parse("text/plain"), tagged_ids)
//
//
//        if (user_img != null) {
//            val file = File(user_img)
//            val fbody = RequestBody.create(MediaType.parse("image/*"), file)
//            image = MultipartBody.Part.createFormData("post_file[]", file.name, fbody)
//        }
//        return iApiService!!.addPost(
//            caption1,
//            location1,
//            image,
//            publish_date1,
//            file_type1,
//            device_type1,
//            tagged_ids1
//        )
//    }




}

