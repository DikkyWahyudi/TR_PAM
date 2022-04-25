package com.example.retailnft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProdukActivity extends AppCompatActivity {
    ImageView image;
    TextView nama,harga,pemilik_sbm,value;
    private EditText pembeli;
    String item,price,ProdukID,SukaID;
    int img;
    private DatabaseReference mFirebaseDatabase,mDatabaseSuka;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);

        nama = findViewById(R.id.value_nama_item);
        harga = findViewById(R.id.value_harga_item);
        image = findViewById(R.id.produk_view);

        pembeli = findViewById(R.id.nama_pembeli);
        value = findViewById(R.id.value_owner_now);
        Intent intent = getIntent();
        item =intent.getStringExtra("item");
        price =intent.getStringExtra("harga");
        img =intent.getIntExtra("img",-1);

        nama.setText(item);
        harga.setText(price);
        image.setImageResource(OpeningActivity.image_grid[img]);
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("item");
        mDatabaseSuka = mFirebaseInstance.getReference("suka");
        //OpeningActivity oa = new OpeningActivity();

    }
    public void hamburger (View view) {
        PopupMenu pop = new PopupMenu(this,view);
        pop.inflate(R.menu.pop_up);
        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        Intent intent_home = new Intent(getApplicationContext(),OpeningActivity.class);
                        startActivity(intent_home);
                        Toast.makeText(getApplicationContext(),"Menu Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_akun:
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if(firebaseUser != null) {
                            Intent intent_akun = new Intent(getApplicationContext(),AkunActivity.class);
                            startActivity(intent_akun);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Belum Masuk", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu_aset:
                        Intent intent_aset = new Intent(getApplicationContext(),activity_aset.class);
                        startActivity(intent_aset);
                        break;
                    case R.id.menu_wishlist:
                        Intent intent_suka = new Intent(getApplicationContext(),ActivitySuka.class);
                        startActivity(intent_suka);
                        break;
                    case R.id.menu_about:
                        Intent intent_login = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent_login);
                        break;
                    default:
                        finishAffinity();
                }
                return true;
            }
        });
        pop.show();
    }
    public void btn_beli (View v) {
        String p = pembeli.getText().toString();
        if (p.length() == 0) {
            pembeli.setError("Nama Pembeli Harus Di isi");
        }
        else {
            if (TextUtils.isEmpty(ProdukID)) {
                ProdukID = mFirebaseDatabase.push().getKey();
                ModelProduk mp =new ModelProduk(item,price,img,p);
                mFirebaseDatabase.child(ProdukID).setValue(mp);
                addProdukListener();
            }
            if (!TextUtils.isEmpty(ProdukID)) {
                updateProduk(p);
            }

        }
    }
    public void addProdukListener() {
        mFirebaseDatabase.child(ProdukID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelProduk mp = dataSnapshot.getValue(ModelProduk.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "Failed to read produk", error.toException());
            }
        });
    }
    public void updateProduk (String ps) {
        mFirebaseDatabase.child(ProdukID).child("pemilik_sekarang").setValue(ps);
    }
    public void btn_suka (View v) {
        String p = value.getText().toString();

        if (TextUtils.isEmpty(SukaID)) {
            SukaID = mDatabaseSuka.push().getKey();
            ModelProduk mp =new ModelProduk(item,price,img,p);
            mDatabaseSuka.child(SukaID).setValue(mp);
            addSukaListener();
        }

    }
    public void addSukaListener () {
        mDatabaseSuka.child(SukaID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelProduk mp = dataSnapshot.getValue(ModelProduk.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "Failed to read produk", error.toException());
            }
        });
    }
}