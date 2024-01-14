package com.example.trainticketsystem_hashmapbeatstherest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class TicketDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        Intent intent = getIntent();

        String ticketID = intent.getStringExtra("ticketId");

        TextView tvTicketId =findViewById(R.id.TicketDetail_tv_ticketId);
        ImageView ivQrCode = findViewById(R.id.TicketDetail_iv_qrcode);

        tvTicketId.setText(ticketID);

        MultiFormatWriter mWriter = new MultiFormatWriter();

        try {
            //BitMatrix class to encode entered text and set Width & Height
            BitMatrix mMatrix = mWriter.encode(ticketID, BarcodeFormat.QR_CODE, 400,400);
            BarcodeEncoder mEncoder = new BarcodeEncoder();
            Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
            ivQrCode.setImageBitmap(mBitmap);//Setting generated QR code to imageView

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}