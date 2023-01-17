package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.database.Database;

public class ability_with_input_fragment extends ability_fragment {

    Button decrease;
    Button increase;

    public ability_with_input_fragment() {
        // Required empty public constructor
        super();
        ability.type = abilities_fragment.FragmentType.Input;
    }

    public static ability_with_input_fragment newInstance(abilities_fragment parent) {
        ability_with_input_fragment fragment = new ability_with_input_fragment();
        fragment.parent = parent;
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
        decrease = view.findViewById(R.id.decrement_button);
        increase = view.findViewById(R.id.increment_button);

        decrease.setOnClickListener(v -> {
            ability.value--;
            currentValue.setText(String.valueOf(ability.value));
        });
        increase.setOnClickListener(v -> {
            ability.value++;
            currentValue.setText(String.valueOf(ability.value));
        });
    }
}