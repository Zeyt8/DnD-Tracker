package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dnd_tracker.database.Ability;
import com.google.android.material.textfield.TextInputEditText;

public class ability_fragment extends Fragment {

    public Ability ability;
    public TextInputEditText name;
    public TextInputEditText description;
    public ImageButton delete;
    public TextView currentValue;
    public TextView maxValue;

    public ability_fragment() {
        // Required empty public constructor
    }

    public static ability_fragment newInstance(String param1, String param2) {
        ability_fragment fragment = new ability_fragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}