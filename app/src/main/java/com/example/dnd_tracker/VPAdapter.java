package com.example.dnd_tracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dnd_tracker.abilities_buffs.abilities_and_buffs;
import com.example.dnd_tracker.spells.spell_slots;
import com.example.dnd_tracker.stats.stats;

public class VPAdapter extends FragmentStateAdapter {

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new stats();
            case 1:
                return new spell_slots();
            case 2:
                return new abilities_and_buffs();
            default:
                return new stats();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
