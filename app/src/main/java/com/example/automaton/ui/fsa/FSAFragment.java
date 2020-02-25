package com.example.automaton.ui.fsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.example.automaton.R;
import com.example.automaton.ui.base.BaseFragment;
import com.example.automaton.ui.base.CanvasView;

public class FSAFragment extends BaseFragment {
    private CanvasView canvasView;
    private FSAViewModel FSAViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        FSAViewModel =
                ViewModelProviders.of(this).get(FSAViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fsa, container, false);
        final Button addStatesBtn = root.findViewById(R.id.button_add_states);
        canvasView = root.findViewById(R.id.canvas_view);
        setupAddStatesBtn(addStatesBtn, canvasView);
        return root;
    }

}