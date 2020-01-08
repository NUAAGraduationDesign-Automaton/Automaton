package com.example.automaton.ui.regular;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegularViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RegularViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    LiveData<String> getText() {
        return mText;
    }
}