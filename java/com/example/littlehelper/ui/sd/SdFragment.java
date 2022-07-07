package com.example.littlehelper.ui.sd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.littlehelper.databinding.FragmentSdBinding;

public class SdFragment extends Fragment {

    private FragmentSdBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SdViewModel sdViewModel =
                new ViewModelProvider(this).get(SdViewModel.class);

        binding = FragmentSdBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSd;
        sdViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}