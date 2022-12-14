package com.example.dnd_tracker.database;

import com.example.dnd_tracker.EStats;
import com.example.dnd_tracker.stats.StatModifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

public class Database {

    public static Database Instance;

    private Database() {}

    public static Database getInstance() {
        if (Instance == null) {
            Instance = new Database();
        }
        return Instance;
    }

    public Stats baseStats = new Stats();
    public Stats actualStats = new Stats();
    public ArrayList<StatModifier> statModifiers = new ArrayList<>();

    public void setBaseStats(int str, int dex, int con, int _int, int wis, int cha) {
        baseStats.str = str;
        baseStats.dex = dex;
        baseStats.con = con;
        baseStats._int = _int;
        baseStats.wis = wis;
        baseStats.cha = cha;
    }

    public Map<EStats, Integer> getBaseStats() {
        ObjectMapper mapObject = new ObjectMapper();
        return mapObject.convertValue(baseStats, Map.class);
    }

    public void save(String filename) {

    }
}
