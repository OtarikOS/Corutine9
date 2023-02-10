package com.koshkin.corutine9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var formatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
    private val scope = CoroutineScope(Job())
    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.button).setOnClickListener {
            onRun()
        }
        findViewById<View>(R.id.button2).setOnClickListener {
            onCancel()
        }
    }

    private fun onCancel() {
        log("onCancel")
        job.cancel()
    }

    private fun onRun() {
        log("onRun start")

         job=scope.launch {
            log("Coroutine start")
                var x=0
             while (x<5 && isActive){
                 delay(1000)
      //      TimeUnit.MILLISECONDS.sleep(2000)
             log("coroutine, x=${x++}, isActive:${isActive}")
             }
            log("coroutine end")
        }

//        log("onRun midl")
//
//        scope.launch {
//            log("coroutine2 start")
//            TimeUnit.MILLISECONDS.sleep(2500)
//            log("coroutine2 end")
//        }

        log("onRun end")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        scope.cancel()
    }

    private fun log(text:String){
        Log.i("TAG","${formatter.format(Date())} $text [${Thread.currentThread().name}]")
    }
}