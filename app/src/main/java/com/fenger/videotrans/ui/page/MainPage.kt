package com.fenger.videotrans.ui.page

import androidx.compose.foundation.background
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fenger.videotrans.ui.route.RouteName
import com.fenger.videotrans.ui.theme.VideoTransTheme

/**
 * @author fengerzhang
 * @date 2022/2/25 16:03
 */

@Composable
fun MainPage() {
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
    var homeIndex = remember { 0 }

//    NavHost(
//        modifier = Modifier.background(VideoTransTheme.colors.background),
//        navController = navCtrl,
//        startDestination = RouteName.Main
//    ) {
//
//        // 二级页面
//        composable(route = RouteName.Second) {
//            SecondPage(navCtrl, scaffoldState, homeIndex) { homeIndex = it }
//        }
//    }


    // 实际的相机
    CameraView({uri, b ->  }, {})

}