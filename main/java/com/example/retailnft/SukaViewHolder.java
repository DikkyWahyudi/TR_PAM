package com.example.retailnft;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SukaViewHolder extends RecyclerView.ViewHolder {
    TextView tv_nama,tv_ltn,owner;
    ImageView imV;
    public SukaViewHolder(View itemView) {
        super(itemView);
        tv_nama = itemView.findViewById(R.id.tv_nama);
        tv_ltn = itemView.findViewById(R.id.tv_ltn);
        owner = itemView.findViewById(R.id.pemilik_suka);
        imV = itemView.findViewById(R.id.imV);
    }
    public void bindToSuka (ModelProduk produk, View.OnClickListener onClickListener) {
        tv_nama.setText(produk.getNama_produk());
        tv_ltn.setText(produk.getHarga_produk());
        imV.setImageResource(OpeningActivity.image_grid[produk.getImg_indeks()]);
        owner.setText(produk.getPemilik_sekarang());
    }
}
