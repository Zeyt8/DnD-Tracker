package com.example.dnd_tracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dnd_tracker.buffs_debuffs.buffs_and_debuffs_fragment;
import com.example.dnd_tracker.spells_abilities.spell_slots_fragment;
import com.example.dnd_tracker.stats.stats_fragment;

public class VPAdapter extends FragmentStateAdapter {

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new stats_fragment();
            case 1:
                return new spell_slots_fragment();
            case 2:
                return new buffs_and_debuffs_fragment();
            default:
                return new stats_fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
