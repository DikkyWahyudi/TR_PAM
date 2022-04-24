package com.example.retailnft;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ModelProduk {
    private String nama_produk;
    private String harga_produk;
    private String pemilik_sekarang;
    //private String pemilik_sebelum;
    private int img_indeks;

    public ModelProduk () {

    }
    public ModelProduk (String produk, String harga, int indeks,String pemilik_skg/*,String pemilik_sbm*/) {
        this.nama_produk = produk;
        this.harga_produk = harga;
        this.img_indeks = indeks;
        this.pemilik_sekarang = pemilik_skg;
        //this.pemilik_sebelum = pemilik_sbm;

    }

    public String getNama_produk() {
        return nama_produk;
    }

    public String getHarga_produk() {
        return harga_produk;
    }

    public int getImg_indeks() {
        return img_indeks;
    }
    public String getPemilik_sekarang() {
        return pemilik_sekarang;
    }

    /*public String getPemilik_sebelum() {
        return pemilik_sebelum;
    }*/


}
