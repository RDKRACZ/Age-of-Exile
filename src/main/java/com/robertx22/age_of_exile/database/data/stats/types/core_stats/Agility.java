package com.robertx22.age_of_exile.database.data.stats.types.core_stats;

import com.robertx22.age_of_exile.database.OptScaleExactStat;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.age_of_exile.database.data.stats.types.offense.Accuracy;
import com.robertx22.age_of_exile.database.data.stats.types.offense.crit.CriticalHit;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;

import java.util.Arrays;

public class Agility extends BaseCoreStat {

    public static final String GUID = "agility";

    public static final Agility INSTANCE = new Agility();

    private Agility() {
        super(Arrays.asList(
            new OptScaleExactStat(20, 20, Accuracy.getInstance(), ModType.FLAT),
            new OptScaleExactStat(0.5F, 0.5F, CriticalHit.getInstance(), ModType.FLAT)
        ));
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public String locNameForLangFile() {
        return "Agility";
    }
}
