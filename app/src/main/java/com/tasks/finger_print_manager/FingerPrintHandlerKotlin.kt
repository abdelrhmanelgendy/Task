package com.tasks.finger_print_manager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.flow.flow


class FingerPrintHandlerKotlin {




    fun isFingerScannerAvailableAndSet(
        context: Context,
        fingerManager: FingerprintManager
    ) = flow {

//size of an array


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            emit(FingerPrintHandlerState.Error("Lower Api Level"))
            return@flow
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT)
            != PackageManager.PERMISSION_GRANTED
        ) {
            emit(FingerPrintHandlerState.Error("User hasn't granted permission to use Fingerprint"))
        }
        if (!fingerManager.isHardwareDetected()) {
            emit(FingerPrintHandlerState.Error("No Finger Print"))
        }
        if (!fingerManager.hasEnrolledFingerprints()) {
            emit(FingerPrintHandlerState.Error("No Finger Print"))

        } else emit(FingerPrintHandlerState.Success())
    }

}