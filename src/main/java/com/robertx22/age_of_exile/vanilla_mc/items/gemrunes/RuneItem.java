package com.robertx22.age_of_exile.vanilla_mc.items.gemrunes;

import com.robertx22.age_of_exile.aoe_data.datapacks.models.IAutoModel;
import com.robertx22.age_of_exile.aoe_data.datapacks.models.ItemModelManager;
import com.robertx22.age_of_exile.database.base.CreativeTabs;
import com.robertx22.age_of_exile.database.data.BaseRuneGem;
import com.robertx22.age_of_exile.database.data.StatModifier;
import com.robertx22.age_of_exile.database.data.currency.base.ICurrencyItemEffect;
import com.robertx22.age_of_exile.database.data.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.age_of_exile.database.data.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.age_of_exile.database.data.currency.loc_reqs.gems.NoDuplicateRunes;
import com.robertx22.age_of_exile.database.data.currency.loc_reqs.gems.SocketLvlNotHigherThanItemLvl;
import com.robertx22.age_of_exile.database.data.currency.loc_reqs.item_types.GearReq;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType;
import com.robertx22.age_of_exile.database.data.runes.Rune;
import com.robertx22.age_of_exile.database.data.stats.types.generated.AttackDamage;
import com.robertx22.age_of_exile.database.registry.ExileDB;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_parts.SocketData;
import com.robertx22.age_of_exile.saveclasses.item_classes.GearItemData;
import com.robertx22.age_of_exile.uncommon.datasaving.Gear;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import com.robertx22.age_of_exile.uncommon.interfaces.IAutoLocName;
import com.robertx22.library_of_exile.registry.IGUID;
import com.robertx22.library_of_exile.utils.RandomUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class RuneItem extends BaseGemRuneItem implements IGUID, IAutoModel, IAutoLocName, ICurrencyItemEffect {
    @Override
    public StationType forStation() {
        return StationType.SOCKET;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Misc;
    }

    @Override
    public Text getName(ItemStack stack) {
        return new TranslatableText(this.getTranslationKey()).formatted(Formatting.GOLD);
    }

    @Override
    public String locNameLangFileGUID() {
        return Registry.ITEM.getId(this)
            .toString();
    }

    @Override
    public String locNameForLangFile() {
        return type.locName + " Rune";
    }

    @Override
    public void generateModel(ItemModelManager manager) {
        manager.generated(this);
    }

    public RuneType type;

    public RuneItem(RuneType type) {
        super(new Settings().maxCount(16)
            .group(CreativeTabs.Runes));
        this.type = type;

        this.weight = type.weight;
    }

    @Override
    public String GUID() {
        return "runes/" + type.id;
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack currency) {

        RuneItem ritem = (RuneItem) currency.getItem();
        Rune rune = ritem.getRune();
        GearItemData gear = Gear.Load(stack);

        SocketData socket = new SocketData();
        socket.rune = rune.identifier;
        socket.lvl = gear.lvl;
        socket.perc = RandomUtils.RandomRange(0, 100);

        gear.sockets.sockets.add(socket);

        ExileDB.Runewords()
            .getList()
            .forEach(x -> {
                if (x.HasRuneWord(gear)) {
                    gear.sockets.word = x.GUID();
                    gear.sockets.word_perc = RandomUtils.RandomRange(0, 100);
                }
            });

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, SimpleGearLocReq.HAS_EMPTY_SOCKETS, new NoDuplicateRunes(), new SocketLvlNotHigherThanItemLvl());
    }

    public static StatModifier dmg(Elements ele) {
        return new
            StatModifier(0.5F, 1F, new AttackDamage(ele), ModType.FLAT);
    }

    public enum RuneType {
        YUN(100, "yun", "Yun", 0.75F),
        NOS(1000, "nos", "Nos", 0.2F),

        MOS(1000, "mos", "Mos", 0.2F),

        ITA(1000, "ita", "Ita", 0.2F),

        CEN(1000, "cen", "Cen", 0.2F),
        FEY(1000, "fey", "Fey", 0.2F),

        DOS(1000, "dos", "Dos", 0.2F),

        ANO(1000, "ano", "Ano", 0.2F),

        TOQ(1000, "toq", "Toq", 0.2F),

        ORU(500, "oru", "Oru", 0.6F),

        WIR(200, "wir", "Wir", 0.7F),

        ENO(1000, "eno", "Eno", 0.5F),

        HAR(1000, "har", "Har", 0.5F),

        XER(1000, "xer", "Xer", 0.5F);

        public String id;
        public String locName;
        public float lvl;
        public int weight;

        RuneType(int weight, String id, String locName, float lvl) {
            this.id = id;
            this.locName = locName;
            this.lvl = lvl;
            this.weight = weight;
        }

    }

    @Override
    public float getStatValueMulti() {
        return 1;
    }

    @Override
    public BaseRuneGem getBaseRuneGem() {
        return getRune();
    }

    public Rune getRune() {
        return ExileDB.Runes()
            .get(type.id);
    }

    @Override
    public List<StatModifier> getStatModsForSerialization(BaseGearType.SlotFamily family) {
        return Arrays.asList();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {

        try {

            tooltip.addAll(getBaseTooltip());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
