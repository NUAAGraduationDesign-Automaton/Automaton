package com.example.automaton.ui.fsa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FSAViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FSAViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    LiveData<String> getText() {
        return mText;
    }
}