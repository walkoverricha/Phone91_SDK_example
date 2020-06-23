package com.phone91.sdk.mvvm.dashboard

import com.phone91.sdk.data.AppDataManager
import com.phone91.sdk.mvvm.dashboard.DashboardActivityVM
import dagger.Module
import dagger.Provides

/**
 * Created by CIS Dev 816 on 4/4/18.
 */
@Module
class DashboardActivityModule {

    @ActivityScoped
    @Provides
    fun provideDashboardActivityViewModel(appDataManager: AppDataManager):
            DashboardActivityVM = DashboardActivityVM(appDataManager)

    annotation class ActivityScoped
}