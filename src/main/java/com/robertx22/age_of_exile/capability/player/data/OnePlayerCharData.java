package com.robertx22.age_of_exile.capability.player.data;

import com.robertx22.age_of_exile.capability.bases.ICommonPlayerCap;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.PlayerCaps;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;

@Storable
public class OnePlayerCharData {

    // these are just for preview on the character screen
    @Store
    public int lvl = 1;
    @Store
    public String race = "";
    // these are just for preview on the character screen

    @Store
    public HashMap<PlayerCaps, CompoundTag> map = new HashMap<>();

    public void save(PlayerEntity player) {

        for (PlayerCaps cap : PlayerCaps.values()) {
            if (cap.shouldSaveToPlayerCharacter()) {
                CompoundTag nbt = new CompoundTag();
                cap.getCap(player)
                    .toTag(nbt);
                map.put(cap, nbt);
            }

        }

        this.lvl = Load.Unit(player)
            .getLevel();
        try {
            this.race = Load.Unit(player)
                .getRace()
                .GUID();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void load(PlayerEntity player) {

        for (PlayerCaps cap : PlayerCaps.values()) {
            if (cap.shouldSaveToPlayerCharacter()) {

                CompoundTag nbt = map.getOrDefault(cap, new CompoundTag());
                ICommonPlayerCap pcap = cap.getCap(player);
                pcap.fromTag(nbt);
                pcap.syncToClient(player);
            }
        }

    }

}
