package com.robertx22.age_of_exile.aoe_data.database.affixes.adders;

import com.robertx22.age_of_exile.aoe_data.database.affixes.AffixBuilder;
import com.robertx22.age_of_exile.database.data.StatModifier;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType.SlotTag;
import com.robertx22.age_of_exile.database.data.stats.types.misc.MoreSocketsStat;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import com.robertx22.library_of_exile.registry.ExileRegistryInit;

public class SocketAffixes implements ExileRegistryInit {

    @Override
    public void registerAll() {

        AffixBuilder.Normal("jeweled")
            .Named("Jeweled")
            .stats(new StatModifier(1, 1, MoreSocketsStat.getInstance(), ModType.FLAT))
            .includesTags(SlotTag.jewelry_family, SlotTag.armor_family, SlotTag.weapon_family, SlotTag.offhand_family)
            .AllowDuplicatesOnSameItem()
            .Weight(3000)
            .Prefix()
            .Build();

        AffixBuilder.Normal("socket_suff")
            .Named("Of Sockets")
            .stats(new StatModifier(1, 1, MoreSocketsStat.getInstance(), ModType.FLAT))
            .includesTags(SlotTag.jewelry_family, SlotTag.armor_family, SlotTag.weapon_family, SlotTag.offhand_family)
            .AllowDuplicatesOnSameItem()
            .Weight(3000)
            .Suffix()
            .Build();

    }
}
