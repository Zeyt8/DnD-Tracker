package com.example.dnd_tracker.database;

import com.example.dnd_tracker.Listener;
import com.example.dnd_tracker.Stats;
import com.example.dnd_tracker.stats.StatModifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

public class Database {

    private static Database Instance;

    private Database() {}

    public static Database getInstance() {
        if (Instance == null) {
            Instance = new Database();
        }
        return Instance;
    }

    public static PlayerStats baseStats = new PlayerStats();
    public static PlayerStats actualStats = new PlayerStats();
    public static ArrayList<StatModifier> statModifiers = new ArrayList<>();

    public static ArrayList<Listener> statChangeListeners = new ArrayList<>();

    public void setBaseStats(int str, int dex, int con, int _int, int wis, int cha) {
        baseStats.str = str;
        baseStats.dex = dex;
        baseStats.con = con;
        baseStats._int = _int;
        baseStats.wis = wis;
        baseStats.cha = cha;
    }

    public static Map getBaseStats() {
        ObjectMapper mapObject = new ObjectMapper();
        return mapObject.convertValue(baseStats, Map.class);
    }

    public static void save(String filename) {

    }

    public static void recalculateActualStats() {
        for (Stats stat : Stats.values()) {
            int value = baseStats.getStat(stat);
            for (StatModifier modifier : statModifiers) {
                System.out.println("StatModifier: " + modifier.stat + " " + modifier.type + " " + modifier.value);
                if (modifier.stat == stat) {
                    value = modifier.applyModifier(value);
                }
            }
            System.out.println("Stat: " + stat + " Value: " + value);
            actualStats.setStat(stat, value);
        }
        for (Listener listener : statChangeListeners) {
            listener.onEvent();
        }
    }
}
