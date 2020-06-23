package  com.phone91.sdk.data.remote

import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import javax.annotation.PostConstruct


interface IAuthApiService {

//    @Headers("Authorization: Bearer sk_test_Y6595fffNK5WmCWyZ1ikf0nu002pc9kKxk")
//    @FormUrlEncoded
//    @POST("customers")
//    fun createCustomer(@Field("source") source: String,
//                       @Field("email") email: String): Observable<Response<JsonObject>>
//
//    @Headers("Authorization: Bearer sk_test_Y6595fffNK5WmCWyZ1ikf0nu002pc9kKxk")
//    @FormUrlEncoded
//    @POST("sources")
//    fun addCard(@Field("source") source: String): Observable<Response<JsonObject>>
//
//
//    @Headers("Authorization: Bearer sk_test_Y6595fffNK5WmCWyZ1ikf0nu002pc9kKxk")
//    @FormUrlEncoded
//    @POST("sources")
//    fun addAccount(@Field("source") source: String): Observable<Response<JsonObject>>
//
//
//    @Headers("Authorization: Bearer sk_test_Y6595fffNK5WmCWyZ1ikf0nu002pc9kKxk")
//    @DELETE("sources/{id}")
//    fun deleteCard(@Path("id") cardid: String): Observable<Response<JsonObject>>
//
//
//    @Headers("Authorization: Bearer sk_test_Y6595fffNK5WmCWyZ1ikf0nu002pc9kKxk")
//    @DELETE("sources/{id}")
//    fun deleteAccount(@Path("id") cardid: String): Observable<Response<JsonObject>>


//    @Headers("Authorization: Bearer sk_test_Y6595fffNK5WmCWyZ1ikf0nu002pc9kKxk")
    @GET("/widget-info/")
    fun getClientDetail(@Header("Authorization") auth: String) :Observable<Response<JsonObject>>

//    @GET("recipients")
//    fun getReceipent(): Observable<Response<JsonObject>>
//
//    @GET("wallet_transactions")
//    fun getHistory(): Observable<Response<JsonObject>>
//
//
// @GET("notifications")
//    fun getNotification(): Observable<Response<JsonObject>>
//
//    @GET("card_details")
//    fun cardDetails(): Observable<Response<JsonObject>>
//
//    @GET("bank_account_details")
//    fun accountDetails(): Observable<Response<JsonObject>>
//
//    @GET("notifications/clear_notification")
//    fun clearNotification(): Observable<Response<JsonObject>>
//
//
//
////    @FormUrlEncoded
//    @GET("users/search_user?")
//    fun getSearchUsers(@Query("search") search: String): Observable<Response<JsonObject>>
//
//
////    @Headers("Authorization: Bearer sk_test_Y6595fffNK5WmCWyZ1ikf0nu002pc9kKxk")
////    @GET("sources?object=bank_account")
////    fun getAccounts(): Observable<Response<JsonObject>>
//
//    @FormUrlEncoded
//    getChannelDetail(channel: String?, name : String?, number : String?, mail:String?, unique_id : String, team_id : String?)
    @Multipart
    @POST("/pubnub-channels/")
    fun getChannelDetail(@Part("channel") channel: RequestBody?,
                   @Part("name") name: RequestBody?,
                   @Part("number") number: RequestBody?,
                   @Part("mail") mail: RequestBody?,
                   @Part("unique_id") unique_id: RequestBody,
                   @Part("team_id") team_id: RequestBody?,
                         @Header("Authorization") authorization:String
): Observable<Response<JsonObject>>

//    //        {"name":"tester", "email":"dtbgkntwqf_1574853265@tfbnw.net", "mobile":"+911234567890",
////            "verification_code":"9996", "apnstoken":"dfsdfsdfsdfsdfsdf1", "device_type":"IOS",
////            "referral_code":"5TdHDdf5"}
//

    @GET("/pubnub-keys/")
    fun getPubnubDetail(@Header("Authorization") authorization:String): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("VerifyOTPAndResetPassword")
//    fun VerifyOTPAndResetPassword(@Part("mobile") mobile: RequestBody,
//                   @Part("otp") otp: RequestBody,
//                   @Part("password") password: RequestBody
//): Observable<Response<JsonObject>>
//
    @Multipart
    @POST("/chat-attachment/")
    fun sendImage(@Header("Authorization") authorization:String,@Part file: MultipartBody.Part?): Observable<Response<JsonObject>>

//
//
//    @GET("recipients/{userId}/recipient_transactions")
//    fun getRecipientHistory(@Path("userId")  userId:String): Observable<Response<JsonObject>>
//
//    @Multipart
//    @PUT("users/change_password")
//    fun changePassword(@Part("old_password") name: RequestBody?,
//                 @Part("password") country: RequestBody?,
//                   @Part("password_confirmation") gender: RequestBody?): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("bank_account_details")
//    fun addAccount(@Part("public_token") name: RequestBody?,
//                 @Part("account_id") country: RequestBody?): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("card_details")
//    fun addCard(@Part("stripe_card_token") name: RequestBody?): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("card_details/{cardId}/charge_by_card")
//    fun addMoney(@Path("cardId")  cardId:String,
//     @Part("amount") amount: RequestBody?): Observable<Response<JsonObject>>
//
//    @GET("card_details/{cardId}/remove_card")
//    fun removeCard(@Path("cardId")  cardId:String): Observable<Response<JsonObject>>
//
//    @GET("bank_account_details/{accountId}/remove_bank")
//    fun removeAccount(@Path("accountId")  accountId:String): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST(" bank_account_details/{accountId}/charge_by_bank")
//    fun addAccountMoney(@Path("accountId")  cardId:String,
//     @Part("amount") amount: RequestBody?): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("wallet_transactions/transfer_money")
//    fun sendMoney(@Part("name") name: RequestBody,
//                   @Part("gender") gender: RequestBody,
//                   @Part("age_group") age_group: RequestBody,
//                   @Part("street_address") street_address: RequestBody): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("users/contact_us")
//    fun sendMessage(@Part("text_message") text_message: RequestBody): Observable<Response<JsonObject>>
//
//
//    @POST("logout")
//    fun logout(): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("recipients")
//    fun addRecipient(@Part("name") name: RequestBody,
//                   @Part("email") gender: RequestBody,
//                   @Part("exists_recipient_id") exists_recipient_id: RequestBody/*,
//                   @Part("street_address") street_address: RequestBody,
//                   @Part("country") country: RequestBody*/
//): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("recipients/invite_recipient")
//    fun setInvitation(@Part("email")email: RequestBody,
//                      @Part("mobile_no") mobile_no: RequestBody
//): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("verifyMobileOTP")
//    fun sendOTP(@Part("mobile") mobile: RequestBody,
//                @Part("verification_code") verification_code: RequestBody): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("sendPasswordResetLink")
//    fun callForgotPassword(@Part("email") email: RequestBody): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("sendPasswordResetOTP")
//    fun callForgotPasswordPhone(@Part("mobile") mobile: RequestBody): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("requestMobileOTP")
//    fun reSendOTP(@Part("name") name: RequestBody,
//                  @Part("email") email: RequestBody,
//                  @Part("mobile") mobile_no: RequestBody,
//                  @Part("device_type") device_type: RequestBody,
//                  @Part("password") password: RequestBody,
//                  @Part("referral_code") refcode: RequestBody?,
//                  @Part("dob") dob: RequestBody?,
//                  @Part("gender") gender: RequestBody?,
//                  @Part("location") location: RequestBody?,
//                  @Part("state_id") state_id: RequestBody?,
//                  @Part("lga_id") lga_id: RequestBody?
//    ): Observable<Response<JsonObject>>
//
////    dob, gender, location, state_id, lga_id
//    @Multipart
//    @POST("fbUserRequestMobileOTP")
//    fun fbUserRequestMobileOTP(@Part("mobile") mobile: RequestBody, @Part("device_type") device_type: RequestBody): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("users/resend_confirm_email")
//    fun reSendLink(@Part("email") email: RequestBody): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("login")
//    fun callSignin(
//        @Part("email") email: RequestBody,
//        @Part("password") password: RequestBody,
//        @Part("device_type") device_type: RequestBody,
//    @Part("apnstoken") apnstoken: RequestBody
//    ): Observable<Response<JsonObject>>
//
//
//    @POST("getStateList")
//    fun getStateList(): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("getLgaByStateId")
//    fun getLgaByStateId( @Part("id") id: RequestBody): Observable<Response<JsonObject>>
//
//    //        {"name":"dtbgkntwqf","email":"dtbgkntwqf_1574853265@tfbnw.net","id":"102916740954244"
////            ,"provider":"FACEBOOK",
////            "photoUrl":"https://graph.facebook.com/102916740954244/picture?type=normal",
////            "device_type":"1","ip_address":"103.47.46.75",
////            "apnstoken":"APf-g1OxMe1Eh-ZRUcpTMpEdxw8-Q5Yrwjz8Z4"}
//    @Multipart
//    @POST("socialLogin")
//    fun callFacebookAPI(
//        @Part("name") name: RequestBody,
//        @Part("email") email: RequestBody,
//        @Part("id") id: RequestBody,
//        @Part("provider") provider: RequestBody,
//        @Part("photoUrl") photoUrl: RequestBody,
//        @Part("device_type") device_type: RequestBody,
//        @Part("apnstoken") apnstoken: RequestBody
//    ): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("changePassword")
//    fun changePassword(
//        @Part("old_password") old_password: RequestBody,
//        @Part("new_password") new_password: RequestBody
//    ): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("saveAppAccessMonthlySubscription")
//    fun saveSubscription(
//        @Part("orderId") orderId: RequestBody,
//        @Part("packageName") packageName: RequestBody,
//        @Part("productId") productId: RequestBody,
//        @Part("purchaseTime") purchaseTime: RequestBody,
//        @Part("purchaseToken") purchaseToken: RequestBody,
//        @Part("autoRenewing") autoRenewing: RequestBody,
//        @Part("device_type") device_type: RequestBody
//    ): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("verifyAndSaveAppAccessMonthlySubscription")
//    fun verifyAndSaveAppAccessMonthlySubscription(
//        @Part("reference") orderId: RequestBody,
//        @Part("trxref") packageName: RequestBody,
//        @Part("device_type") device_type: RequestBody
//    ): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("verifyAndSaveProjectMonthlySubscription")
//    fun verifyAndSaveProjectMonthlySubscription(
//        @Part("reference") orderId: RequestBody,
//        @Part("trxref") packageName: RequestBody,
//        @Part("device_type") device_type: RequestBody
//    ): Observable<Response<JsonObject>>
//
//
//
//    @POST("getUserDetails")
//    fun getUserDetails(): Observable<Response<JsonObject>>
//
//    @POST("getCoinsHistory")
//    fun getCoinsHistory(): Observable<Response<JsonObject>>
//
//    @POST("getAppAccessMonthlySubscriptionHistory")
//    fun getSubscription(@Query("page")  page:String,@Query("pagination_limit")  pagination_limit:String): Observable<Response<JsonObject>>
//
//    @POST("getMonthlyPlanDetails")
//    fun getMonthlyPlanDetails(): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("getProjectDetails")
//    fun getProjectDetails(@Part("id") name: RequestBody): Observable<Response<JsonObject>>
//
//    @POST("getQuotes")
//    fun getQuotes(@Query("page")  page:String,@Query("pagination_limit")  pagination_limit:String): Observable<Response<JsonObject>>
//
//    @POST("getSongs")
//    fun getMusicList(@Query("page")  page:String,@Query("pagination_limit")  pagination_limit:String): Observable<Response<JsonObject>>
//
//    @POST("getNotificationlist")
//    fun getNotificationList(@Query("page")  page:String,@Query("pagination_limit")  pagination_limit:String): Observable<Response<JsonObject>>
//
//    @POST("getProjects")
//    fun getProjectList(@Query("page")  page:String,@Query("pagination_limit")  pagination_limit:String): Observable<Response<JsonObject>>
//
//    @Multipart
//    @POST("updateUserProfile")
//    fun updateUserProfile(@Part("name") name: RequestBody,
//                   @Part("mobile") gender: RequestBody?,
//                   @Part file: MultipartBody.Part?
//    ): Observable<Response<JsonObject>>
//
//
//    @Multipart
//    @POST("addPost")
//    fun addPost(
//        @Part("caption") caption1: RequestBody,
//        @Part("location") location1: RequestBody,
//        @Part  image: MultipartBody.Part?,
//        @Part("publish_date")  publishDate1: RequestBody,
//        @Part("file_type") fileType1: RequestBody,
//        @Part("device_type")  deviceType1: RequestBody,
//        @Part("tagged_ids")  taggedIds1: RequestBody
//    ): Observable<Response<JsonObject>>


}