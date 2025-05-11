package ru.laimcraft.vanilla.custom.enchantments;

import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.PaperEnchantmentRegistryEntry;
import io.papermc.paper.registry.data.util.Conversions;
import io.papermc.paper.registry.set.RegistryKeySet;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.Range;

import java.util.List;

public class Pickaxe3x3Enchantment111111111111111111111 extends PaperEnchantmentRegistryEntry {

    public Pickaxe3x3Enchantment111111111111111111111(Conversions conversions, TypedKey<Enchantment> ignoredKey, net.minecraft.world.item.enchantment.@Nullable Enchantment internal) {
        super(conversions, ignoredKey, internal);
    }

    @Override
    public Component description() {
        return super.description();
    }

    @Override
    public RegistryKeySet<ItemType> supportedItems() {
        return super.supportedItems();
    }

    @Override
    public @Nullable RegistryKeySet<ItemType> primaryItems() {
        return super.primaryItems();
    }

    @Override
    public @Range(from = 1L, to = 1024L) int weight() {
        return super.weight();
    }

    @Override
    public @Range(from = 1L, to = 255L) int maxLevel() {
        return super.maxLevel();
    }

    @Override
    public EnchantmentCost minimumCost() {
        return super.minimumCost();
    }

    @Override
    public EnchantmentCost maximumCost() {
        return super.maximumCost();
    }

    @Override
    public @Range(from = 0L, to = 2147483647L) int anvilCost() {
        return super.anvilCost();
    }

    @Override
    public List<EquipmentSlotGroup> activeSlots() {
        return super.activeSlots();
    }

    @Override
    public RegistryKeySet<Enchantment> exclusiveWith() {
        return super.exclusiveWith();
    }
}