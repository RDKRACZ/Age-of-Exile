package com.robertx22.age_of_exile.aoe_data;

import com.robertx22.age_of_exile.aoe_data.database.affixes.Prefixes;
import com.robertx22.age_of_exile.aoe_data.database.affixes.Suffixes;
import com.robertx22.age_of_exile.aoe_data.database.base_gear_types.BaseGearsRegister;
import com.robertx22.age_of_exile.aoe_data.database.dim_configs.DimConfigs;
import com.robertx22.age_of_exile.aoe_data.database.entity_configs.EntityConfigs;
import com.robertx22.age_of_exile.aoe_data.database.exile_effects.adders.ExileEffects;
import com.robertx22.age_of_exile.aoe_data.database.gear_slots.GearSlots;
import com.robertx22.age_of_exile.aoe_data.database.gems.Gems;
import com.robertx22.age_of_exile.aoe_data.database.mob_affixes.MobAffixes;
import com.robertx22.age_of_exile.aoe_data.database.perks.Perks;
import com.robertx22.age_of_exile.aoe_data.database.runes.Runes;
import com.robertx22.age_of_exile.aoe_data.database.runewords.Runewords;
import com.robertx22.age_of_exile.aoe_data.database.spell_mods.SpellModifiers;
import com.robertx22.age_of_exile.aoe_data.database.spells.Spells;
import com.robertx22.age_of_exile.aoe_data.database.unique_gears.UniqueGearReg;
import com.robertx22.age_of_exile.database.registrators.Tiers;
import com.robertx22.age_of_exile.mmorpg.MMORPG;

public class GeneratedData {

    // as these only add serizables.
    // They shouldn't be needed at all to play the game.
    // If it errors without them, then that means i hardcoded something i shouldn't have
    public static void addAllObjectsToGenerate() {
        if (MMORPG.RUN_DEV_TOOLS) {

            new GearSlots().registerAll();
            new BaseGearsRegister().registerAll();
            new UniqueGearReg().registerAll();

            new ExileEffects().registerAll();

            new Tiers().registerAll();

            new Spells().registerAll(); // some stats are based on spells, so spells go first

            new Prefixes().registerAll();
            new Suffixes().registerAll();

            new MobAffixes().registerAll();
            new DimConfigs().registerAll();
            new EntityConfigs().registerAll();

            new Gems().registerAll();
            new Runes().registerAll();
            new Runewords().registerAll();
            new SpellModifiers().registerAll();
            new Perks().registerAll();
        }
    }
}
