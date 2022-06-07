package com.example.dailylife_10119005.ui.daily;

//NIM : 10119005
//Nama : Hayin Ananta
//Kelas : IF-1

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dailylife_10119005.MainActivity;
import com.example.dailylife_10119005.R;
import com.example.dailylife_10119005.model.DailyModel;
import com.example.dailylife_10119005.service.DailyService;

public class UpdateDailyActivity extends AppCompatActivity {

    EditText judul_update, kategori_update, konten_update;
    Button update_button, delete_button;

    String id, judul, kategori, konten, tanggal;
    DailyModel dailyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_daily);
        judul_update = findViewById(R.id.judul_update);
        kategori_update = findViewById(R.id.kategori_update);
        konten_update = findViewById(R.id.konten_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(judul);
        }
        dailyModel = new DailyModel(id, judul, kategori, konten, tanggal);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DailyService db = new DailyService(UpdateDailyActivity.this);
                db.updateData(dailyModel);
                Intent intent = new Intent(UpdateDailyActivity.this, MainActivity.class);
//            buat clear activity sebelumnya
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("judul") &&
                getIntent().hasExtra("kategori") &&
                getIntent().hasExtra("konten") &&
                getIntent().hasExtra("tanggal")){

            id = getIntent().getStringExtra("id");
            judul = getIntent().getStringExtra("judul");
            kategori = getIntent().getStringExtra("kategori");
            konten = getIntent().getStringExtra("konten");
            tanggal = getIntent().getStringExtra("tanggal");

            judul_update.setText(judul);
            kategori_update.setText(kategori);
            konten_update.setText(konten);
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + judul + " ?");
        builder.setMessage("Are you sure you want to delete " + judul + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DailyService db = new DailyService(UpdateDailyActivity.this);
                db.deleteOneRow(id);

                Intent intent = new Intent(UpdateDailyActivity.this, MainActivity.class);
//            buat clear activity sebelumnya
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}