package com.aphillip_study_jetpack_basics

import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.provider.MediaStore
import java.io.File
import java.util.*

abstract class ScreenShotContentObserver constructor(handler: Handler?, val context: Context) :
    ContentObserver(handler) {

    private var isFromEdit = false
    private var previousPath: String? = null


    override fun deliverSelfNotifications(): Boolean {
        return super.deliverSelfNotifications()
    }

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        var cursor: Cursor? = null
        try {
            cursor = context.getContentResolver().query(
                uri!!, arrayOf<String>(
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATA
                ), null, null, null
            )
            if (cursor != null && cursor.moveToLast()) {
                val displayNameColumnIndex: Int =
                    cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
                val dataColumnIndex: Int = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                val fileName: String = cursor.getString(displayNameColumnIndex)
                val path: String = cursor.getString(dataColumnIndex)
                if (File(path).lastModified() >= System.currentTimeMillis() - 10000) {
                    if (isScreenshot(path) && !isFromEdit && !(previousPath != null && previousPath == path)) {
                        onScreenShot(path, fileName)
                    }
                    previousPath = path
                    isFromEdit = false
                } else {
                    cursor.close()
                    return
                }
            }
        } catch (t: Throwable) {
            isFromEdit = true
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
        super.onChange(selfChange, uri)
    }

    private fun isScreenshot(path: String?): Boolean {
        return path != null && path.lowercase(Locale.getDefault()).contains("screenshot")
    }

    protected abstract fun onScreenShot(path: String?, fileName: String?)

}