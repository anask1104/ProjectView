package com.anask1104gmail.projectview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class DetailActivity extends AppCompatActivity {


    TextView title_tv,desc_tv;
    ImageView image_iv;
    Bitmap bmp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        title_tv=(TextView) findViewById(R.id.title_tv);
        desc_tv=(TextView) findViewById(R.id.desc_tv);
        Bundle title_data = getIntent().getExtras();
        String titleString=title_data.get("title").toString();
        String desc=title_data.get("description").toString();




        if(getIntent().hasExtra("byteArray")) {
            ImageView image_iv= new ImageView(this);
            Bitmap _bitmap = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
            image_iv.setImageBitmap(_bitmap);
        }


        title_tv.setText(titleString);
        desc_tv.setText(desc);




    }
}
