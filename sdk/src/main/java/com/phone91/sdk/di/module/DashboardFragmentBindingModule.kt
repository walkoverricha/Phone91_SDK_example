package com.phone91.sdk.di.module



import com.phone91.sdk.mvvm.dashboard.home.HomeFragment
import com.phone91.sdk.mvvm.dashboard.teams.TeamFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class DashboardFragmentBindingModule {




    @ContributesAndroidInjector
    abstract fun teamFragment(): TeamFragment


    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment



}