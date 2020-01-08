package com.example.automaton.ui.turing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.automaton.R;

public class TuringFragment extends Fragment {

    private TuringViewModel turingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        turingViewModel =
                ViewModelProviders.of(this).get(TuringViewModel.class);
        View root = inflater.inflate(R.layout.fragment_turing, container, false);
        final TextView textView = root.findViewById(R.id.text_turing_title);
        turingViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}