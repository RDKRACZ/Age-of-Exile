package com.robertx22.age_of_exile.uncommon.effectdatas.rework.condition;

import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.saveclasses.unit.StatData;
import com.robertx22.age_of_exile.uncommon.effectdatas.EffectData;

public class StringMatchesCondition extends StatCondition<EffectData> {

    public String string_id = "";
    public String string_key = "";

    public StringMatchesCondition(String key, String id) {
        super(key + "_is_" + id, "string_matches");
        this.string_id = id;
        this.string_key = key;
    }

    public StringMatchesCondition() {
        super("", "string_matches");
    }

    @Override
    public boolean can(EffectData event, StatData data, Stat stat) {

        return event.data.getString(string_key)
            .equals(string_id);
    }

    @Override
    public Class<? extends StatCondition> getSerClass() {
        return StringMatchesCondition.class;
    }

}
