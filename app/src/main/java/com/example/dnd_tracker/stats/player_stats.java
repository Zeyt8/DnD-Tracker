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
import com.example.dnd_tracker.EStats;
import com.example.dnd_tracker.database.Database;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class player_stats extends Fragment {

    set_player_base_stats setPlayerBaseStats;

    Map<EStats, Integer> statIds = Map.ofEntries(
            entry(EStats.Str, R.id.str_text),
            entry(EStats.Dex, R.id.dex_text),
            entry(EStats.Con, R.id.con_text),
            entry(EStats.Int, R.id.int_text),
            entry(EStats.Wis, R.id.wis_text),
            entry(EStats.Cha, R.id.cha_text)
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
            setPlayerBaseStats = new set_player_base_stats();
            setPlayerBaseStats.playerStats = this;
            getParentFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, setPlayerBaseStats).commit();
        });
    }

    @SuppressLint("SetTextI18n")
    public void computeStats() {
        for (Map.Entry<EStats, Integer> st : statIds.entrySet()) {
            TextView input = requireView().findViewById(st.getValue());

            Database.getInstance().actualStats.setStat(
                    st.getKey(),
                    Database.getInstance().baseStats.getStat(st.getKey())
            );

            int value = Database.getInstance().actualStats.getStat(st.getKey());
            int modifier = (int)((value - 10) / 2);
            input.setText(
                    st.getKey().toString() + ":\n" + value + "(" + (modifier < 0 ? modifier : "+" + modifier) + ")"
            );
        }
    }
}