package com.example.katiemishra.visionapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {​
    ImageView iv;
    ​
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);​
        iv = (ImageView) findViewById(R.id.myImageView);​
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
        Mat m = new Mat();
    }​
    static final int REQUEST_VIDEO_CAPTURE = 1;
    ​
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bmp;
        Mat m = new Mat();
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            // convert uri to mat
            try {
                bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                Utils.bitmapToMat(bmp, m);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // processing goes here​
            // mat to bitmap
            Bitmap bm = Bitmap.createBitmap(m.cols(), m.rows(),Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(m, bm);​
            iv.setImageBitmap(bm);
        }
    }
}