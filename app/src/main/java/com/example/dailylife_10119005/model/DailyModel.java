package com.example.dailylife_10119005.model;

//NIM : 10119005
//Nama : Hayin Ananta
//Kelas : IF-1

public class DailyModel {
    private String id,kategori,judul,konten,tanggal;

    public DailyModel(String id,String kategori, String judul, String konten, String tanggal) {
        this.id = id;
        this.kategori = kategori;
        this.judul = judul;
        this.konten = konten;
        this.tanggal = tanggal;

    }


    public String getId() {
        return id;
    }

    public String getKategori() {
        return kategori;
    }

    public String getJudul() {
        return judul;
    }

    public String getKonten() {
        return konten;
    }

    public String getTanggal() {
        return tanggal;
    }
}
