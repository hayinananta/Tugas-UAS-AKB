package com.example.dailylife_10119005.ui.profile;

//NIM : 10119005
//Nama : Hayin Ananta
//Kelas : IF-1

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dailylife_10119005.R;
import com.example.dailylife_10119005.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import com.example.dailylife_10119005.databinding.FragmentHomeBinding;

public class ProfileFragment extends Fragment {

//    private FragmentHomeBinding binding;

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        ProfileViewModel homeViewModel =
//                new ViewModelProvider(this).get(ProfileViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }


    private Button btn_reset_password, btn_logout;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btn_logout = view.findViewById(R.id.btn_keluar);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser == null) {
                    startActivity(new Intent(getActivity(), SignInActivity.class));
                }
            }
        };
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Toast.makeText(getContext(), "Keluar", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });
//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                firebaseAuth.signOut();
//                Toast.makeText(getContext(), "Berhasil keluar.", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), SignInActivity.class));
//            }
//        });
        return view;
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        firebaseAuth.addAuthStateListener(authStateListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        firebaseAuth.removeAuthStateListener(authStateListener);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        binding = null;
    }
}