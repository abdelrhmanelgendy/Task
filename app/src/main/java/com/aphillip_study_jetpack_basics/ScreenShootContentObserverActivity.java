package com.aphillip_study_jetpack_basics;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.tasks.navigationcomponent.R;
import com.tasks.navigationcomponent.databinding.ActivityScreenShootContentObserverBinding;

import java.io.File;

public class ScreenShootContentObserverActivity extends AppCompatActivity {
    private ScreenShotContentObserver screenShotContentObserver;

ActivityScreenShootContentObserverBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityScreenShootContentObserverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HandlerThread handlerThread = new HandlerThread("content_observer");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        screenShotContentObserver = new ScreenShotContentObserver(handler, this) {
            @Override
            protected void onScreenShot(String path, String fileName) {

                Bitmap bitmap = BitmapFactory.decodeFile(path);
                binding.imageView.setImageBitmap(bitmap);

                Log.d("TAG", "onScreenShot: "+path+" "+fileName);
                File file = new File(path); //this is the file of screenshot image
            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();

        getContentResolver().registerContentObserver(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                true,
                screenShotContentObserver
        );
    }

    @Override
    public void onPause() {
        super.onPause();

        try {
            getContentResolver().unregisterContentObserver(screenShotContentObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            getContentResolver().unregisterContentObserver(screenShotContentObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}