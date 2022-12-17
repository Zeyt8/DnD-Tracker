package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.database.Database;

import org.jetbrains.annotations.NotNull;

public class spell_slots_fragment extends Fragment {

    View lastSpellSlot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spell_slots, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Button decrease = view.findViewById(R.id.remove_spell_slot);
        Button increase = view.findViewById(R.id.add_spell_slot);
        increase.setOnClickListener(v -> {
            addSpellSlot(view);
        });
        decrease.setOnClickListener(v -> {
            removeSpellSlot(view);
        });
    }

    private void addSpellSlot(View view) {
        Fragment sm = spell_slot_fragment.newInstance(Database.spellSlots.size() + 1);
        View v = sm.onCreateView(getLayoutInflater(), (ViewGroup) view, null);
        sm.onViewCreated(v, null);
        v.setId(View.generateViewId());
        ConstraintLayout cl = view.findViewById(R.id.parent_layout);
        ConstraintSet set = new ConstraintSet();
        cl.addView(v);
        set.clone(cl);
        if (lastSpellSlot == null) {
            set.connect(v.getId(), ConstraintSet.TOP, R.id.spell_slots_text, ConstraintSet.BOTTOM, 16);
        } else {
            set.connect(v.getId(), ConstraintSet.TOP, lastSpellSlot.getId(), ConstraintSet.BOTTOM, 16);
        }
        set.connect(v.getId(), ConstraintSet.LEFT, R.id.parent_layout, ConstraintSet.LEFT, 24);
        lastSpellSlot = v;
        set.applyTo(cl);
    }

    // TODO: Fix this
    private void removeSpellSlot(View view) {
        if (lastSpellSlot != null) {
            ConstraintLayout cl = view.findViewById(R.id.parent_layout);
            cl.removeView(lastSpellSlot);
            lastSpellSlot = null;
        }
    }
}