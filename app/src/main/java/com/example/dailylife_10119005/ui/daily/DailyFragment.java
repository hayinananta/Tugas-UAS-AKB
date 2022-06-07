package com.example.dailylife_10119005.ui.daily;

//NIM : 10119005
//Nama : Hayin Ananta
//Kelas : IF-1

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailylife_10119005.R;
//import com.example.dailylife_10119005.databinding.FragmentGalleryBinding;
import com.example.dailylife_10119005.model.DailyModel;
import com.example.dailylife_10119005.service.DailyService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DailyFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageView;
    TextView no_data;
    DailyAdapter dailyAdapter;

    DailyService db;
    List<DailyModel> daily_list = new ArrayList<DailyModel>();

//    private FragmentGalleryBinding binding;

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        DailyViewModel galleryViewModel =
//                new ViewModelProvider(this).get(DailyViewModel.class);
//
//        binding = FragmentGalleryBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textGallery;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        db = new DailyService(getActivity());
        recyclerView = view.findViewById(R.id.recyclerView);
        add_button = view.findViewById(R.id.add_button);
        empty_imageView = view.findViewById(R.id.empty_imageview);
        no_data = view.findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddDailyActivity.class);
                startActivity(intent);
            }
        });

        storeDataInListModel();

        dailyAdapter = new DailyAdapter(getActivity(), daily_list);
        recyclerView.setAdapter(dailyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));

        return view;
    }

    void storeDataInListModel(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){

        }else{
            while(cursor.moveToNext()){
                DailyModel dailyModel = new DailyModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                daily_list.add(dailyModel);
            }
            empty_imageView.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        binding = null;
    }
}