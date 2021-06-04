package com.rishiraj.chandiguide;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;

public class API extends AppCompatActivity {

    ImageView image;
    TextView title, desc, address;

    int pos = RecyclerRowAdapter.post;

    ArrayList<model> arrData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_p_i);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E53935")));

        image = findViewById(R.id.img);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        address = findViewById(R.id.address);

        DatabaseConn databaseConn = DatabaseConn.getDB(this);

        if(!databaseConn.checkDB()){
            //for create
            databaseConn.createDB(this);
        }

        // for open
        databaseConn.openDB();

        //query execute
        arrData = databaseConn.getQues();

        /*for(int i=0;i<arrQues.size();i++){
            Log.d("Ques Data : ",arrQues.get(i).atomicNum);
        }*/

        loadQues();

        getSupportActionBar().setTitle(""+arrData.get(pos).title);



    }

    private void loadQues(){

        byte[] imo = arrData.get(pos).image;
        Bitmap bmp= BitmapFactory.decodeByteArray(imo, 0 , imo.length);
        image.setImageBitmap(bmp);

        title.setText(arrData.get(pos).title);
        desc.setText(arrData.get(pos).desc);
        address.setText("Address : "+arrData.get(pos).address);
    }

}