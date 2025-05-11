package ru.laimcraft.vanilla;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class NewChestCommand extends LiteralCommandNode<CommandSourceStack> {

    public NewChestCommand(String literal, Command<CommandSourceStack> command, Predicate<CommandSourceStack> requirement, CommandNode<CommandSourceStack> redirect, RedirectModifier<CommandSourceStack> modifier, boolean forks) {
        super(literal, command, requirement, redirect, modifier, forks);
    }
}
