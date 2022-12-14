package com.example.dnd_tracker.stats;

import com.example.dnd_tracker.database.Stats;

public class StatModifier {
    public Stats stat;
    public Types type;
    public int value;
}

enum Types {
    Flat,
    Percentage,
    Setter
}