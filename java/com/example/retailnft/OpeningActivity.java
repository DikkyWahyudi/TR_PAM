package com.example.retailnft;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.HashMap;

public class OpeningActivity extends AppCompatActivity {
    GridView gridView;
    CarouselView carouselView;
    int [] image = {R.drawable.item1,R.drawable.item4,R.drawable.item6};
    public static int [] image_grid = {R.drawable.item1,R.drawable.item2,R.drawable.item3,
                         R.drawable.item4,R.drawable.item5,R.drawable.item6,
                         R.drawable.item7,R.drawable.item8,R.drawable.header,R.drawable.header2};
    public String [] text = {"Beauty Snow","Afternoon Beach","Rain Forest","Sunset Lake","Invoguration","Black Dragon",
                      "Child Snow","Snow Village","Shopping Day","Old Man"};
    public String [] price = {"50","40","30","60","45","35","32","27","43","75"};
    ImageView imageViewAndroid;
    TextView title,harga;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        carouselView = findViewById(R.id.cars_view);
        carouselView.setPageCount(image.length);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(image[position]);
            }
        };

        carouselView.setImageListener(imageListener);

        GridViewCustom gridViewCustom = new GridViewCustom(OpeningActivity.this, image_grid,text);
        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(gridViewCustom);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < gridView.getChildCount(); i++) {
                    if (position == i) {
                        String nama = text[position];
                        String harga = price[position];
                        int img = position;
                        Intent intent = new Intent(getApplicationContext(), ProdukActivity.class);
                        intent.putExtra("item", nama);
                        intent.putExtra("harga", harga);
                        intent.putExtra("img", img);
                        startActivity(intent);
                    }
                }
            }
        });

    }
    public class GridViewCustom extends BaseAdapter {
        private Context mContext;
        private final int [] gridView_Image;
        private final String [] gridView_Text;


        public GridViewCustom (Context context, int[] gridViewGambar, String [] gridViewTeks) {
            mContext = context;
            this.gridView_Image = gridViewGambar;
            this.gridView_Text = gridViewTeks;
        }

        @Override
        public int getCount() {
            return gridView_Text.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("InflateParams")
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View gridViewAndroid;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                gridViewAndroid = inflater.inflate(R.layout.grid_layout, null);
                imageViewAndroid = gridViewAndroid.findViewById(R.id.grid_image);
                title = gridViewAndroid.findViewById(R.id.item_name);
                harga = gridViewAndroid.findViewById(R.id.harga);
                imageViewAndroid.setImageResource(gridView_Image[i]);
                title.setText(gridView_Text[i]);
                harga.setText(price[i]);
            }
            else {
                gridViewAndroid = convertView;
            }

            return gridViewAndroid;
        }
    }
    public void hamburger (View view) {
        PopupMenu pop = new PopupMenu(this,view);
        pop.inflate(R.menu.pop_up);
        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        Intent intent = new Intent(getApplicationContext(),OpeningActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_akun:
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if(firebaseUser != null) {
                            Intent intent_akun = new Intent(getApplicationContext(),AkunActivity.class);
                            startActivity(intent_akun);
                            //Toast.makeText(getApplicationContext(), "Masuk Akun", Toast.LENGTH_SHORT).show();
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

    public void beranda (View v) {
        Intent intent = new Intent(this, OpeningActivity.class);
        startActivity(intent);
    }
    public void aset (View v) {
        Intent intent = new Intent(this, activity_aset.class);
        startActivity(intent);
    }
    public void suka (View v) {
        Intent intent = new Intent(this, ActivitySuka.class);
        startActivity(intent);
    }
    public void akun (View v) {
        Intent intent = new Intent(this, AkunActivity.class);
        startActivity(intent);
    }



}