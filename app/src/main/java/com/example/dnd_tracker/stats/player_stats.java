package com.example.dnd_tracker.stats;

import static java.util.Map.entry;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dnd_tracker.R;
import com.example.dnd_tracker.Stats;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class player_stats extends Fragment {

    SetPlayerBaseStats setPlayerBaseStats;
    private HashMap<Stats, Integer> stats = new HashMap<>(Map.ofEntries(
            entry(Stats.Str, 0),
            entry(Stats.Dex, 0),
            entry(Stats.Con, 0),
            entry(Stats.Int, 0),
            entry(Stats.Wis, 0),
            entry(Stats.Cha, 0)
    ));

    Map<Stats, Integer> statIds = Map.ofEntries(
            entry(Stats.Str, R.id.str_text),
            entry(Stats.Dex, R.id.dex_text),
            entry(Stats.Con, R.id.con_text),
            entry(Stats.Int, R.id.int_text),
            entry(Stats.Wis, R.id.wis_text),
            entry(Stats.Cha, R.id.cha_text)
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_stats, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Button button = view.findViewById(R.id.submit_button);
        button.setOnClickListener(v -> {
            setPlayerBaseStats = new SetPlayerBaseStats();
            setPlayerBaseStats.playerStats = this;
            getParentFragmentManager().beginTransaction().add(R.id.frameLayout, setPlayerBaseStats).commit();
        });
    }

    @SuppressLint("SetTextI18n")
    public void computeStats(HashMap<Stats, Integer> baseStats) {
        stats.putAll(baseStats);
        for (Map.Entry<Stats, Integer> st : stats.entrySet()) {
            TextView input = requireView().findViewById(statIds.get(st.getKey()));
            int modifier = (int)((st.getValue() - 10) / 2);
            input.setText(st.getKey().toString() + ":\n" + st.getValue() + "(" + (modifier < 0 ? modifier : "+" + modifier) + ")");
        }
    }
}