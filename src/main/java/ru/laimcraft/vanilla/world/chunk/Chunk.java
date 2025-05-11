package ru.laimcraft.vanilla.world.chunk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.laimcraft.vanilla.DataBase;
import ru.laimcraft.vanilla.Vanilla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public record Chunk(org.bukkit.Chunk chunk) {

    public Chunk {
        Bukkit.getAsyncScheduler().runNow(Vanilla.instance, scheduledTask -> { // Загружаем данные из базы данных что бы продолжить загрузку
            loadChunk(chunk.getWorld().getName(), chunk().getX(), chunk.getZ());
        });
    }

    public void loadChunk(String world, int chunkX, int chunkZ) {
        try (Connection connection = DataBase.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `vanilla`.`blocks` WHERE world = ? AND chunkX = ? AND chunkZ = ?");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

            }
            return;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.format("%s[Ошибка MySQL] При попытке вызвать loadChunk произошла ошибка \n", ChatColor.RED));
            return;
        }
    }

}
