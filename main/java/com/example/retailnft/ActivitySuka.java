package com.example.retailnft;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ActivitySuka extends AppCompatActivity {
    int [] image = {R.drawable.item1,R.drawable.item4,R.drawable.item6};
    public String [] array_pemilik = {"Tidak Ada","Tidak Ada","Tidak Ada","Tidak Ada","Tidak Ada","Tidak Ada","Tidak Ada","Tidak Ada","Tidak Ada","Tidak Ada"};
    CarouselView carouselView;
    DatabaseReference getInstance;
    private RecyclerView mRecycler;
    FirebaseRecyclerAdapter<ModelProduk,SukaViewHolder> mAdapter;
    LinearLayoutManager mManager;
    private FirebaseUser firebaseUser;
    TextView owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suka);

        carouselView = findViewById(R.id.cars_view);
        carouselView.setPageCount(image.length);
        owner = findViewById(R.id.pemilik_suka);
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(image[position]);
            }
        };
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        //getReference = FirebaseDatabase.getInstance().getReference("item");
        getInstance = mFirebaseInstance.getReference("suka");
        carouselView.setImageListener(imageListener);

        mRecycler = findViewById(R.id.list_suka);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query query = getQuery(getInstance);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<ModelProduk>()
                .setQuery(query, ModelProduk.class).build();

        mAdapter = new FirebaseRecyclerAdapter<ModelProduk, SukaViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SukaViewHolder holder, int position, @NonNull ModelProduk model) {
                holder.bindToSuka(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }

            @NonNull
            @Override
            public SukaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new SukaViewHolder(inflater.inflate(R.layout.list_suka,parent,false));
            }
        };
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase) {
        Query query = mDatabase;
        return query;
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
                        break;
                    case R.id.menu_akun:
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if(firebaseUser != null) {
                            Intent intent_akun = new Intent(getApplicationContext(),AkunActivity.class);
                            startActivity(intent_akun);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Belum Masuk Akun", Toast.LENGTH_SHORT).show();
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