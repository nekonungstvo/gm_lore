package ru.konungstvo.gm_tooltip.core;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;

public class CommonProxy {
    public void init(FMLInitializationEvent event) { }

    public void onServerStart(FMLServerStartingEvent event) {
        MinecraftServer server = MinecraftServer.getServer();
        ServerCommandManager manager = (ServerCommandManager) server.getCommandManager();
        manager.registerCommand(new LoreCommand());
    }
}
