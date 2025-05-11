package ru.laimcraft.vanilla;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.ItemTypeKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import io.papermc.paper.registry.set.RegistryKeySet;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class spi implements io.papermc.paper.plugin.bootstrap.PluginBootstrap {

    public static TypedKey<Enchantment> pickaxe3x3Key;
    public static final HashMap<String, Enchantment> enchantments = new HashMap<>(1);

    @Override
    public JavaPlugin createPlugin(PluginProviderContext context) {
        return new Vanilla();
        //return io.papermc.paper.plugin.bootstrap.PluginBootstrap.super.createPlugin(context);
    }

    @Override
    public void bootstrap(BootstrapContext context) {
        spawnLog(context);
        registerPickaxe3x3(context);
        registerCommands(context);
        new CommandLoader(context);
    }

    public static void registerCommands(BootstrapContext context) {
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

    public static void registerPickaxe3x3(BootstrapContext context) {
        // Create the key manually
        pickaxe3x3Key = TypedKey.create(
                RegistryKey.ENCHANTMENT,
                Key.key("vanilla", "pickaxe3x3")
        );
        // Register on freeze
        context.getLifecycleManager().registerEventHandler(
                RegistryEvents.ENCHANTMENT.freeze().newHandler(event -> {
                    //RegistryKeySet<ItemType> registryKeySet = event.getOrCreateTag(ItemTypeTagKeys.PICKAXES);
                    event.registry().register(
                            pickaxe3x3Key,
                            builder -> builder
                                    .description(Component.text("Кирка 3х3"))
                                    .supportedItems(event.getOrCreateTag(ItemTypeTagKeys.PICKAXES))
                                    .weight(10)
                                    .maxLevel(1)
                                    .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(5, 1))
                                    .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(10, 1))
                                    .anvilCost(2)
                                    .activeSlots(EquipmentSlotGroup.HAND)
                    );
                })
        );
    }

    public static void spawnLog(BootstrapContext context) {
        for(int i = 0; i < 100; i++) {
            context.getLogger().info("Registering custom enchantment: pickaxe3x3");
            System.out.println("yes");
        }
    }
}
