package com.example.automaton.ui.regular;

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

public class RegularFragment extends Fragment {

    private RegularViewModel regularViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        regularViewModel =
                ViewModelProviders.of(this).get(RegularViewModel.class);
        View root = inflater.inflate(R.layout.fragment_regular, container, false);
        final TextView textView = root.findViewById(R.id.text_regular_title);
        regularViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}