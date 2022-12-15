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

import com.example.dnd_tracker.database.Database;
import com.example.dnd_tracker.R;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import com.example.dnd_tracker.Stats;

public class set_player_base_stats_fragment extends Fragment {

    Map<Stats, Integer> statIds = Map.ofEntries(
            entry(Stats.Str, R.id.str_input),
            entry(Stats.Dex, R.id.dex_input),
            entry(Stats.Con, R.id.con_input),
            entry(Stats.Int, R.id.int_input),
            entry(Stats.Wis, R.id.wis_input),
            entry(Stats.Cha, R.id.cha_input)
    );

    Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_player_base_stats, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        setBaseStats();
        submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            for (Map.Entry<Stats, Integer> st : statIds.entrySet()) {
                EditText input = view.findViewById(st.getValue());
                if (input.getText().toString().equals("")) {
                    input.setText("0");
                } else {
                    Database.baseStats.setStat(st.getKey(), Integer.parseInt(input.getText().toString()));
                }
            }
            Database.recalculateActualStats();
            getParentFragmentManager().beginTransaction().remove(this).commit();
        });
    }

    public void setBaseStats() {
        for (Map.Entry<Stats, Integer> st : statIds.entrySet()) {
            EditText input = getView().findViewById(st.getValue());
            if (Database.baseStats.getStat(st.getKey()) != 0) {
                input.setText(String.valueOf(Database.baseStats.getStat(st.getKey())));
            }
        }
    }
}
