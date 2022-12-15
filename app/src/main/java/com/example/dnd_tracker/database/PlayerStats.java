package com.example.dnd_tracker.database;

import com.example.dnd_tracker.Stats;

import org.jetbrains.annotations.NotNull;

public class PlayerStats {
    public int str;
    public int dex;
    public int con;
    public int _int;
    public int wis;
    public int cha;

    public void setStat(@NotNull Stats stat, int value) {
        switch (stat) {
            case Str:
                str = value;
                break;
            case Dex:
                dex = value;
                break;
            case Con:
                con = value;
                break;
            case Int:
                _int = value;
                break;
            case Wis:
                wis = value;
                break;
            case Cha:
                cha = value;
                break;
        }
    }

    public int getStat(@NotNull Stats stat) {
        switch (stat) {
            case Str:
                return str;
            case Dex:
                return dex;
            case Con:
                return con;
            case Int:
                return _int;
            case Wis:
                return wis;
            case Cha:
                return cha;
        }
        return 0;
    }
}
