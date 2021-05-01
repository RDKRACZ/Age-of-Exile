package com.robertx22.age_of_exile.aoe_data.database.stats;

import com.robertx22.age_of_exile.aoe_data.database.exile_effects.adders.BeneficialEffects;
import com.robertx22.age_of_exile.aoe_data.database.stat_conditions.StatConditions;
import com.robertx22.age_of_exile.aoe_data.database.stat_effects.StatEffects;
import com.robertx22.age_of_exile.database.data.stats.StatScaling;
import com.robertx22.age_of_exile.database.data.stats.datapacks.test.DataPackStatAccessor;
import com.robertx22.age_of_exile.database.data.stats.types.special.SpecialStats;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;
import com.robertx22.age_of_exile.uncommon.effectdatas.AttackType;
import com.robertx22.age_of_exile.uncommon.effectdatas.DamageEffect;
import com.robertx22.age_of_exile.uncommon.interfaces.IStatEffect;

import java.util.Arrays;

public class DataStats implements ISlashRegistryInit {

    public DataPackStatAccessor<ExileEffectContext> CHANCE_TO_GIVE_EFFECT_ON_SELF = DatapackStatBuilder
        .<ExileEffectContext>of(x -> "chance_to_give_" + x.id + "_to_self", x -> x.element)
        .addAllOfType(Arrays.asList(BeneficialEffects.BLOODLUST_CTX))
        .worksWithEvent(DamageEffect.ID)

        .setPriority(100)
        .setSide(IStatEffect.EffectSides.Source)

        .addCondition(StatConditions.IF_RANDOM_ROLL)
        .addCondition(StatConditions.IF_CRIT)
        .addCondition(StatConditions.ATTACK_TYPE_MATCHES.get(AttackType.ATTACK.id))

        .addEffect(StatEffects.GIVE_SELF_EFFECT.get(BeneficialEffects.BLOODLUST))

        .setLocName(x -> SpecialStats.format(
            "Your " + x.element.getIconNameFormat() + " Attacks have " + SpecialStats.VAL1 + "% chance of giving " + x.locname
        ))
        .setLocDesc(x -> "Chance to give effect")

        .modifyAfterDone(x -> {
            x.is_long = true;
            x.is_perc = true;
            x.scaling = StatScaling.NONE;
        })

        .build();

    @Override
    public void registerAll() {

    }
}
