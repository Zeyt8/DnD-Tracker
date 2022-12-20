package com.example.dnd_tracker.stats;

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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class stats_fragment extends Fragment {

    ConstraintLayout cl;

    ArrayList<View> statModifierFragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Button button = view.findViewById(R.id.add_stat_modifier_button);
        button.setOnClickListener(v -> addModifier(view));
        cl = view.findViewById(R.id.parent_layout);
    }

    public void addModifier(View view) {
        stat_modifier_fragment sm = stat_modifier_fragment.newInstance(this);
        View v = sm.onCreateView(getLayoutInflater(), cl, null);
        sm.onViewCreated(v, null);

        v.setId(View.generateViewId());
        ConstraintSet set = new ConstraintSet();
        cl.addView(v);
        set.clone(cl);

        statModifierFragments.add(v);

        if (statModifierFragments.size() == 1) {
            set.connect(v.getId(), ConstraintSet.TOP, R.id.modifiers_text_view, ConstraintSet.BOTTOM, 0);
        } else {
            set.connect(v.getId(), ConstraintSet.TOP,
                    statModifierFragments.get(statModifierFragments.size() - 2).getId(), ConstraintSet.BOTTOM, 0);
        }
        set.connect(R.id.add_stat_modifier_button, ConstraintSet.TOP, v.getId(), ConstraintSet.BOTTOM, 16);
        set.applyTo(cl);
    }

    public void removeModifier(View fragment, int id) {
        ConstraintSet set = new ConstraintSet();
        cl.removeView(cl.findViewById(id));
        set.clone(cl);
        if (statModifierFragments.indexOf(fragment) != statModifierFragments.size() - 1) {
            if (statModifierFragments.indexOf(fragment) == 0) {
                set.connect(
                        statModifierFragments.get(statModifierFragments.indexOf(fragment) + 1).getId(),
                        ConstraintSet.TOP,
                        R.id.modifiers_text_view,
                        ConstraintSet.BOTTOM, 0);
            }
            else {
                set.connect(
                        statModifierFragments.get(statModifierFragments.indexOf(fragment) + 1).getId(),
                        ConstraintSet.TOP,
                        statModifierFragments.get(statModifierFragments.indexOf(fragment) - 1).getId(),
                        ConstraintSet.BOTTOM, 0);
            }
        }
        statModifierFragments.remove(fragment);
        if (statModifierFragments.size() == 0) {
            set.connect(R.id.add_stat_modifier_button, ConstraintSet.TOP, R.id.modifiers_text_view, ConstraintSet.BOTTOM, 16);
        } else {
            set.connect(R.id.add_stat_modifier_button, ConstraintSet.TOP,
                    statModifierFragments.get(statModifierFragments.size() - 1).getId(), ConstraintSet.BOTTOM, 16);
        }
        set.applyTo(cl);
    }
}