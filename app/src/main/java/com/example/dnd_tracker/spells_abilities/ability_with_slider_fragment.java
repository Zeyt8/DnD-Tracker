package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.database.Database;

public class ability_with_slider_fragment extends ability_fragment {

    SeekBar seekBar;

    public ability_with_slider_fragment() {
        // Required empty public constructor
        super();
        ability.type = abilities_fragment.FragmentType.Slider;
    }

    public static ability_with_slider_fragment newInstance(abilities_fragment parent) {
        ability_with_slider_fragment fragment = new ability_with_slider_fragment();
        fragment.parent = parent;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ability_with_slider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar = view.findViewById(R.id.seekBar);
        maxValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    seekBar.setMax(Integer.parseInt(s.toString()));
                }
            }
            @Override
            public void afterTextChanged(android.text.Editable s) {
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ability.value = progress;
                currentValue.setText(String.valueOf(ability.value));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}