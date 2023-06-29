package com.tasks.finger_print_manager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.tasks.navigationcomponent.databinding.ActivityDownloadManagerScreenBinding;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class FingerPrintManagerActivity extends AppCompatActivity {
    private static final String TAG = "DownloadManagerTAG";


    ActivityDownloadManagerScreenBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDownloadManagerScreenBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnClicking.setOnClickListener(v->{
            try {
                downLoad();
            }catch (Exception e){
                e.printStackTrace();
                Log.d(TAG, "onCreate: "+e.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void downLoad() throws Exception {

        FingerPrintHandler  fingerprintHandler = new FingerPrintHandler(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            //start anim
            binding.lottieFailed.setVisibility(View.GONE);
            binding.lottieSuccess.setVisibility(View.GONE);
binding.imgFinger.setVisibility(View.VISIBLE);


            fingerprintHandler.setOnAuthenticationListener(new FingerprintManager.AuthenticationCallback() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    Toast.makeText(FingerPrintManagerActivity.this,
                            "Authentication error\n" + "Error code" + errorCode + "\nError String" + errString,
                            Toast.LENGTH_LONG).show();
                 }

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                     Toast.makeText(FingerPrintManagerActivity.this,
                            "Authentication help\n" + helpString,
                            Toast.LENGTH_LONG).show();

                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    authSuccess();
                      Toast.makeText(FingerPrintManagerActivity.this,
                            "Authentication succeeded.",
                            Toast.LENGTH_LONG).show();

                 }

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onAuthenticationFailed() {
                    authFailed();

                    Toast.makeText(FingerPrintManagerActivity.this,
                            "Authentication failed.",
                            Toast.LENGTH_LONG).show();



                }
            });

        }
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);

        String keyAlias = "my_key";

        KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(keyAlias, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setUserAuthenticationRequired(true)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .build();

        keyGenerator.init(keyGenParameterSpec);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                + KeyProperties.BLOCK_MODE_CBC +
                "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);



        fingerprintHandler.startListening(cryptoObject);


    }

    private void authSuccess() {
        binding.lottieFailed.setVisibility(View.GONE);
        binding.lottieSuccess.setVisibility(View.VISIBLE);
        binding.lottieSuccess.playAnimation();
        binding.imgFinger.setVisibility(View.GONE);


    }
    private void authFailed() {
        binding.lottieFailed.setVisibility(View.VISIBLE);
        binding.lottieSuccess.setVisibility(View.GONE);
        binding.lottieFailed.playAnimation();
        binding.imgFinger.setVisibility(View.GONE);

    }


}

