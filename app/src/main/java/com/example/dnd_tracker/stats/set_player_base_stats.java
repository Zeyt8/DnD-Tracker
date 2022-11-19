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

import com.example.dnd_tracker.EStats;

public class set_player_base_stats extends Fragment {

    Map<EStats, Integer> statIds = Map.ofEntries(
            entry(EStats.Str, R.id.str_input),
            entry(EStats.Dex, R.id.dex_input),
            entry(EStats.Con, R.id.con_input),
            entry(EStats.Int, R.id.int_input),
            entry(EStats.Wis, R.id.wis_input),
            entry(EStats.Cha, R.id.cha_input)
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
            playerStats.computeStats();
        });
        for (Map.Entry<EStats, Integer> st : statIds.entrySet()) {
            EditText input = view.findViewById(st.getValue());
            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Database.getInstance().baseStats.setStat(st.getKey(), Integer.parseInt(s.toString()));
                }
            });
        }
    }
}