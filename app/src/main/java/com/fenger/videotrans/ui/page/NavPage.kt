package com.fenger.videotrans.ui.page

import androidx.compose.foundation.background
import androidx.compose.material.BottomDrawer
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomDrawerState
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
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

/**
 * @author fengerzhang
 * @date 2022/3/1 18:03
 */
@ExperimentalMaterialApi
@ExperimentalMaterialNavigationApi
@Composable
fun NavPage() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
    var homeIndex = remember { 0 }

    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
        NavHost(
            modifier = Modifier.background(VideoTransTheme.colors.background),
            navController = navCtrl,
            startDestination = RouteName.Main
        ) {

            // 相机页面
            composable(route = RouteName.Main) {
                CameraPage(navCtrl, scaffoldState, homeIndex, onImageCaptured = { photoUri -> navCtrl.navigate(RouteName.Second)})
            }

            // 二级页面
            composable(route = RouteName.Second) {
//                SecondPage(navCtrl, scaffoldState, homeIndex) { homeIndex = it }
                val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
                BottomDrawer(
                    drawerState = drawerState,
                    drawerContent = {

                    }
                ) {
                    Text(text = "这是相册页面")
                }
            }

            // 相册页面

        }
    }
}