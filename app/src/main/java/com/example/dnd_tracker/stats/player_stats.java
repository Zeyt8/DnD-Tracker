package com.example.dnd_tracker.stats;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dnd_tracker.R;

import org.jetbrains.annotations.NotNull;

public class player_stats extends Fragment {

    SetPlayerBaseStats setPlayerBaseStats;

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
            getParentFragmentManager().beginTransaction().add(R.id.frameLayout, setPlayerBaseStats).commit();
        });
    }
}