package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.dnd_tracker.R;

public class ability_with_slider_fragment extends ability_fragment {

    SeekBar seekBar;

    public ability_with_slider_fragment() {
        // Required empty public constructor
    }

    public static ability_with_slider_fragment newInstance(String param1, String param2) {
        ability_with_slider_fragment fragment = new ability_with_slider_fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ability_with_slider, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}