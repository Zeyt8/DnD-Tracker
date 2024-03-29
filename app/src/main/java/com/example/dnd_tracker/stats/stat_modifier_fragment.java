package com.example.dnd_tracker.stats;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.Stats;
import com.example.dnd_tracker.database.Database;
import com.example.dnd_tracker.database.StatModifier;

public class stat_modifier_fragment extends Fragment {

    EditText name;
    EditText description;
    StatModifier statModifier;
    Spinner stat;
    Spinner type;
    EditText value;
    CheckBox active;
    ImageButton delete;

    stats_fragment parent;

    public stat_modifier_fragment() {
        // Required empty public constructor
        statModifier = new StatModifier();
        Database.statModifiers.add(statModifier);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment statModifier.
     */
    public static stat_modifier_fragment newInstance(stats_fragment parent) {
        stat_modifier_fragment fragment = new stat_modifier_fragment();
        fragment.parent = parent;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stat_modifier, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        name = view.findViewById(R.id.stat_modifier_name);
        description = view.findViewById(R.id.stat_modifier_description);
        stat = view.findViewById(R.id.stat_spinner);
        type = view.findViewById(R.id.type_spinner);
        value = view.findViewById(R.id.value_text_input);
        active = view.findViewById(R.id.active_checkbox);
        delete = view.findViewById(R.id.delete_button);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                statModifier.name = s.toString();
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
                statModifier.description = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        stat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statModifier.stat = Stats.values()[position];
                Database.recalculateActualStats();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                statModifier.stat = null;
            }
        });

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statModifier.type = StatModifier.Types.values()[position];
                Database.recalculateActualStats();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                statModifier.type = null;
            }
        });

        value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    statModifier.value = 0;
                } else {
                    statModifier.value = Integer.parseInt(s.toString());
                }
                Database.recalculateActualStats();
            }
        });

        active.setOnClickListener(v -> {
            statModifier.active = active.isChecked();
            Database.recalculateActualStats();
        });

        delete.setOnClickListener(v -> {
            Database.statModifiers.remove(statModifier);
            parent.removeModifier(view, view.getId());
            Database.recalculateActualStats();
        });
    }

    public void setModifier(StatModifier statModifier) {
        this.statModifier = statModifier;
        name.setText(statModifier.name);
        description.setText(statModifier.description);
        stat.setSelection(statModifier.stat.ordinal());
        type.setSelection(statModifier.type.ordinal());
        value.setText(String.valueOf(statModifier.value));
        active.setChecked(statModifier.active);
    }
}
