package com.example.dailylife_10119005.ui.daily;

//NIM : 10119005
//Nama : Hayin Ananta
//Kelas : IF-1

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dailylife_10119005.MainActivity;
import com.example.dailylife_10119005.R;
import com.example.dailylife_10119005.model.DailyModel;
import com.example.dailylife_10119005.service.DailyService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDailyActivity extends AppCompatActivity {
    EditText judul_input, kategori_input, konten_input;
    String tanggal_input;
    Button tambah_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_daily);

        judul_input = findViewById(R.id.judul_input);
        kategori_input = findViewById(R.id.kategori_input);
        konten_input = findViewById(R.id.konten_input);
        tambah_button = findViewById(R.id.tambah_button);
        tanggal_input = getDateNow();
        tambah_button.setOnClickListener(view -> {
            DailyService db = new DailyService(AddDailyActivity.this);
            DailyModel daily = new DailyModel("id",
                    judul_input.getText().toString().trim(),
                    kategori_input.getText().toString().trim(),
                    konten_input.getText().toString().trim(),
                    tanggal_input.trim());

            db.addDaily(daily);

            Intent intent = new Intent(AddDailyActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
    public String getDateNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}