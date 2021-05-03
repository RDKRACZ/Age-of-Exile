package com.robertx22.age_of_exile.uncommon.effectdatas;

import com.robertx22.age_of_exile.saveclasses.unit.ModifyResourceContext;
import com.robertx22.age_of_exile.saveclasses.unit.ResourceType;
import com.robertx22.age_of_exile.saveclasses.unit.ResourcesData;
import net.minecraft.entity.LivingEntity;

public class RegenEvent extends EffectEvent {

    public static String ID = "on_regen";

    @Override
    public String GUID() {
        return ID;
    }

    public ResourceType type;

    public RegenEvent(LivingEntity source, LivingEntity target, ResourceType type) {
        super(source, target);
        this.type = type;
    }

    @Override
    protected void activate() {

        if (data.isCanceled()) {
            return;
        }

        ModifyResourceContext ctx = new ModifyResourceContext(targetData, target, type,
            data.getNumber(),
            ResourcesData.Use.RESTORE
        );
        targetData.getResources()
            .modify(ctx);
    }
}
