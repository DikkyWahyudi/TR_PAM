package com.example.retailnft;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity {
    //private TextView title1,slogan;
    //private ListAdapter adapter;
    private SimpleAdapter Adapter;
    private HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;
    String [] ktg = {"Gambar","Video","Sticker"};
    String [] gbr;
    private ListView list;
    //private ArrayAdapter<CharSequence> adapter;
    CarouselView carouselView;

    int [] image = {R.drawable.header,R.drawable.header_1,R.drawable.header2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        list = findViewById(R.id.list);

        gbr = new String[] {
                Integer.toString(R.drawable.header),
                Integer.toString(R.drawable.header),
                Integer.toString(R.drawable.header)
        };
        mylist = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < ktg.length; i++) {
            map = new HashMap<String, String>();

            map.put("ktg", ktg[i]);
            map.put("gbr", gbr[i]);
            mylist.add(map);
        }
        Adapter = new SimpleAdapter(this, mylist,R.layout.latihan,
                  new String[] {"ktg","gbr"}, new int[] {R.id.kategori, R.id.header});

        list.setAdapter(Adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < list.getChildCount(); i++) {
                    if (position == i) {
                        list.getChildAt(position).setBackgroundColor(Color.GRAY);
                    }
                    else {
                        list.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

                Toast.makeText(DashboardActivity.this, Adapter.getItem(position)+" Terpilih", Toast.LENGTH_SHORT).show();
            }
        });

        carouselView = findViewById(R.id.car_view);
        carouselView.setPageCount(image.length);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(image[position]);
            }
        };

        carouselView.setImageListener(imageListener);


//        list = findViewById(R.id.list);
//        adapter = ArrayAdapter.createFromResource(this,R.array.list_item, android.R.layout.simple_list_item_1);
//        list.setAdapter(adapter);
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                for (int i = 0; i < list.getChildCount(); i++) {
//                    if (position == i) {
//                        list.getChildAt(position).setBackgroundColor(Color.GRAY);
//                    }
//                    else {
//                        list.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
//                    }
//                }
//
//                Toast.makeText(DashboardActivity.this, adapter.getItem(position)+" Terpilih", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

}