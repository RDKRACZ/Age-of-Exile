package com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases;

import com.robertx22.age_of_exile.capability.entity.EntityCap;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.Dexterity;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.Intelligence;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.Strength;
import com.robertx22.age_of_exile.database.data.stats.types.reduced_req.FlatIncreasedReq;
import com.robertx22.age_of_exile.database.data.stats.types.reduced_req.ReducedAllStatReqOnItem;
import com.robertx22.age_of_exile.saveclasses.ExactStatData;
import com.robertx22.age_of_exile.saveclasses.item_classes.GearItemData;

import java.util.List;

public class FinalizedGearStatReq {

    public int dexterity;
    public int intelligence;
    public int strength;

    public FinalizedGearStatReq(int dexterity, int intelligence, int strength) {
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.strength = strength;
    }

    public void calculate(GearItemData gear) {

        List<ExactStatData> stats = gear.GetAllStats(false, false);

        // first apply flat +x str req, then x% reduced requirements

        stats.forEach(x -> {
            if (x.getStat() instanceof FlatIncreasedReq) {
                FlatIncreasedReq reduce = (FlatIncreasedReq) x.getStat();

                dexterity = (int) reduce.getModifiedRequirement(Dexterity.INSTANCE, dexterity, x);

                intelligence = (int) reduce.getModifiedRequirement(Intelligence.INSTANCE, intelligence, x);

                strength = (int) reduce.getModifiedRequirement(Strength.INSTANCE, strength, x);

            }
        });

        stats
            .forEach(x -> {
                if (x.getStat() instanceof ReducedAllStatReqOnItem) {
                    ReducedAllStatReqOnItem reduce = (ReducedAllStatReqOnItem) x.getStat();

                    if (dexterity > 0) {
                        dexterity = (int) reduce.getModifiedRequirement(dexterity, x);
                    }
                    if (intelligence > 0) {
                        intelligence = (int) reduce.getModifiedRequirement(intelligence, x);
                    }
                    if (strength > 0) {
                        strength = (int) reduce.getModifiedRequirement(strength, x);
                    }
                }
            });
    }

    public boolean passesStatRequirements(EntityCap.UnitData data, GearItemData gear) {

        if (data.getUnit()
            .getStatsContainer()
            .isCalculating()) {
            if (data.getUnit()
                .getStatInCalculation(Dexterity.INSTANCE)
                .getFlatAverage() < dexterity) {
                return false;
            }
            if (data.getUnit()
                .getStatInCalculation(Intelligence.INSTANCE)
                .getFlatAverage() < intelligence) {
                return false;
            }
            if (data.getUnit()
                .getStatInCalculation(Strength.INSTANCE)
                .getFlatAverage() < strength) {
                return false;
            }
        } else {
            if (data.getUnit()
                .getCalculatedStat(Dexterity.INSTANCE)
                .getAverageValue() < dexterity) {
                return false;
            }
            if (data.getUnit()
                .getCalculatedStat(Intelligence.INSTANCE)
                .getAverageValue() < intelligence) {
                return false;
            }
            if (data.getUnit()
                .getCalculatedStat(Strength.INSTANCE)
                .getAverageValue() < strength) {
                return false;
            }
        }

        return true;
    }

}
