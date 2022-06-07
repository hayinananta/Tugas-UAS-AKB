package com.example.dailylife_10119005.service;

//NIM : 10119005
//Nama : Hayin Ananta
//Kelas : IF-1

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.dailylife_10119005.model.DailyModel;

public class DailyService extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "dailydb.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "daily";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_KATEGORI = "daily_kategori";
    private static final String COLUMN_JUDUL = "daily_judul";
    private static final String COLUMN_KONTEN = "daily_konten";
    private static final String COLUMN_TANGGAL = "daily_tanggal";

    public DailyService(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_KATEGORI + " TEXT, " +
                COLUMN_JUDUL + " TEXT, " +
                COLUMN_KONTEN + " TEXT, " +
                COLUMN_TANGGAL + " TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addDaily(DailyModel dailyModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_JUDUL, dailyModel.getJudul());
        cv.put(COLUMN_KONTEN, dailyModel.getKonten());
        cv.put(COLUMN_KATEGORI, dailyModel.getKategori());
        cv.put(COLUMN_TANGGAL, dailyModel.getTanggal());

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Berhasil Menambahkan Database", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(DailyModel dailyModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String id = dailyModel.getId();

        cv.put(COLUMN_JUDUL, dailyModel.getJudul());
        cv.put(COLUMN_KONTEN, dailyModel.getKonten());
        cv.put(COLUMN_KATEGORI, dailyModel.getKategori());
        cv.put(COLUMN_TANGGAL, dailyModel.getTanggal());

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteOneRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

