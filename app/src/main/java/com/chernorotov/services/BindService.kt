package com.chernorotov.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class BindService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }
    
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: ")
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
    
    companion object {
        const val TAG = "MySimpleService"
    }
    
}