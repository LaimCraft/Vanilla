package ru.laimcraft.vanilla;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

import java.util.function.Predicate;

public class CommandLoader {

    public CommandLoader(BootstrapContext context) {
        context.getLifecycleManager()
                .registerEventHandler(LifecycleEvents.COMMANDS, event -> {
                    Commands commands = event.registrar();
                    //commands.register();
                    /*commands.register("home", ((commandSourceStack, strings) -> {}));
                    /*commands.register("sethome", ((commandSourceStack, strings) -> {}));
                    commands.register("chest", ((commandSourceStack, strings) -> {}));
                    commands.register("adminmode", ((commandSourceStack, strings) -> {}));
                    commands.register("fly", ((commandSourceStack, strings) -> {}));
                    commands.register("gm", ((commandSourceStack, strings) -> {}));
                    commands.register("stop", ((commandSourceStack, strings) -> {}));*/
                });
    }

    public static void register(String cmd) {
        Commands.literal("home")
                .executes(context -> {
                    //HomeCommand.onCommand
                    HomeCommand.onCommand(context);
                    return 1;}).build();

    }

    /*

        context.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            // получаем объект Commands-регистратора
            var commands = event.registrar();
            // регистрируем Brigadier-команду "mycmd"
            commands.register()
            commands.register(
                    Commands.literal("home")
                            .requires(ctx -> ctx.getSender().hasPermission("t"))
                            .executes(commandContext -> {
                                commandContext.getSource().getSender().sendMessage("hi");
                                return 1;
                            }).build());
        });
    * */
}
