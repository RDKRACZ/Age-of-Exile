package com.robertx22.age_of_exile.mmorpg.registers.client;

import com.robertx22.age_of_exile.mmorpg.registers.common.SlashContainers;
import com.robertx22.age_of_exile.player_skills.items.backpacks.BackpackScreen;
import com.robertx22.age_of_exile.vanilla_mc.blocks.runeword_station.RuneWordStationGui;
import com.robertx22.age_of_exile.vanilla_mc.blocks.salvage_station.GuiGearSalvage;
import net.minecraft.client.gui.ScreenManager;

public class ContainerGuiRegisters {

    public static void reg() {

        ScreenManager.register(SlashContainers.SALVAGE.get(), GuiGearSalvage::new);
        ScreenManager.register(SlashContainers.BACKPACK.get(), BackpackScreen::new);
        ScreenManager.register(SlashContainers.RUNEWORD.get(), RuneWordStationGui::new);

    }

}
