package com.example.retailnft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AkunActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private TextView textname;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        textname = findViewById(R.id.name);
        logout = findViewById(R.id.btn_logout);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null){
            textname.setText(firebaseUser.getDisplayName());
        }/*else{
            textname.setText("Login Gagal");
        }*/

        logout.setOnClickListener(v ->{
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
    }
    public void home (View v) {
        Intent intent = new Intent(this, OpeningActivity.class);
        startActivity(intent);
    }
    public void asset (View v) {
        Intent intent = new Intent(this, activity_aset.class);
        startActivity(intent);
    }
    public void like (View v) {
        Intent intent = new Intent(this, ActivitySuka.class);
        startActivity(intent);
    }
    public void akun (View v) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null) {
            Intent intent = new Intent(this, AkunActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Belum Masuk Akun", Toast.LENGTH_SHORT).show();
        }

    }
}