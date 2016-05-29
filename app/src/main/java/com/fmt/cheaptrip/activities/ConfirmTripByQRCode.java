package com.fmt.cheaptrip.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.widget.Toast;

import com.fmt.cheaptrip.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Miguel on 28/05/16.
 */

public class ConfirmTripByQRCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("qrcode", rawResult.getText());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

        // Do something with the result here
        //Toast.makeText(getApplicationContext(), rawResult.getText(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}