package com.robertx22.age_of_exile.player_skills.items.alchemy;

import com.robertx22.age_of_exile.aoe_data.datapacks.models.IAutoModel;
import com.robertx22.age_of_exile.database.data.currency.base.IShapelessRecipe;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.player_skills.items.TieredItem;
import com.robertx22.age_of_exile.player_skills.items.foods.SkillItemTier;
import com.robertx22.age_of_exile.uncommon.interfaces.IAutoLocName;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;

public class CondensedSalvageEssence extends TieredItem implements IAutoLocName, IAutoModel, IShapelessRecipe {

    public CondensedSalvageEssence(SkillItemTier tier) {
        super(tier);
    }

    @Override
    public ShapelessRecipeJsonFactory getRecipe() {
        ShapelessRecipeJsonFactory fac = ShapelessRecipeJsonFactory.create(this, 1);
        fac.input(ModRegistry.TIERED.SALVAGED_ESSENCE_MAP.get(tier), 3);
        return fac.criterion("player_level", trigger());
    }

    @Override
    public String locNameForLangFile() {
        return "Condensed " + tier.word + " Essence";
    }

    @Override
    public String GUID() {
        return "mat/condensed_salvage/c" + tier.tier;
    }
}
