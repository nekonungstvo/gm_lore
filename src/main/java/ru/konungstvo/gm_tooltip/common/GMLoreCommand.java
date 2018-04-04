package ru.konungstvo.gm_tooltip.common;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.Arrays;

public class GMLoreCommand extends CommandBase {
    public String getCommandName() {
        return "gm_lore";
    }

    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "gm_lore (set <text>| clear)";
    }

    private NBTTagCompound fetchOrCreateNBT(ItemStack itemStack) {
        NBTTagCompound nbt = itemStack.getTagCompound();

        if (nbt == null) {
            nbt = new NBTTagCompound();
            itemStack.setTagCompound(nbt);
        }

        return nbt;
    }

    private ItemStack fetchItemStack(ICommandSender sender) {
        if (!(sender instanceof EntityPlayerMP)) return null;
        return ((EntityPlayerMP) sender).getHeldItem();
    }

    private String joinArguments(ArrayList<String> arguments) {
        StringBuilder builder = new StringBuilder();
        for (String argument : arguments) {
            builder.append(argument);
            builder.append(" ");
        }
        return builder.toString();
    }

    private String popArgument(ArrayList<String> arguments) {
        String argument = arguments.get(0);
        arguments.remove(0);
        return argument;
    }

    private boolean validateInput(ArrayList<String> args) {
        return
                (args.size() >= 1) &&
                !(args.size() != 1 && args.get(0).equals("clear")) &&
                (args.get(0).equals("clear") || args.get(0).equals("set"));
    }

    public void processCommand(ICommandSender sender, String[] args) {
        ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(args));
        ItemStack itemStack = this.fetchItemStack(sender);

        if (itemStack == null) {
            sender.addChatMessage(new ChatComponentText("GM Lore: No item in hand!"));
            return;
        }

        if (!this.validateInput(arguments)) {
            sender.addChatMessage(new ChatComponentText("GM Lore: Wrong usage!"));
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            return;
        }

        String operation = this.popArgument(arguments);
        String description = this.joinArguments(arguments);
        if (operation.equals("clear")) description = "";

        fetchOrCreateNBT(itemStack).setString("gm_lore", description);
    }

    public int getRequiredPermissionLevel() {
        return 4;
    }

}