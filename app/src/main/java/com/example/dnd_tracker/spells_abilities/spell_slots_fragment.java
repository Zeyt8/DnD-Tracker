package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.database.Database;

import java.util.ArrayList;

public class spell_slots_fragment extends Fragment {

    ArrayList<View> spellSlots = new ArrayList<>();
    ConstraintLayout cl;

    TextView spellSlotsText;
    TextView abilitiesText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spell_slots, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        spellSlotsText = view.findViewById(R.id.spell_slots_text);
        abilitiesText = view.findViewById(R.id.abilities_text);
        Button decrease = view.findViewById(R.id.remove_spell_slot);
        Button increase = view.findViewById(R.id.add_spell_slot);
        increase.setOnClickListener(v -> {
            addSpellSlot(view);
        });
        decrease.setOnClickListener(v -> {
            removeSpellSlot();
        });
        cl = view.findViewById(R.id.parent_layout);
    }

    private void addSpellSlot(View view) {
        spell_slot_fragment sm = spell_slot_fragment.newInstance(Database.spellSlots.size() + 1);
        View v = sm.onCreateView(getLayoutInflater(), (ViewGroup) view, null);
        sm.onViewCreated(v, null);
        v.setId(View.generateViewId());
        ConstraintSet set = new ConstraintSet();
        cl.addView(v);
        set.clone(cl);
        spellSlots.add(v);
        if (spellSlots.size() == 1) {
            set.connect(v.getId(), ConstraintSet.TOP, spellSlotsText.getId(), ConstraintSet.BOTTOM, 16);
        } else {
            set.connect(v.getId(), ConstraintSet.TOP, spellSlots.get(spellSlots.size() - 2).getId(), ConstraintSet.BOTTOM, 16);
        }
        set.connect(v.getId(), ConstraintSet.LEFT, cl.getId(), ConstraintSet.LEFT, 24);
        set.applyTo(cl);
    }

    private void removeSpellSlot() {
        if (spellSlots.size() == 0) {
            return;
        }
        cl.removeView(cl.findViewById(spellSlots.get(spellSlots.size() - 1).getId()));
        spellSlots.remove(spellSlots.size() - 1);

        Database.spellSlots.remove(Database.spellSlots.size() - 1);
    }
}