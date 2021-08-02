package com.example.youranimelist.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.youranimelist.R;
import com.example.youranimelist.firebase.FirebaseList;
import com.example.youranimelist.fragment.AnimesFragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profile_image;
    ImageView glideImage;
    TextView Username;
    Button change_image;
    int SELECT_IMAGE_CODE = 1;
    Button changeUsername;
    EditText usernameEditText;
    String FILE_NAME="file_name";
    Button list1Btn;
    int REQUEST_CODE=1;
    Button list2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //glide
        glideImage = findViewById(R.id.glideImage);
        Glide.with(this)
                .load("https://static.wikia.nocookie.net/gensin-impact/images/1/1b/Character_Paimon_Portrait.png/revision/latest?cb=20201205191049")
                .into(glideImage);


        list2Btn=findViewById(R.id.List2Btn);
        list2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,AnimesFragmentActivity.class);
                startActivity(intent);
            }
        });

        list2Btn=findViewById(R.id.List2Btn);
        list2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,AnimesFragmentActivity.class);
                startActivity(intent);
            }
        });


        list1Btn=findViewById(R.id.List1Btn);
        list1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, FirebaseList.class)); }
        });
//changing username
        changeUsername = findViewById(R.id.changeUsername);
        usernameEditText=findViewById(R.id.usernameEditText);
        Username = findViewById(R.id.username);

        changeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
//changing Image
        profile_image = findViewById(R.id.ProfileImage);
        change_image = findViewById(R.id.change_image);

        change_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CAMERA) +
                        ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
                } else {
                    openDialog();
                }
            }
        });
        load();
    }
    //changing username
    public void save() {
        String text = usernameEditText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            usernameEditText.getText().clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        load();
    }

    public void load() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                System.err.println("Not Null: " + text);
                sb.append(text).append("\n");
            }
            if (sb.toString().isEmpty()) {
                return;
            }
            Username.setText("Welcome " + sb.toString() + "! what would you like to do today?");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void openDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.permissionText));
        builder.setTitle(getResources().getString(R.string.PermissionTitle));
        builder.setCancelable(false);
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                requestPermission();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }


    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CAMERA) +
            ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            //when permission not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this, Manifest.permission.CAMERA) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))){
                //Create AlertDialog
                AlertDialog.Builder builder= new AlertDialog.Builder(
                        ProfileActivity.this);
                builder.setTitle("Grant those permission");
                builder.setMessage("");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(ProfileActivity.this,
                                new String[]{
                                        Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);

                    }
                });
            builder.setNegativeButton("Cancel",null);
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            } else {
                ActivityCompat.requestPermissions(ProfileActivity.this,
                        new String[]{
                                Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);


            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Uri uri = data.getData();
            profile_image.setImageURI(uri);
        }
    }




}