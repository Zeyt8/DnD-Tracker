package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.database.Database;
import com.example.dnd_tracker.database.SpellSlot;

public class spell_slot_fragment extends Fragment {

    SpellSlot spellSlot;

    TextView decrease;
    TextView increase;
    TextView current;
    EditText max;

    public spell_slot_fragment() {
        spellSlot = new SpellSlot();
        Database.spellSlots.add(spellSlot);
    }

    public static spell_slot_fragment newInstance(int level) {
        spell_slot_fragment fragment = new spell_slot_fragment();
        fragment.spellSlot.level = level;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spell_slot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView label = view.findViewById(R.id.spell_slot_level_text);
        label.setText("Level " + spellSlot.level);

        decrease = view.findViewById(R.id.decrease_button);
        increase = view.findViewById(R.id.increase_button);
        current = view.findViewById(R.id.current_spell_slots);
        max = view.findViewById(R.id.max_spell_slots);

        decrease.setOnClickListener(v -> setCurrentSpellSlot(Math.max(getCurrentSpellSlot() - 1, 0)));
        increase.setOnClickListener(v -> {
            if (max.getText().toString().equals("")) {
                return;
            }
            setCurrentSpellSlot(Math.min(getCurrentSpellSlot() + 1, spellSlot.max));
        });
        max.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    spellSlot.max = 0;
                } else {
                    spellSlot.max = Integer.parseInt(s.toString());
                }
            }
        });
    }

    private int getCurrentSpellSlot() {
        return spellSlot.current;
    }

    private void setCurrentSpellSlot(int value) {
        current.setText(String.valueOf(value));
        spellSlot.current = value;
    }
}