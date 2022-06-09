package com.chernorotov.services

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast

class HelloService : Service() {

    private lateinit var serviceLooper: Looper
    private lateinit var serviceHandler: Handler

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
            Log.d(TAG, "handleMessage: ${msg.arg1}")
            stopSelf(msg.arg1)
        }

    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: $startId")
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()

        serviceHandler.obtainMessage().also { msg ->
            msg.arg1 = startId
            serviceHandler.sendMessage(msg)
        }

        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "MyHelloService"
    }
}