package com.example.retailnft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class ProdukActivity extends AppCompatActivity {
    ImageView image;
    TextView nama,harga;
    String item,price;
    int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);

        nama = findViewById(R.id.value_nama_item);
        harga = findViewById(R.id.value_harga_item);
        image = findViewById(R.id.produk_view);

        Intent intent = getIntent();
        item =intent.getStringExtra("item");
        price =intent.getStringExtra("harga");
        img =intent.getIntExtra("img",-1);

        nama.setText(item);
        harga.setText(price);
        image.setImageResource(OpeningActivity.image_grid[img]);
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
                        Intent intent = new Intent(getApplicationContext(),OpeningActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Menu Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_akun:
                        Toast.makeText(getApplicationContext(),"Menu Akun", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_aset:
                        Toast.makeText(getApplicationContext(),"Menu Aset", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_wishlist:
                        Toast.makeText(getApplicationContext(),"Menu Wishlist", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_kontak:
                        Toast.makeText(getApplicationContext(),"Menu Kontak", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_about:
                        Toast.makeText(getApplicationContext(),"Menu Tentang", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        finishAffinity();
                }
                return true;
            }
        });
        pop.show();
    }
}