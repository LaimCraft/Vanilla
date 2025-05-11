package ru.laimcraft.vanilla.custom.blocks;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Interaction;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.DataBase;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.Worlds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
/*
public class CraftBox implements Listener {

    public static final Material defaultMaterial = Material.MOSSY_STONE_BRICKS;
    public static final NamespacedKey blockPlaceItemType = new NamespacedKey("vanilla", "block_place_item_type");
    public static final NamespacedKey logic = new NamespacedKey("vanilla", "logic");
    public static final NamespacedKey eastLogic = new NamespacedKey("vanilla", "easy_logic");
    public static final NamespacedKey noDeleteKey = new NamespacedKey("vanilla", "no_delete_entity");
    public static final NamespacedKey mainBlockPosition = new NamespacedKey("vanilla", "main_block_position");
    public static final NamespacedKey mainBlockPositionX = new NamespacedKey("vanilla", "main_block_position_x");
    public static final NamespacedKey mainBlockPositionY = new NamespacedKey("vanilla", "main_block_position_y");
    public static final NamespacedKey mainBlockPositionZ = new NamespacedKey("vanilla", "main_block_position_z");

    public CraftBox() {
        Bukkit.getPluginManager().registerEvents(this, Vanilla.instance);
    }

    @EventHandler
    private void blockPlaceEvent(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if(item.getType() != defaultMaterial) return;
        switch (item.getPersistentDataContainer().get(blockPlaceItemType, PersistentDataType.STRING).toLowerCase()) {
            case "craft_box":
                place(event, event.getPlayer().getFacing(), event.getBlock().getLocation());
                return;
            case null:
                return;
            default:
                return;
        }
    }

    public void place(BlockPlaceEvent event, BlockFace face, Location location) {
        CraftWorld craftWorld = (CraftWorld) location.getWorld();
        ServerLevel level = craftWorld.getHandle();
        Block block = location.getBlock();
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();

        if(block.getType() != Material.AIR) {
            event.setCancelled(true);
            return;
        }

        if(getBlock(craftWorld.getName(), x, y, z) != null) {
            event.setCancelled(true);
            return;
        }

        // Настройка Interaction
        Interaction interaction = new Interaction(EntityType.INTERACTION, level);
        interaction.setPos(x, y, z);
        float width = 1.01F;
        float height = 1.01F;
        interaction.setWidth(width);
        interaction.setHeight(height);

        setRotateAndMixing(face, interaction, location, height, width, x, z);

        level.addFreshEntity(interaction);
        org.bukkit.entity.Interaction bukkitInteraction = (org.bukkit.entity.Interaction) interaction.getBukkitEntity();
        bukkitInteraction.getPersistentDataContainer().set(noDeleteKey, PersistentDataType.BOOLEAN, true);
        bukkitInteraction.getPersistentDataContainer().set(mainBlockPosition, PersistentDataType.BOOLEAN, true);
        bukkitInteraction.getPersistentDataContainer().set(mainBlockPositionX, PersistentDataType.INTEGER, x);
        bukkitInteraction.getPersistentDataContainer().set(mainBlockPositionY, PersistentDataType.INTEGER, y);
        bukkitInteraction.getPersistentDataContainer().set(mainBlockPositionZ, PersistentDataType.INTEGER, z);
        bukkitInteraction.getPersistentDataContainer().set(eastLogic, PersistentDataType.STRING, "craft_box");

        // Конец настройки Interaction

        // Код для добавления блока в базу данных

        // CODE------
        // Конец кода для добавления блока в базу данных




    }

    public String addBlock(String world, int x, int y, int z, String type) {
        try (Connection connection = DataBase.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `vanilla`.`blocks` WHERE `world` = ? and `x` = ? and `y` = ? and `z` = ?;");
            ps.setString(1, world);
            ps.setInt(2, x);
            ps.setInt(3, y);
            ps.setInt(4, z);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("type");
            } return null;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(e.getMessage());
            return null;
        }
    }

    public String getBlock(String world, int x, int y, int z) {
        try (Connection connection = DataBase.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `vanilla`.`blocks` WHERE `world` = ? and `x` = ? and `y` = ? and `z` = ?;");
            ps.setString(1, world);
            ps.setInt(2, x);
            ps.setInt(3, y);
            ps.setInt(4, z);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("type");
            } return null;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(e.getMessage());
            return null;
        }
    }

    public void removeBlock(String world, int x, int y, int z) {
        try (Connection connection = DataBase.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `vanilla`.`blocks` WHERE `world` = ? and `x` = ? and `y` = ? and `z` = ?;");
            ps.setString(1, world);
            ps.setInt(2, x);
            ps.setInt(3, y);
            ps.setInt(4, z);
            ps.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(e.getMessage());
        }
    }


    public static void setRotateAndMixing(BlockFace placeSide, Interaction interaction, Location location, float height, float width, float x, float z) {

        x = x + (height / 2);
        z = z + (width / 2);

        float mixingX = 0f;
        float mixingZ = 0f;

        switch (placeSide) {
            case EAST:
                interaction.setRot(0f, 0f);
                mixingX += x;
                mixingZ += z;
                break;
            case SOUTH:
                interaction.setRot(90f, 0f);
                mixingX++;

                mixingX -= x;
                mixingZ += z;
                break;
            case WEST:
                interaction.setRot(180f, 0f);
                mixingX++;
                mixingZ++;

                mixingX -= x;
                mixingZ -= z;
                break;
            case NORTH:
                interaction.setRot(-90f, 0f);
                mixingZ++;

                mixingX += x;
                mixingZ -= z;
                break;
            default:
                break;
        }

        interaction.setPos(location.getX() + mixingX, location.getY(), location.getZ() + mixingZ);
    }

}
*/