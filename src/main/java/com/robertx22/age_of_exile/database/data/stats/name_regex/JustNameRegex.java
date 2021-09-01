package com.robertx22.age_of_exile.database.data.stats.name_regex;

import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;

public class JustNameRegex extends StatNameRegex {

    @Override
    public String getStatNameRegex(ModType type, Stat stat, float v1) {
        return NAME;
    }
}
