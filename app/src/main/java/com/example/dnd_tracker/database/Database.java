package com.example.dnd_tracker.database;

import com.example.dnd_tracker.Listener;
import com.example.dnd_tracker.Stats;
import com.example.dnd_tracker.spells_abilities.abilities_fragment;
import com.example.dnd_tracker.spells_abilities.ability_fragment;
import com.example.dnd_tracker.spells_abilities.spell_slot_fragment;
import com.example.dnd_tracker.spells_abilities.spell_slots_fragment;
import com.example.dnd_tracker.stats.stat_modifier_fragment;
import com.example.dnd_tracker.stats.stats_fragment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectWriter;

public class Database {

    private static Database Instance;
    @JsonIgnore
    public stats_fragment statsFragment;
    @JsonIgnore
    public spell_slots_fragment spellSlotsFragment;
    @JsonIgnore
    public abilities_fragment abilitiesFragment;

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
    public static ArrayList<SpellSlot> spellSlots = new ArrayList<>();
    public static ArrayList<Ability> abilities = new ArrayList<>();
    public static ArrayList<Listener> statChangeListeners = new ArrayList<>();

    public PlayerStats getBaseStats() {
        return baseStats;
    }

    public ArrayList<StatModifier> getStatModifiers() {
        return statModifiers;
    }

    public ArrayList<SpellSlot> getSpellSlots() {
        return spellSlots;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void setBaseStats(PlayerStats baseStats) {
        Database.baseStats = baseStats;
    }

    public void setStatModifiers(ArrayList<StatModifier> statModifiers) {
        Database.statModifiers = statModifiers;
    }

    public void setSpellSlots(ArrayList<SpellSlot> spellSlots) {
        Database.spellSlots = spellSlots;
    }

    public void setAbilities(ArrayList<Ability> abilities) {
        Database.abilities = abilities;
    }

    public void save(String filename) {
        // save baseStats, statModifiers, spellSlots, abilities to filename
        ObjectMapper mapObject = new ObjectMapper();
        ObjectWriter writer = mapObject.writerWithDefaultPrettyPrinter();
        try {
            String json = writer.writeValueAsString(this);
            System.out.println(json);
            FileWriter file = new FileWriter(filename);
            file.write(json);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(String filename) {
        // read json from filename
        FileReader file = null;
        try {
            file = new FileReader(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapObject = new ObjectMapper();
        try {
            mapObject.readValue(file, Database.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        recalculateActualStats();
        /*for (StatModifier statModifier : statModifiers) {
            stat_modifier_fragment sm = statsFragment.addModifier();
            sm.setModifier(statModifier);
        }*/
        for (SpellSlot spellSlot : spellSlots) {
            spell_slot_fragment ss = spellSlotsFragment.addSpellSlot();
            ss.setSpellSlot(spellSlot);
        }
        for (int i = 0; i < abilities.size(); i++) {
            ability_fragment af = abilitiesFragment.addAbility(abilities.get(i).type);
            af.setAbility(abilities.get(i));
        }
    }

    public static void recalculateActualStats() {
        for (Stats stat : Stats.values()) {
            int value = baseStats.getStat(stat);
            for (StatModifier modifier : statModifiers) {
                if (modifier.stat == stat && modifier.active) {
                    value = modifier.applyModifier(value);
                }
            }
            actualStats.setStat(stat, value);
        }
        for (Listener listener : statChangeListeners) {
            listener.onEvent();
        }
    }
}
