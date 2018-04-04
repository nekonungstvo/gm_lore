package ru.konungstvo.gm_tooltip.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import ru.konungstvo.gm_tooltip.GMLoreMod;

public class CommonProxy {
    @Mod.Instance(GMLoreMod.MODID)
    private static GMLoreMod instance;

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new LoreGuiHandler());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onServerStart(FMLServerStartingEvent event) {
        MinecraftServer server = MinecraftServer.getServer();
        ServerCommandManager manager = (ServerCommandManager) server.getCommandManager();
        manager.registerCommand(new GMLoreCommand());
    }

    @SubscribeEvent
    public void test(ServerChatEvent event) {
        EntityPlayer player = event.player;
        player.openGui(
                instance,
                0,
                player.getEntityWorld(),
                (int) player.posX,
                (int) player.posY,
                (int) player.posZ
        );
    }
}
