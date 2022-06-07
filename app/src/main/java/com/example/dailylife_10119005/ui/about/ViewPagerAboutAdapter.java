package com.example.dailylife_10119005.ui.about;

//NIM : 10119005
//Nama : Hayin Ananta
//Kelas : IF-1

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailylife_10119005.R;
import com.example.dailylife_10119005.model.AboutItemModel;

import java.util.List;

public class ViewPagerAboutAdapter extends RecyclerView.Adapter<ViewPagerAboutAdapter.ViewHolder> {

    List<AboutItemModel> aboutList;

    public ViewPagerAboutAdapter(List<AboutItemModel> aboutList) {
        this.aboutList = aboutList;
    }

    @NonNull
    @Override
    public ViewPagerAboutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAboutAdapter.ViewHolder holder, int position) {
        AboutItemModel aboutItem = aboutList.get(position);
        holder.title.setText(aboutItem.title);
        holder.desc.setText(aboutItem.desc);
    }

    @Override
    public int getItemCount() {
        if(aboutList != null) return aboutList.size();
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, desc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_about);
            desc = itemView.findViewById(R.id.desc_about);
        }
    }
}
