package com.example.orange_task

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Time
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.res.ResourcesCompat
import com.example.list.ui.host.HostActivity
import com.example.list.uitest.FirstTestActivity
import com.example.orange_task.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseToken()
        startActivity(Intent(this, HostActivity::class.java))
       // startActivity(Intent(this, FirstTestActivity::class.java))
        finish()

    }

    private fun firebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {task ->
            if (task.isSuccessful){
                Log.i("main", "firebaseToken: ${task.result}")
            }else{
                Log.i("main", "firebaseToken: fail2")
            }
        }

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
            .addOnFailureListener { e->
                Log.i("main", "firebaseToken: fail1 ${e.message}")
            }.addOnCompleteListener { task ->
                var msg = "success"
                if (!task.isSuccessful) {
                   msg = "fail"
                }
                Log.i("main", "firebaseToken4: $msg")
               Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
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
    ): Notification {
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