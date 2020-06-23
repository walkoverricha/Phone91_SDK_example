package com.phone91.sdk.di.module

//import com.titan.ui.auth.AuthModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.phone91.sdk.di.scope.ActivityScoped
import com.phone91.sdk.mvvm.dashboard.DashboardActivity
import com.phone91.sdk.mvvm.dashboard.DashboardActivityModule

//import com.mbaka.ui.mvvm.createpost.CreatePostActivity
//import com.mbaka.ui.mvvm.dashboard.DashboardActivity
//import com.mbaka.ui.mvvm.dashboard.incomingvideocall.IncomingVideoModule
//import com.mbaka.ui.mvvm.dashboard.videoactivity.IncomingVideoCallActivity
//import com.mbaka.ui.mvvm.dashboard.videoactivity.VideoActivity
//import com.mbaka.ui.mvvm.dashboard.videoactivity.VideoModule
//import com.mbaka.ui.mvvm.forgotpassword.ForgotActivity
//import com.mbaka.ui.mvvm.forgotpassword.ForgotModule
//import com.mbaka.ui.mvvm.intro.Introduction
//import com.mbaka.ui.mvvm.intro.IntroductionModule
//import com.mbaka.ui.mvvm.otp.OTPActivity
//import com.mbaka.ui.mvvm.otp.OTPModule
//import com.mbaka.ui.mvvm.resetpassword.ResetPasswordActivity
//import com.mbaka.ui.mvvm.resetpassword.ResetPasswordModule
//import com.mbaka.ui.mvvm.signin.SigninActivity
//import com.mbaka.ui.mvvm.signin.SigninModule
//import com.mbaka.ui.mvvm.signup.SignupActivity
//import com.mbaka.ui.mvvm.signup.SignupModule
//import com.mbaka.ui.mvvm.splash.SplashActivity
//import com.mbaka.ui.mvvm.splash.SplashModule
//import com.mkaba.ui.mvvm.dashboard.CreatePostActivityModule
//import com.mkaba.ui.mvvm.dashboard.DashboardActivityModule



/**
 * Created by CIS Dev 816 on 30/3/18.
 */
@Module
abstract class ActivityBindingModule {


    @ContributesAndroidInjector(modules = [DashboardActivityModule::class, DashboardFragmentBindingModule::class])
    @DashboardActivityModule.ActivityScoped
    abstract fun dashboardActivity(): DashboardActivity
//
//
////
//    @ContributesAndroidInjector(modules = [SplashModule::class])
//    @SplashModule.ActivityScoped
//    abstract fun splashActivity(): SplashActivity
//
//
//
//    @ContributesAndroidInjector(modules = [CreatePostActivityModule::class,CreatePostFragmentBindingModule::class])
//    @CreatePostActivityModule.ActivityScoped
//    abstract fun createPostActivity(): CreatePostActivity



//    @ContributesAndroidInjector(modules = [SignupModule::class])
//    @SignupModule.ActivityScoped
//    abstract fun signupActivity(): SignupActivity
//
//
//    @ContributesAndroidInjector(modules = [OTPModule::class])
//    @OTPModule.ActivityScoped
//    abstract fun oTPActivity(): OTPActivity
//
//    @ContributesAndroidInjector(modules = [SigninModule::class])
//    @SigninModule.ActivityScoped
//    abstract fun signinActivity(): SigninActivity
//
//    @ContributesAndroidInjector(modules = [ForgotModule::class])
//    @ActivityScoped
//    abstract fun forgotActivity(): ForgotActivity
//
//
//    @ContributesAndroidInjector(modules = [ResetPasswordModule::class])
//    @ActivityScoped
//    abstract fun resetPasswordActivity(): ResetPasswordActivity
//
//    @ContributesAndroidInjector(modules = [IntroductionModule::class])
//    @ActivityScoped
//    abstract fun Introduction(): Introduction
//
//
//    @ContributesAndroidInjector(modules = [VideoModule::class])
//    @ActivityScoped
//    abstract fun videoActivity(): VideoActivity
//
//
//    @ContributesAndroidInjector(modules = [IncomingVideoModule::class])
//    @ActivityScoped
//    abstract fun incomingVideoCallActivity(): IncomingVideoCallActivity


}