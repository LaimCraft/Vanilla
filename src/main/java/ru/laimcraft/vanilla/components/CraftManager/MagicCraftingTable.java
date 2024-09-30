package ru.laimcraft.vanilla.components.CraftManager;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

import java.util.HashMap;
import java.util.Map;

public class MagicCraftingTable {
    private Inventory magicCraftingTable;
    private Player player;
    private Location location;
    public MagicCraftingTable(Player player, Location location) {
        this.player = player;
        this.location = location;
        location.setX(location.getX()+0.5d);
        location.setY(location.getY()+1);
        location.setZ(location.getZ()+0.5d);
        magicCraftingTable = new MagicCraftingTableMenu().getInventory();
        player.openInventory(magicCraftingTable);
    }

    public void close() {
        ItemStack air = new ItemStack(Material.AIR);
        for(int i = 0; i!=11; i++) {
            magicCraftingTable.setItem(i, air);}
        for(int i = 16; i!=20; i++) {
            magicCraftingTable.setItem(i, air);}
        for(int i = 25; i!=29; i++) {
            magicCraftingTable.setItem(i, air);}
        for(int i = 34; i!=38; i++) {
            magicCraftingTable.setItem(i, air);}
        for(int i = 43; i!=54; i++) {
            magicCraftingTable.setItem(i, air);}
        HashMap<Material, Integer> items = new HashMap<>();
        for(int i = 0; i!=54; i++) {
            ItemStack item = magicCraftingTable.getItem(i);
            if(item == null) continue;
            if(item.getType() == Material.AIR) continue;
            if(!Vanilla.craftMaterialAllowed.getMaterials().contains(item.getType())) {
                location.getWorld().dropItem(location, item);
                continue;
            }
            if(!items.containsKey(item.getType())) { items.put(item.getType(), item.getAmount()); continue; }
            items.replace(item.getType(), items.get(item.getType()) + item.getAmount());
        }
        if(Vanilla.crafts.crafts.contains(items)) {
            String craftResult = Vanilla.crafts.craftList.get(items);
            switch (craftResult) {
                case "health":
                    PlayerStatus status = Vanilla.players.get(player.getName());
                    if(status.getMaxHP() >= (status.getLevel() * 20)) {
                        player.sendMessage(ChatColor.RED + "На вашем уровне вы не можете набрать больше " + (status.getLevel() * 20) + "ХП!");
                        dropItems(items);
                        return;}
                    if(status.getXP() < 5000) {
                        player.sendMessage(ChatColor.RED + "У вас недостаточно опыта для крафта");
                        dropItems(items);
                    return;}
                    status.removeXP(5000);
                    status.addMaxHP(1);
                    return;
                case "elytra":
                    ItemStack itemStack = new ItemStack(Material.ELYTRA);
                    itemStack.addEnchantment(Enchantment.UNBREAKING, 3);
                    itemStack.addEnchantment(Enchantment.MENDING, 1);
                    dropItem(itemStack);
                    return;
                default:
                    return;
            }
        }
        dropItems(items);
    }

    private void dropItems(HashMap<Material, Integer> items) {
        for(Map.Entry<Material, Integer> entry : items.entrySet()) {
            Material material = entry.getKey();
            Integer amount = entry.getValue();
            ItemStack item = new ItemStack(material);
            boolean toNoll = true;
            while(toNoll) {
                if(amount <= material.getMaxStackSize()) {
                    item.setAmount(amount);
                    location.getWorld().dropItem(location, item);
                    toNoll = false;
                } else {
                    amount = amount - material.getMaxStackSize();
                    item.setAmount(material.getMaxStackSize());
                    location.getWorld().dropItem(location, item);
                }
            }
        }
    }

    private void dropItem(ItemStack item) {
        location.getWorld().dropItem(location, item);
    }
}
