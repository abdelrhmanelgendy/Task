package com.tasks.phillip_worker_manager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

class PhotoCompressionWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    private   val TAG = "PhotoCompressionWorker"
    override suspend fun doWork(): Result {

        val sharedImageUri = inputData.getString(KEY_CONTENT_URI)
        val maxSize = inputData.getLong(KEY_COMPRESSION_MAX_SIZE, 0)
        return withContext(Dispatchers.IO) {


            val uri = Uri.parse(sharedImageUri)
            val bytes = context.contentResolver.openInputStream(uri)?.use {
                it.readBytes()
            }?:return@withContext Result.failure()


           val originalBitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.size)

            var outputBytes :ByteArray
            var quality =100
            do {
                val outputStream =ByteArrayOutputStream()
                outputStream.use {
                    originalBitmap.compress(Bitmap.CompressFormat.JPEG,quality,it)
                    outputBytes=it.toByteArray()
                    quality-=10

                    Log.d(TAG, "doWork: Size"+outputBytes.size+" quality "+quality)
                }
            }while (outputBytes.size>maxSize&&quality>=4)


            val file =File(context.cacheDir,"${workerParameters.id}.jpeg")
            file.writeBytes(outputBytes)
            Log.d(TAG, "doWork: Size Success"+outputBytes.size)

            Result.success(
                workDataOf(
                    KEY_RESULT_PATH to file.absolutePath
                )
            )

        }
    }

    companion object {
        const val KEY_CONTENT_URI = "KEY_CONTENT_URI"
        const val KEY_COMPRESSION_MAX_SIZE = "KEY_COMPRESSION_THRESHOLD"
        const val KEY_RESULT_PATH = "KEY_RESULT_PATH"
    }
}