package com.example.dnd_tracker.spells_abilities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.database.Ability;
import com.example.dnd_tracker.database.Database;

import java.util.ArrayList;

public class abilities_fragment extends Fragment {

    TextView abilitiesText;
    Button add_ability_button;
    Spinner fragmentTypeSpinner;
    ConstraintLayout cl;

    ArrayList<View> abilities = new ArrayList<>();

    public enum FragmentType {
        Input,
        MaxValue,
        Slider
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_abilities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        add_ability_button = view.findViewById(R.id.add_ability_button);
        abilitiesText = view.findViewById(R.id.abilities_text);
        fragmentTypeSpinner = view.findViewById(R.id.fragment_type_spinner);
        add_ability_button.setOnClickListener(v -> {
            addAbility(FragmentType.values()[fragmentTypeSpinner.getSelectedItemPosition()]);
        });
        cl = view.findViewById(R.id.parent_layout);
        Database.getInstance().abilitiesFragment = this;
    }

    public ability_fragment addAbility(FragmentType type) {
        ability_fragment sm;
        switch (type) {
            case Input:
                sm = ability_with_input_fragment.newInstance(this);
                break;
            case MaxValue:
                sm = ability_with_max_value_fragment.newInstance(this);
                break;
            case Slider:
                sm = ability_with_slider_fragment.newInstance(this);
                break;
            default:
                sm = ability_fragment.newInstance(this);
                break;
        }
        View v = sm.onCreateView(getLayoutInflater(), cl, null);
        sm.onViewCreated(v, null);
        v.setId(View.generateViewId());
        ConstraintSet set = new ConstraintSet();
        cl.addView(v);
        set.clone(cl);
        abilities.add(v);
        if (abilities.size() == 1) {
            set.connect(v.getId(), ConstraintSet.TOP, abilitiesText.getId(), ConstraintSet.BOTTOM, 36);
        } else {
            set.connect(v.getId(), ConstraintSet.TOP, abilities.get(abilities.size() - 2).getId(), ConstraintSet.BOTTOM, 16);
        }
        set.connect(v.getId(), ConstraintSet.LEFT, cl.getId(), ConstraintSet.LEFT, 0);
        set.applyTo(cl);
        return sm;
    }

    public void removeAbility(View fragment, int id) {
        ConstraintSet set = new ConstraintSet();
        cl.removeView(cl.findViewById(id));
        set.clone(cl);
        if (abilities.indexOf(fragment) != abilities.size() - 1) {
            if (abilities.indexOf(fragment) == 0) {
                set.connect(
                        abilities.get(abilities.indexOf(fragment) + 1).getId(),
                        ConstraintSet.TOP,
                        R.id.abilities_text,
                        ConstraintSet.BOTTOM, 36);
            }
            else {
                set.connect(
                        abilities.get(abilities.indexOf(fragment) + 1).getId(),
                        ConstraintSet.TOP,
                        abilities.get(abilities.indexOf(fragment) - 1).getId(),
                        ConstraintSet.BOTTOM, 16);
            }
        }
        abilities.remove(fragment);
        set.applyTo(cl);
    }
}