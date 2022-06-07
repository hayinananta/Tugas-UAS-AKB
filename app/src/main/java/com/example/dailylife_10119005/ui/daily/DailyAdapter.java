package com.example.dailylife_10119005.ui.daily;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailylife_10119005.R;
import com.example.dailylife_10119005.model.DailyModel;

import java.util.ArrayList;
import java.util.List;

//NIM : 10119005
//Nama : Hayin Ananta
//Kelas : IF-1

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.MyViewHolder> {
    private Activity activity;
    private List<DailyModel> daily_list = new ArrayList<DailyModel>();

    public DailyAdapter(Activity activity, List<DailyModel> daily_list) {
        this.activity = activity;
        this.daily_list = daily_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.daily_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.daily_judul_txt.setText(String.valueOf(daily_list.get(position).getJudul()));
        holder.daily_kategori_txt.setText(String.valueOf(daily_list.get(position).getKategori()));
        holder.daily_tanggal_txt.setText(String.valueOf(daily_list.get(position).getTanggal()).substring(0,10));

        holder.linearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(activity, UpdateDailyActivity.class);
            intent.putExtra("id", String.valueOf(daily_list.get(position).getId()));
            intent.putExtra("judul", String.valueOf(daily_list.get(position).getJudul()));
            intent.putExtra("kategori", String.valueOf(daily_list.get(position).getKategori()));
            intent.putExtra("konten", String.valueOf(daily_list.get(position).getKonten()));
            intent.putExtra("tanggal", String.valueOf(daily_list.get(position).getTanggal()));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        if (daily_list != null) return daily_list.size();
        return 0;
    }

//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
//        LayoutInflater inflater = LayoutInflater.from(activity);
//        View view = inflater.inflate(R.layout.daily_row, parent, false);
//        return new MyViewHolder(view);
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.daily_judul_txt.setText(String.valueOf(daily_list.get(position).getJudul()));
//        holder.daily_kategori_txt.setText(String.valueOf(daily_list.get(position).getKategori()));
//        holder.daily_tanggal_txt.setText(String.valueOf(daily_list.get(position).getTanggal()));
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(activity, UpdateDailyActivity.class);
//                intent.putExtra("id", String.valueOf(daily_list.get(position).getId()));
//                intent.putExtra("judul", String.valueOf(daily_list.get(position).getJudul()));
//                intent.putExtra("kategori", String.valueOf(daily_list.get(position).getKategori()));
//                intent.putExtra("konten", String.valueOf(daily_list.get(position).getKonten()));
//                intent.putExtra("tanggal", String.valueOf(daily_list.get(position).getTanggal()));
//                activity.startActivityForResult(intent, 1);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        if (daily_list != null) return daily_list.size();
//        return 0;
//    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView daily_judul_txt,daily_kategori_txt,daily_tanggal_txt;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            daily_judul_txt = itemView.findViewById(R.id.daily_judul_txt);
            daily_kategori_txt = itemView.findViewById(R.id.daily_kategori_txt);
            daily_tanggal_txt = itemView.findViewById(R.id.daily_tanggal_txt);
            linearLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
