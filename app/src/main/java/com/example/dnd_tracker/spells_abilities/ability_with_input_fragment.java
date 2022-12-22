package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dnd_tracker.R;

public class ability_with_input_fragment extends ability_fragment {

    Button decrease;
    Button increase;

    public ability_with_input_fragment() {
        // Required empty public constructor
    }

    public static ability_with_input_fragment newInstance(String param1, String param2) {
        ability_with_input_fragment fragment = new ability_with_input_fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ability_with_input, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}