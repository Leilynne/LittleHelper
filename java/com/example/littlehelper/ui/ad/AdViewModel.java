package com.example.littlehelper.ui.ad;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.littlehelper.MainActivity;


public class AdViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AdViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Количество шагов: " + (MainActivity.numSteps));
    }
    public LiveData<String> getText() {
        return mText;
    }

}