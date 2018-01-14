package ru.konungstvo.gm_tooltip;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import ru.konungstvo.gm_tooltip.core.CommonProxy;

@Mod(modid = GMLoreMod.MODID, version = GMLoreMod.VERSION)
public class GMLoreMod {
    public static final String MODID = "gm_lore";
    public static final String VERSION = "1.0";

    @SidedProxy(
            clientSide = "ru.konungstvo.gm_tooltip.core.ClientProxy",
            serverSide = "ru.konungstvo.gm_tooltip.core.CommonProxy"
    )
    protected static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
        proxy.onServerStart(event);
    }
}

