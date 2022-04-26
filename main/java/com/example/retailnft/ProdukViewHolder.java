package com.example.retailnft;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProdukViewHolder extends RecyclerView.ViewHolder{
    TextView tv_nama,tv_ltn,owner;
    ImageView imV;
    public ProdukViewHolder(View itemView) {
        super(itemView);
        tv_nama = itemView.findViewById(R.id.tv_nama);
        tv_ltn = itemView.findViewById(R.id.tv_ltn);
        owner = itemView.findViewById(R.id.pemilik);
        imV = itemView.findViewById(R.id.imV);
    }
    public void bindToProduk (ModelProduk produk, View.OnClickListener onClickListener) {
        tv_nama.setText(produk.getNama_produk());
        tv_ltn.setText(produk.getHarga_produk());
        imV.setImageResource(OpeningActivity.image_grid[produk.getImg_indeks()]);
        owner.setText(produk.getPemilik_sekarang());
    }
}
