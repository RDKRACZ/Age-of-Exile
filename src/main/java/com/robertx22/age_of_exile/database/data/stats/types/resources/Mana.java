package com.robertx22.age_of_exile.database.data.stats.types.resources;

import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.database.data.stats.StatScaling;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;

public class Mana extends Stat {
    public static String GUID = "mana";

    public static Mana getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.SCALING;
    }

    @Override
    public String getIconPath() {
        return "resource/mana";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    @Override
    public String locDescForLangFile() {
        return "Mana is used to cast spells";
    }

    private Mana() {
        this.min_val = 0;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Mana";
    }

    private static class SingletonHolder {
        private static final Mana INSTANCE = new Mana();
    }
}
