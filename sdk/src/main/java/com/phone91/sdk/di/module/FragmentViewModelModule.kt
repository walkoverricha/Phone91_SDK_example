package com.phone91.sdk.di.module

import androidx.lifecycle.ViewModel
import com.phone91.sdk.di.key.ViewModelKey
import com.phone91.sdk.mvvm.dashboard.home.HomeViewModel
import com.phone91.sdk.mvvm.dashboard.teams.TeamViewModel
//import com.mbaka.ui.mvvm.createpost.addPostFragment.AddPostViewModel
//import com.mbaka.ui.mvvm.createpost.camerafragment.CameraViewModel
//import com.mbaka.ui.mvvm.createpost.editphotocontroller.EditPhotoControlViewModel
//import com.mbaka.ui.mvvm.createpost.editphotofilter.EditPhotoViewModel
//import com.mbaka.ui.mvvm.dashboard.changepassword.ChangePasswordViewModel
//import com.mbaka.ui.mvvm.dashboard.chat.ChatViewModel
//import com.mbaka.ui.mvvm.dashboard.customvideoplayer.CustomYTViewModel
//import com.mbaka.ui.mvvm.dashboard.donation.ProjectListViewModel
//import com.mbaka.ui.mvvm.dashboard.donation.projectdetail.ProjectDetailViewModel
//import com.mbaka.ui.mvvm.dashboard.editprofile.EditProfileViewModel
//import com.mbaka.ui.mvvm.dashboard.gospelmusic.list.MusicListViewModel
//import com.mbaka.ui.mvvm.dashboard.gospelmusic.music.MusicViewModel
//import com.mbaka.ui.mvvm.dashboard.home.HomeViewModel
//import com.mbaka.ui.mvvm.dashboard.monthlysubscription.SubscriptionViewModel
//import com.mbaka.ui.mvvm.dashboard.notification.NotificationViewModel
//import com.mbaka.ui.mvvm.dashboard.postdetails.PostViewModel
//import com.mbaka.ui.mvvm.dashboard.referralcode.ReferralCodeViewModel
//import com.mbaka.ui.mvvm.dashboard.settingsfragment.SettingsViewModel
//import com.mbaka.ui.mvvm.dashboard.tabParentFragment.TabParentViewModel
//import com.mbaka.ui.mvvm.dashboard.tnc.TNCViewModel
//import com.mbaka.ui.mvvm.dashboard.userprofile.ProfileViewModel
//import com.mbaka.ui.mvvm.dashboard.videoplayer.VideoViewModel
//import com.titan.ui.mvvm.dashboard.aboutus.AbouUsViewModel
//import com.titan.ui.mvvm.dashboard.addReceipent.AddReceipeintViewModel
//import com.titan.ui.mvvm.dashboard.addaccount.AddAccountPagerViewModel
//import com.titan.ui.mvvm.dashboard.addmoney.AddMoneyPagerViewModel
//import com.titan.ui.mvvm.dashboard.changepassword.ChangePasswordViewModel
//import com.titan.ui.mvvm.dashboard.contactus.ContactUsViewModel
//import com.titan.ui.mvvm.dashboard.history.HistoryViewModel
//import com.titan.ui.mvvm.dashboard.home.HomeViewModel
//import com.titan.ui.mvvm.dashboard.myprofile.MyProfileViewModel
//import com.titan.ui.mvvm.dashboard.mywallet.MyWalletPagerViewModel
//import com.titan.ui.mvvm.dashboard.notification.NotificationViewModel
//import com.titan.ui.mvvm.dashboard.recipientdetail.RecipientDetailViewModel
//import com.titan.ui.mvvm.dashboard.recipienthistory.RecipientHistoryViewModel
//import com.titan.ui.mvvm.dashboard.sendmoney.SendMoneyViewModel
//import com.organizers.ondemand.ui.auth.Login.LoginViewModel
//import com.organizers.ondemand.ui.auth.Signup.SignUpViewModel
//import com.organizers.ondemand.ui.auth.chooseuser.ChooseUserViewModel
//import com.organizers.ondemand.ui.auth.signupprofessional.SignUpProfViewModel
//import com.organizers.ondemand.ui.chat.ChatViewModel
//import com.organizers.ondemand.ui.chat.MessageChatViewModel
//import com.organizers.ondemand.ui.dashboard.FAQ.FAQViewModel
//import com.organizers.ondemand.ui.dashboard.PaymentDetailsPRO.PaymentDetailsProVM
//import com.organizers.ondemand.ui.dashboard.aboutus.AboutUsViewModel
//import com.organizers.ondemand.ui.dashboard.addproject.AddProjectViewModel
//import com.organizers.ondemand.ui.dashboard.checkzipcode.CheckZipcodeViewModel
//import com.organizers.ondemand.ui.dashboard.checkzipcode.MessageViewModel
//import com.organizers.ondemand.ui.dashboard.checkzipcode.SuccessMessageViewModel
//import com.organizers.ondemand.ui.dashboard.homefragment.HomeViewModel
//import com.organizers.ondemand.ui.dashboard.organizerDetails.OrganizerDetailsViewModel
//import com.organizers.ondemand.ui.dashboard.organizershome.OrganizerHomeViewModel
//import com.organizers.ondemand.ui.dashboard.pastProject.ProjectCompleted.ProjectsCompletedViewModel
//import com.organizers.ondemand.ui.dashboard.pastProject.ProjectPending.ProjectsPendingViewModel
//import com.organizers.ondemand.ui.dashboard.pastProject.ProjectsProgress.ProjectsProgressViewModel
//import com.organizers.ondemand.ui.dashboard.pastProject.pager.ProjectsContainerViewModel
//import com.organizers.ondemand.ui.dashboard.payment.PaymentViewModel
//import com.organizers.ondemand.ui.dashboard.pickOrganizer.PickOrganizerViewModel
//import com.organizers.ondemand.ui.dashboard.projectDetail.ProjectDetailFragment
//import com.organizers.ondemand.ui.dashboard.projectDetail.ProjectDetailsViewModel
//import com.organizers.ondemand.ui.dashboard.requestdetails.RequestDetailsViewModel
//import com.organizers.ondemand.ui.dashboard.subcategoryfragment.SubCategoriesViewModel
//import com.organizers.ondemand.ui.dashboard.tracking.TrackingViewModel
//import com.organizers.ondemand.ui.dashboard.userprofile.ProfessionalProfileViewModel
//import com.organizers.ondemand.ui.dashboard.userprofile.UserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module/*(includes = [DealDetailsChildFragmentViewModel::class])*/
abstract class FragmentViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeamViewModel::class)
    abstract fun bindTeamViewModel(teamViewModel: TeamViewModel): ViewModel



}