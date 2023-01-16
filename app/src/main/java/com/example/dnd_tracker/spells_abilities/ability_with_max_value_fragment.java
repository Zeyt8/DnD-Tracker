package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.database.Database;

public class ability_with_max_value_fragment extends ability_fragment {

    Button decrease;
    Button increase;

    public ability_with_max_value_fragment() {
        // Required empty public constructor
        Database.abilities.add(ability);
    }

    public static ability_with_max_value_fragment newInstance(abilities_fragment parent) {
        ability_with_max_value_fragment fragment = new ability_with_max_value_fragment();
        fragment.parent = parent;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ability_with_max_value, container, false);
    }
}