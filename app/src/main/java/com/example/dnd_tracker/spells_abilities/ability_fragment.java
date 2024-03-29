package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.database.Ability;
import com.example.dnd_tracker.database.Database;

public class ability_fragment extends Fragment {

    public Ability ability;
    public EditText name;
    public EditText description;
    public ImageButton delete;
    public TextView currentValue;
    public EditText maxValue;

    abilities_fragment parent;

    public ability_fragment() {
        // Required empty public constructor
        ability = new Ability();
        Database.abilities.add(ability);
    }

    public static ability_fragment newInstance(abilities_fragment parent) {
        ability_fragment fragment = new ability_fragment();
        fragment.parent = parent;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.name_text);
        description = view.findViewById(R.id.description_text);
        delete = view.findViewById(R.id.delete_imagebutton);
        currentValue = view.findViewById(R.id.ability_value);
        maxValue = view.findViewById(R.id.ability_maxvalue);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ability.name = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ability.description = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        currentValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ability.value = Integer.parseInt(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        if (maxValue != null) {
            maxValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ability.maxValue = Integer.parseInt(s.toString());
                }
                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        delete.setOnClickListener(v -> {
            Database.abilities.remove(ability);
            parent.removeAbility(view, view.getId());
        });
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
        name.setText(ability.name);
        description.setText(ability.description);
        currentValue.setText(String.valueOf(ability.value));
        if (maxValue != null) {
            maxValue.setText(String.valueOf(ability.maxValue));
        }
    }
}