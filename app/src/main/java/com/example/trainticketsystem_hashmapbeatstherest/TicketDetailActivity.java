package com.example.trainticketsystem_hashmapbeatstherest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.zxing.WriterException;
//
//import androidmads
//import androidmads.library.qrgenearator.QRGEncoder;



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

        //dependency not working
//        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
//
//        Display display = manager.getDefaultDisplay();
//
//        Point point = new Point();
//        display.getSize(point);
//
//        int width = point.x;
//        int height = point.y;
//
//        int dimen = width < height ? width : height;
//        dimen = dimen * 3 / 4;
//
//        qrgEncoder = new QRGEncoder(dataEdt.getText().toString(), null, QRGContents.Type.TEXT, dimen);
//        try {
//            // getting our qrcode in the form of bitmap.
//            bitmap = qrgEncoder.encodeAsBitmap();
//            // the bitmap is set inside our image
//            // view using .setimagebitmap method.
//            qrCodeIV.setImageBitmap(bitmap);
//        } catch (WriterException e) {
//            // this method is called for
//            // exception handling.
//            Log.e("Tag", e.toString());
//        }
    }
}