package com.tasks.finger_print_manager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerPrintHandler {
    private static FingerprintManager.AuthenticationCallback mAuthenticationCallback;
    private static CancellationSignal mCancellationSignal;
    private static FingerprintManager mFingerprintManager;
    private   Context mContext;

    public FingerPrintHandler(Context mContext) {
        this.mContext = mContext;
        mFingerprintManager=mContext.getSystemService(FingerprintManager.class);
    }

    public void setOnAuthenticationListener(FingerprintManager.AuthenticationCallback listener) {
            mAuthenticationCallback = listener;
        }



        public void startListening(FingerprintManager.CryptoObject cryptoObject) {
            if (isFingerScannerAvailableAndSet()) {
                try {
                    mCancellationSignal = new CancellationSignal();
                    mFingerprintManager.authenticate(cryptoObject, mCancellationSignal, 0 /* flags */, mAuthenticationCallback, new Handler(Looper.getMainLooper()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopListening() {
            if (isFingerScannerAvailableAndSet()) {
                try {
                    mCancellationSignal.cancel();
                    mCancellationSignal = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean isFingerScannerAvailableAndSet() {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
                return false;
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.USE_FINGERPRINT)
                    != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(mContext, "User hasn't granted permission to use Fingerprint", Toast.LENGTH_LONG).show();
                return false;
            }
            if (mFingerprintManager == null){
                Toast.makeText(mContext,
                        "mFingerprintManager is null",
                        Toast.LENGTH_LONG).show();
                 return false;
            }
            if (!mFingerprintManager.isHardwareDetected()) {
                Toast.makeText(mContext,
                        "fingerprint hardware not present or not functional",
                        Toast.LENGTH_LONG).show();
                 return false;
            }
            if (!mFingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(mContext,
                        "no fingerprint enrolled/saved",
                        Toast.LENGTH_LONG).show();
                 return false;
            }
            return true;
        }


}
