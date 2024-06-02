package com.daniel.advweek4

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

import android.content.Context
import android.os.Build.VERSION_CODES


fun createNotifChannel(context: Context, priority:Int, showBadge:Boolean, description:String){
    if(Build.VERSION.SDK_INT >= VERSION_CODES.O){
        val name = context.getString(R.string.app_name) // "AdvWeek4"
        val channelID = "${context.packageName}-${name}"

        val channel = NotificationChannel(channelID, name, priority)
        channel.description = description
        channel.setShowBadge(showBadge)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}