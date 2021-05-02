package com.robertx22.age_of_exile.uncommon.effectdatas;

import com.robertx22.age_of_exile.database.data.spells.components.Spell;
import com.robertx22.age_of_exile.saveclasses.item_classes.GearItemData;
import com.robertx22.age_of_exile.uncommon.datasaving.Gear;
import com.robertx22.age_of_exile.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.age_of_exile.uncommon.effectdatas.rework.EventData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class SpellDamageEffect extends DamageEffect {

    public SpellDamageEffect(LivingEntity source, LivingEntity target, int dmg, Spell spell) {
        super(null, source, target, dmg, spell.getConfig().style.getAttackType(), weapon(source, spell), spell.getConfig().style);

        this.data.setString(EventData.SPELL, spell.GUID());
    }

    private static WeaponTypes weapon(LivingEntity en, Spell spell) {

        try {
            if (spell.getConfig().style.getAttackType() != AttackType.spell) {

                ItemStack stack = en.getMainHandStack();

                GearItemData gear = Gear.Load(stack);

                if (gear != null) {
                    return gear.GetBaseGearType().weapon_type;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return WeaponTypes.none;

    }

    @Override
    protected void activate() {
        super.activate();
    }
}
