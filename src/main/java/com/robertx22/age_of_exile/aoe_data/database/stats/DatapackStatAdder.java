package com.robertx22.age_of_exile.aoe_data.database.stats;

import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.database.data.stats.datapacks.stats.AddPerPercentOfOther;
import com.robertx22.age_of_exile.database.data.stats.datapacks.stats.ConvertFromOneToOtherStat;
import com.robertx22.age_of_exile.database.data.stats.datapacks.stats.MoreXPerYOf;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.Intelligence;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.Vitality;
import com.robertx22.age_of_exile.database.data.stats.types.generated.AttackDamage;
import com.robertx22.age_of_exile.database.data.stats.types.offense.SpellDamage;
import com.robertx22.age_of_exile.database.data.stats.types.resources.HealPower;
import com.robertx22.age_of_exile.database.data.stats.types.resources.blood.Blood;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.Health;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.Mana;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;

public class DatapackStatAdder implements ISlashRegistryInit {

    public static Stat HEAL_TO_SPELL_DMG = new AddPerPercentOfOther(HealPower.getInstance(), SpellDamage.getInstance());

    public static Stat BLOOD_PER_10VIT = new MoreXPerYOf(Vitality.INSTANCE, Blood.getInstance(), 10);
    public static Stat HEALTH_PER_10_INT = new MoreXPerYOf(Intelligence.INSTANCE, Health.getInstance(), 10);
    public static Stat MANA_PER_10_INT = new MoreXPerYOf(Intelligence.INSTANCE, Mana.getInstance(), 10);

    public static Stat CONVERT_HEALTH_TO_PHYS_DMG = new ConvertFromOneToOtherStat(Health.getInstance(), new AttackDamage(Elements.Physical));

    @Override
    public void registerAll() {

        HEAL_TO_SPELL_DMG.addToSerializables();
        BLOOD_PER_10VIT.addToSerializables();
        CONVERT_HEALTH_TO_PHYS_DMG.addToSerializables();
        HEALTH_PER_10_INT.addToSerializables();
        MANA_PER_10_INT.addToSerializables();

    }
}
