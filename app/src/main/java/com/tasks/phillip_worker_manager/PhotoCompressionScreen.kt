package com.tasks.phillip_worker_manager

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
import com.tasks.phillip_worker_manager.PhotoCompressionWorker.Companion.KEY_COMPRESSION_MAX_SIZE
import com.tasks.phillip_worker_manager.PhotoCompressionWorker.Companion.KEY_CONTENT_URI
import com.tasks.phillip_worker_manager.PhotoCompressionWorker.Companion.KEY_RESULT_PATH

class PhotoCompressionScreen : ComponentActivity() {

/*
        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="androidx.work.WorkManagerInitializer"
                android:value="androidx.startup"
                tools:node="remove" />
        </provider>
 */
    private lateinit var workerManager: WorkManager
    private val viewModel by viewModels<PhotoCompressionViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        workerManager = WorkManager.getInstance(this)


        setContent {
            Log.d("TAG123", "onCreate: ")
            val scrollState = rememberScrollState()
            NavigationComponentTheme() {
                val workerResult = viewModel.workerId?.let { id ->
                    workerManager.getWorkInfoByIdLiveData(id).observeAsState().value
                }


                LaunchedEffect(key1 = workerResult?.outputData) {
                    Log.d("TAG123", "onCreate: LaunchedEffect")
                    if (workerResult?.outputData != null) {
                        val filePath = workerResult.outputData.getString(
                            KEY_RESULT_PATH
                        )
                        filePath?.let {
                            val bitmap = BitmapFactory.decodeFile(it)
                            viewModel.updateCompressedBitmap(bitmap)
                        }
                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    viewModel.unCompressedUri?.let {


                        Text(text = "UnCompressed Image")
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    viewModel.compressedBitmap?.let {
                        Text(text = "Compressed Image")
                    }
                }
            }

        }

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uri: Uri =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
            } else {
                intent?.getParcelableExtra(Intent.EXTRA_STREAM)
            } ?: return

        viewModel.updateUnCompressedUri(uri)

        val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>().setInputData(
            workDataOf(
                KEY_CONTENT_URI to uri.toString(),
                KEY_COMPRESSION_MAX_SIZE to 1024 * 5L
            )
        ).setConstraints(
            Constraints(
                requiresStorageNotLow = true
            )
        ).build()

        viewModel.updateWorkId(request.id)
        val enqueue = workerManager.enqueue(request)

    }
}