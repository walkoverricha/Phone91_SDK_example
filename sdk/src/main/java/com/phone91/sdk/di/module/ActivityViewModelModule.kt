package com.phone91.sdk.di.module

import androidx.lifecycle.ViewModel
import com.phone91.sdk.di.key.ViewModelKey
import com.phone91.sdk.mvvm.dashboard.DashboardActivityVM
//import com.mbaka.ui.mvvm.createpost.CreatePostActivityVM
//import com.mbaka.ui.mvvm.dashboard.DashboardActivityVM
//import com.mbaka.ui.mvvm.dashboard.videoactivity.VideoActViewModel
//import com.mbaka.ui.mvvm.dashboard.videoplayer.VideoViewModel
//import com.mbaka.ui.mvvm.forgotpassword.ForgotViewModel
//import com.mbaka.ui.mvvm.intro.IntroductionViewModel
//import com.mbaka.ui.mvvm.otp.OTPViewModel
//import com.mbaka.ui.mvvm.resetpassword.ResetPasswordViewModel
//import com.mbaka.ui.mvvm.signin.SigninViewModel
//import com.mbaka.ui.mvvm.signup.SignupViewModel
//import com.mbaka.ui.mvvm.splash.SplashViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by CIS Dev 816 on 30/3/18.
 */
@Module(includes = [FragmentViewModelModule::class])
abstract class ActivityViewModelModule {


//    @Binds
//    @IntoMap
//    @ViewModelKey(SplashViewModel::class)
//    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
//
    @Binds
    @IntoMap
    @ViewModelKey(DashboardActivityVM::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardActivityVM): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(CreatePostActivityVM::class)
//    abstract fun bindCreatePostActivityVM(createPostActivityVM: CreatePostActivityVM): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(SignupViewModel::class)
//    abstract fun bindAuthViewModel(signupViewModel: SignupViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ForgotViewModel::class)
//    abstract fun bindForgotViewModel(forgotViewModel: ForgotViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(OTPViewModel::class)
//    abstract fun bindOTPViewModel(oTPViewModel: OTPViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(SigninViewModel::class)
//    abstract fun bindSigninViewModel(signinViewModel: SigninViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ResetPasswordViewModel::class)
//    abstract fun bindResetPasswordViewModel(resetPasswordViewModel: ResetPasswordViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(IntroductionViewModel::class)
//    abstract fun bindIntroductionViewModel(introductionViewModel: IntroductionViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(VideoActViewModel::class)
//    abstract fun bindVideoActViewModel(videoViewModel: VideoActViewModel): ViewModel
}