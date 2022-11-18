package com.example.dnd_tracker.stats;

import static java.util.Map.entry;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dnd_tracker.R;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import com.example.dnd_tracker.Stats;

public class SetPlayerBaseStats extends Fragment {

    public HashMap<Stats, Integer> baseStats = new HashMap<>(Map.ofEntries(
            entry(Stats.Str, 0),
            entry(Stats.Dex, 0),
            entry(Stats.Con, 0),
            entry(Stats.Int, 0),
            entry(Stats.Wis, 0),
            entry(Stats.Cha, 0)
    ));

    Map<Stats, Integer> statIds = Map.ofEntries(
            entry(Stats.Str, R.id.str_input),
            entry(Stats.Dex, R.id.dex_input),
            entry(Stats.Con, R.id.con_input),
            entry(Stats.Int, R.id.int_input),
            entry(Stats.Wis, R.id.wis_input),
            entry(Stats.Cha, R.id.cha_input)
    );

    Button submitButton;

    public player_stats playerStats;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_player_base_stats, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().remove(this).commit();
            playerStats.computeStats(baseStats);
        });
        for (Map.Entry<Stats, Integer> st : baseStats.entrySet()) {
            EditText input = view.findViewById(statIds.get(st.getKey()));
            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    baseStats.put(st.getKey(), Integer.parseInt(s.toString()));
                }
            });
        }
    }
}