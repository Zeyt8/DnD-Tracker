package com.example.dnd_tracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdapter extends FragmentStateAdapter {

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Stats();
            case 1:
                return new SpellSlots();
            case 2:
                return new AbilitiesAndBuffs();
            default:
                return new Stats();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}