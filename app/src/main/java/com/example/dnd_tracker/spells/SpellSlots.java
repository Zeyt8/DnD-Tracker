package com.example.dnd_tracker.spells;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dnd_tracker.R;

import org.jetbrains.annotations.NotNull;

public class SpellSlots extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spell_slots, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        for (int i = 1; i <= 9; i++) {
            TextView decrease = view.findViewWithTag("decrease" + i);
            TextView increase = view.findViewWithTag("increase" + i);
            TextView current = view.findViewWithTag("currentSpellSlots" + i);
            EditText max = view.findViewWithTag("maxSpellSlots" + i);
            decrease.setOnClickListener(
                    v -> current.setText(
                            String.valueOf(
                                    Math.max(Integer.parseInt(current.getText().toString()) - 1, 0)
                            )
                    )
            );
            increase.setOnClickListener(
                    v -> {
                        if (max.getText().toString().equals("")) {
                            return;
                        }
                        current.setText(
                                String.valueOf(
                                        Math.min(Integer.parseInt(current.getText().toString()) + 1, Integer.parseInt(max.getText().toString()))
                                )
                        );
                    }
            );
        }
    }
}