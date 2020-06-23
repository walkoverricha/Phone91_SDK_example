package com.phone91.sdk.routing

import android.content.Context
import com.phone91.sdk.routing.Navigator
//import com.phone91.sdk.routing.createpost.CreatePostController
//import com.mbaka.ui.routing.createpost.CreatePostRouteManager
import com.phone91.sdk.routing.dashboard.DashboardController
import com.phone91.sdk.routing.dashboard.DashboardRouteManager

import javax.inject.Inject

class NavigationController
@Inject
constructor
(val context: Context,private var dashboardRouteManager: DashboardRouteManager) : Navigator,DashboardController by dashboardRouteManager
