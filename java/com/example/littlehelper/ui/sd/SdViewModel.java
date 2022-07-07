package com.example.littlehelper.ui.sd;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SdViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SdViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is sd fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}