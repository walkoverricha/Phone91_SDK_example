package com.phone91.sdk.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.phone91.sdk.di.module.ActivityBindingModule
import com.phone91.sdk.di.module.MyApplicationModule
import com.phone91.sdk.MyApplication
import javax.inject.Singleton

/**
 * Created by CIS Dev 816 on 27/3/18.
 */

@Singleton
@Component(modules = [(MyApplicationModule::class),
    (ActivityBindingModule::class),
    (AndroidSupportInjectionModule::class)])

interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(myApplication: MyApplication)
}