package com.fenger.videotrans

import android.app.Application
import com.fenger.videotrans.utils.MMKVHelper

/**
 * @author fengerzhang
 * @date 2021/8/10 12:19
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        MMKVHelper.initMMKV(this)
    }
}