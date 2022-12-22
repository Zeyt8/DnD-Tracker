package com.example.dnd_tracker.database;

import com.example.dnd_tracker.Stats;

public class StatModifier {

    public String name;
    public String description;
    public Stats stat = Stats.Str;
    public Types type = Types.Flat;
    public int value = 0;
    public boolean active = true;

    enum Types {
        Flat,
        Percentage,
        Setter
    }

    public int applyModifier(int value) {
        switch (type) {
            case Flat:
                return value + this.value;
            case Percentage:
                return (int) (value * (1 + this.value / 100.0));
            case Setter:
                return this.value;
            default:
                return value;
        }
    }
}