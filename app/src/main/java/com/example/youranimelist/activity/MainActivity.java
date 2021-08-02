package com.example.youranimelist.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.youranimelist.R;
import com.example.youranimelist.objects.Anime;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private static final int SIGN_IN =1;
    String KEY="key";
    public static final int PRIMARY_FOREGROUND_NOTIF_SERVICE_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean log_in = prefs.getBoolean(KEY, false);
        if (log_in){startActivity(new Intent(MainActivity.this, ProfileActivity.class));}

        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        signInButton=findViewById(R.id.google_sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SIGN_IN){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(KEY, true);
                editor.apply();

                SendNotification();

                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Login failed!",Toast.LENGTH_SHORT).show();
            }

        }

    }

    void SendNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String id = "_channel_01";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(id, "notification", importance);
            mChannel.enableLights(true);

            Notification notification = new Notification.Builder(getApplicationContext(), id)
                    .setSmallIcon(R.drawable.ic_baseline_android_24)
                    .setContentTitle("Login was successful!")
                    .setContentText("Enjoy your time with our app")
                    .build();

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel);
                mNotificationManager.notify(PRIMARY_FOREGROUND_NOTIF_SERVICE_ID, notification);
            }
        }
    }


}