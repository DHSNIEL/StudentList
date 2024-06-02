package com.daniel.advweek4.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.daniel.advweek4.R
import com.daniel.advweek4.createNotifChannel


class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null
        private const val PERMISSION_REQUEST_CODE = 1

        fun showNotif(title: String, content: String, icon: Int) {
            val channelID = "${instance?.packageName} - ${instance?.getString(R.string.app_name)}"

            val builder =
                NotificationCompat.Builder(instance!!.applicationContext, channelID).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }

            val manager = NotificationManagerCompat.from(instance!!.applicationContext)

            if (ContextCompat.checkSelfPermission(
                    instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_REQUEST_CODE
                )
                return
            }
            manager.notify(1001, builder.build())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instance = this

        createNotifChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT,
            false,
            "Notification channel for creating new student"
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), PERMISSION_REQUEST_CODE
                )
            }
        }
    }
}