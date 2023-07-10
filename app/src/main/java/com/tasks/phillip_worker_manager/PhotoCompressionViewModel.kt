package com.tasks.phillip_worker_manager

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.tasks.phillip_worker_manager.PhotoCompressionWorker.Companion.KEY_COMPRESSION_MAX_SIZE
import com.tasks.phillip_worker_manager.PhotoCompressionWorker.Companion.KEY_CONTENT_URI
import java.util.UUID

class PhotoCompressionViewModel :ViewModel(){


    var unCompressedUri:Uri? by mutableStateOf(null)
    private set

    var compressedBitmap:Bitmap? by mutableStateOf(null)
    private set

    var workerId:UUID? by mutableStateOf(null)
        private set


    fun updateUnCompressedUri (uri: Uri){
        unCompressedUri=uri
    }

    fun updateCompressedBitmap(bitmap: Bitmap)
    {
        compressedBitmap=bitmap
    }

    fun updateWorkId(uuid: UUID){
        workerId=uuid
    }
}