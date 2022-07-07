package com.example.littlehelper.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.littlehelper.FinClass;

import com.example.littlehelper.databinding.FragmentProfileBinding;

public class Fragment_profile extends Fragment {
    private FragmentProfileBinding binding;
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentProfileViewModel fragmentProfileViewModel =
                new ViewModelProvider(this).get(FragmentProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textprofile;
        textView.setText(" " + FinClass.getAlarm());
        return root;
    }

}
