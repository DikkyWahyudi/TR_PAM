package com.example.retailnft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.HashMap;

public class activity_aset extends AppCompatActivity {
    private ListView lv;
    private ListAdapter adapter;
    SimpleAdapter Adapter;
    HashMap<String, String> map;
    int [] image = {R.drawable.header,R.drawable.header_1,R.drawable.header2};
    CarouselView carouselView;
    ArrayList<HashMap<String, String>> mylist;
    private FirebaseAuth auth;
    FirebaseDatabase getDatabase;
    DatabaseReference getReference;
    String GetProdukID;
    int [] img_indeks;
    public static String [] image_aset = {Integer.toString(R.drawable.header),Integer.toString(R.drawable.header_1),
            Integer.toString(R.drawable.header2), Integer.toString(R.drawable.header),Integer.toString(R.drawable.header_1),
            Integer.toString(R.drawable.header2), Integer.toString(R.drawable.header),Integer.toString(R.drawable.header_1),
            Integer.toString(R.drawable.header2),Integer.toString(R.drawable.header)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aset);

        carouselView = findViewById(R.id.cars_view);
        carouselView.setPageCount(image.length);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(image[position]);
            }
        };

        carouselView.setImageListener(imageListener);

        lv = findViewById(R.id.list_aset);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        //GetProdukID = getReference.child();
        getDatabase = FirebaseDatabase.getInstance();
        getReference = getDatabase.getReference();

        mylist = new ArrayList<HashMap<String,String>>();

        getReference.child("item").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mylist = new ArrayList<HashMap<String, String>>();
                ModelProduk mp = dataSnapshot.getValue(ModelProduk.class);

                map.put("nama_item", mp.getNama_produk());
                map.put("harga_item", mp.getHarga_produk());
                map.put("img_view", image_aset[mp.getImg_indeks()]);
                mylist.add(map);
                Adapter = new SimpleAdapter(getApplicationContext(), mylist, R.layout.list_aset, new String[]
                        {"nama_item","harga_item","img_view"}, new int[] {R.id.tv_nama, R.id.tv_ltn, R.id.imV});
                lv.setAdapter(Adapter);
            }
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}