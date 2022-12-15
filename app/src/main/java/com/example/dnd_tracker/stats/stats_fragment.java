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

public class stats_fragment extends Fragment {

    View lastStatModifier;

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
    }

    public void addModifier(View view) {
        Fragment sm = new stat_modifier_fragment();
        View v = sm.onCreateView(getLayoutInflater(), (ViewGroup) view, null);
        sm.onViewCreated(v, null);
        v.setId(View.generateViewId());
        ConstraintLayout cl = view.findViewById(R.id.parent_layout);
        ConstraintSet set = new ConstraintSet();
        cl.addView(v);
        set.clone(cl);
        if (lastStatModifier == null) {
            set.connect(v.getId(), ConstraintSet.TOP, R.id.modifiers_text_view, ConstraintSet.BOTTOM, 0);
        } else {
            set.connect(v.getId(), ConstraintSet.TOP, lastStatModifier.getId(), ConstraintSet.BOTTOM, 0);
        }
        lastStatModifier = v;
        set.connect(R.id.add_stat_modifier_button, ConstraintSet.TOP, lastStatModifier.getId(), ConstraintSet.BOTTOM, 16);
        set.applyTo(cl);
    }
}