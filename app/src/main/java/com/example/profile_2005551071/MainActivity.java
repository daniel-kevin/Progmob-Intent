package com.example.profile_2005551071;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Intent profileIntent;
    private Button buttonIsiProfile;
    private String nama = null;
    private boolean flag = false;

    private byte[] byteArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonIsiProfile = findViewById(R.id.buttonIsiProfile);
        profileIntent = getIntent();
        ImageView imageView = findViewById(R.id.profileImg);
        byteArray = profileIntent.getByteArrayExtra("image");
        nama = profileIntent.getStringExtra("textNama");
        if(nama != null){
            TextView textSelamat = findViewById(R.id.textSelamat);
            textSelamat.setText("Selamat Datang "+ nama);
            System.out.println(profileIntent.getStringExtra("textNama"));
            buttonIsiProfile.setText("Ubah Profile");
        }
        if(byteArray != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageView.setImageBitmap(bitmap);
        }
        if(nama != null && byteArray != null){
            flag = true;
        }
    }

    public void isiProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        if(flag){
            Profile profile = new Profile(nama);
            intent.putExtra("nama",profile);
            intent.putExtra("image", byteArray);
        }
        startActivity(intent);
    }
}