package ru.konungstvo.gm_tooltip.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import ru.konungstvo.gm_tooltip.GMLoreMod;
import ru.konungstvo.gm_tooltip.client.LoreGuiHandler;

public class CommonProxy {
    @Mod.Instance(GMLoreMod.MODID)
    private static GMLoreMod instance;

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new LoreGuiHandler());
    }

    public void onServerStart(FMLServerStartingEvent event) {
        MinecraftServer server = MinecraftServer.getServer();
        ServerCommandManager manager = (ServerCommandManager) server.getCommandManager();
        manager.registerCommand(new GMLoreCommand());
    }
}
