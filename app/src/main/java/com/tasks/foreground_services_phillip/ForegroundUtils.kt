package com.tasks.foreground_services_phillip

 import android.app.Notification
 import android.app.NotificationChannel
 import android.app.NotificationManager
 import android.app.PendingIntent
 import android.content.Context
 import android.content.Intent
 import android.graphics.BitmapFactory
 import android.os.Build
 import android.support.v4.media.MediaMetadataCompat
 import android.support.v4.media.session.MediaSessionCompat
 import android.support.v4.media.session.PlaybackStateCompat
 import android.util.Log
 import androidx.annotation.RequiresApi
 import androidx.core.app.NotificationCompat
 import com.tasks.navigationcomponent.R
 import com.tasks.navigationcomponent.R.drawable.download


class ForegroundUtils {


    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(context: Context){
            val channel = NotificationChannel(
                "Media_Player_Channel",
                "Media Player",
                NotificationManager.IMPORTANCE_LOW
            )
            channel.description = "Media Player Notifications"
            val notificationManager: NotificationManager =
                context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
    }


    private  val TAG = "ForegroundServiceTAG"
    fun createNotificationBuilder(context: Context): Notification {


        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, "Media_Player_Channel")
                .setSmallIcon(com.tasks.navigationcomponent.R.drawable.ic_baseline_play_arrow_24)
                .setContentTitle("Media Player")
                .setContentText("Playing Song Name")

        val bitmap = BitmapFactory.decodeResource(context.resources, download)

        val mediaSession = MediaSessionCompat(context, "MyMediaSession")
        val metadata = MediaMetadataCompat.Builder()
            .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, "Artist name")
            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, "Song title")
            .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, bitmap)
            .build()

        mediaSession.setMetadata(metadata)

        val playbackStateBuilder = PlaybackStateCompat.Builder()
            .setActions(PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PAUSE or PlaybackStateCompat.ACTION_SKIP_TO_NEXT or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS)
            .setState(PlaybackStateCompat.STATE_PAUSED, 0, 1f)
        mediaSession.setPlaybackState(playbackStateBuilder.build())


        mediaSession.setCallback(object : MediaSessionCompat.Callback() {
            override fun onPlay() {
                Log.d(TAG, "onPlay: ")
                // Handle play action
            }

            override fun onPause() {
                Log.d(TAG, "onPause: ")
                // Handle pause action
            }

            override fun onSkipToNext() {
                Log.d(TAG, "onSkipToNext: ")
                // Handle skip to next action
            }

            override fun onSkipToPrevious() {
                Log.d(TAG, "onSkipToPrevious: ")
                // Handle skip to previous action
            }

            // Other media control actions can be handled here
        })

        mediaSession.isActive = true

        mediaSession.isActive = false
        mediaSession.release()

        val style = androidx.media.app.NotificationCompat.MediaStyle()
        style.setMediaSession(mediaSession.sessionToken)

        builder.setStyle(style);


        val playIntent = Intent(context, MediaPlayerService::class.java)
        playIntent.action = "PLAY"
        val playPendingIntent = PendingIntent.getService(context, 0, playIntent, PendingIntent.FLAG_IMMUTABLE)
        builder.addAction(
            NotificationCompat.Action(
                R.drawable.ic_baseline_play_arrow_24,
                "Play",
                playPendingIntent
            )
        )

        val pauseIntent = Intent(context, MediaPlayerService::class.java)
        pauseIntent.action = "PAUSE"
        val pausePendingIntent = PendingIntent.getService(context, 0, pauseIntent, PendingIntent.FLAG_IMMUTABLE)
        builder.addAction(
            NotificationCompat.Action(
                R.drawable.ic_baseline_pause_24,
                "Pause",
                pausePendingIntent
            )
        )


        builder.setOngoing(true)


          return builder.build()
    }


}