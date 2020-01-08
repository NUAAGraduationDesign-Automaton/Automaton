package com.example.automaton.ui.turing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TuringViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TuringViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    LiveData<String> getText() {
        return mText;
    }
}