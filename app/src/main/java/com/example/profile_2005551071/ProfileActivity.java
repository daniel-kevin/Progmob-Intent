package com.example.profile_2005551071;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class ProfileActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_GET = 1;
    private ImageView imageView;
    private Button buttonSubmit;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.imgProfile);
        imageView.setOnClickListener(imageViewClickListener);

        buttonSubmit = findViewById(R.id.btnSubmit);
        buttonSubmit.setOnClickListener(buttonSubmitClickListener);

        EditText textNama = findViewById(R.id.editTextNama);
        Intent intent = getIntent();
        Profile profile = intent.getParcelableExtra("nama");
        byte[] byteArray = intent.getByteArrayExtra("image");

        if(profile != null){
            String nama = profile.getNama();
            System.out.println(nama);
            textNama.setText(nama);
        }
        if(byteArray != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageView.setImageBitmap(bitmap);
        }
    }
    private View.OnClickListener imageViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE_GET);
//            System.out.println("test");
        }
    };
    private View.OnClickListener buttonSubmitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textNama = findViewById(R.id.editTextNama);
            String StextNama = textNama.getText().toString();
            Drawable drawable = imageView.getDrawable();
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.putExtra("textNama",StextNama);
            intent.putExtra("image", byteArray);
            startActivity(intent);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}
