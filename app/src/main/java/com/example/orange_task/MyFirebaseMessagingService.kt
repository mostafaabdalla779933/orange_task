package com.example.orange_task

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.format.Time
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.res.ResourcesCompat
import com.example.list.ui.host.HostActivity
import com.example.orange_task.MostafaActivity.Companion.DEEP
import com.example.orange_task.MostafaActivity.Companion.LINK
import com.example.orange_task.MostafaActivity.Companion.TYPE
import com.example.orange_task.MostafaActivity.Companion.WEB
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService :FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i("main", "onMessageReceived: ${message.data}")
        Log.i("main", "onMessageReceived: ${message.notification?.body}")


        val intent  = Intent(this,HostActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


        val link: String?=if(!message.data[DEEP].isNullOrEmpty()){
            intent.putExtra(TYPE,DEEP)
            message.data[DEEP]
        }else{
            intent.putExtra(TYPE,WEB)
            message.data[WEB]
        }
        intent.putExtra(LINK,link)

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(browserIntent)

      //  startActivity(intent)



       // sendNotification(this,"mostafa")
       // showMessage()

    }


    private fun showMessage(){
       val notification = NotificationCompat.Builder(this,"movieTask")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("mostafa")
            .setContentText("alaa")
            .setAutoCancel(false)
        val manager = NotificationManagerCompat.from(this)
        manager.notify(190,notification.build())

    }


    fun sendNotification(context: Context, msg: String) {
        val channelId = context.getString(R.string.app_name)
        initNotificationManager(context, channelId, msg)
    }

    private fun initNotificationManager(context: Context, channelId: String, msg: String) {
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, channelId,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val contentIntent: PendingIntent = getPendingIntent(context, msg)
        notificationManager.notify(1099,
            buildNotification(context, channelId, msg, contentIntent)
        )
    }

    private fun getPendingIntent(
        context: Context,
        msg: String
    ): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("ahmed", msg)
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)
    }

    private fun buildNotification(
        context: Context,
        channelId: String,
        msg: String,
        contentIntent: PendingIntent
    ): Notification? {
        val mBuilder = NotificationCompat.Builder(context, channelId)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(R.drawable.ic_launcher_background)
            mBuilder.color = ResourcesCompat.getColor(
                context.resources,
                R.color.black,
                null
            )
        } else mBuilder.setSmallIcon(R.drawable.ic_launcher_background)

//        mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
//                R.drawable.ico_my_orange_notification_color));
        mBuilder.setContentTitle(context.getString(R.string.app_name))
        mBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(msg))
        mBuilder.setContentText(msg)
        mBuilder.setAutoCancel(true)
        val time = Time()
        time.setToNow()
        mBuilder.setWhen(time.toMillis(true))
        mBuilder.setContentIntent(contentIntent)
        return mBuilder.build()
    }



}