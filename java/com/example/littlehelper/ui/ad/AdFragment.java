package com.example.littlehelper.ui.ad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.littlehelper.databinding.FragmentAdBinding;

public class AdFragment extends Fragment {

    private FragmentAdBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AdViewModel adViewModel =
                new ViewModelProvider(this).get(AdViewModel.class);

        binding = FragmentAdBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textad;
        adViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}