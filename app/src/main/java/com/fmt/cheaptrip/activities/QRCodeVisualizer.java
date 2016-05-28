package com.fmt.cheaptrip.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.fmt.cheaptrip.R;

import net.glxn.qrgen.android.QRCode;

/**
 * Created by Miguel on 28/05/16.
 */

public class QRCodeVisualizer extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_visualizer);



    }
}
