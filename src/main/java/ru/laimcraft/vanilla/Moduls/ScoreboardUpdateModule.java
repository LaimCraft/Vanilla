package ru.laimcraft.vanilla.Moduls;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.numbers.BlankFormat;
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import ru.laimcraft.utils.Utils;
import ru.laimcraft.vanilla.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.minecraft.world.scores.DisplaySlot.SIDEBAR;

public class ScoreboardUpdateModule {
    private Core core;
    private String title = ChatColor.GREEN + "Статус";

    public ScoreboardUpdateModule(Core core) {
        this.core = core; start();}
    private void start() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            Scoreboard scoreboard = new Scoreboard();
            Objective objective = scoreboard.addObjective(craftPlayer.getName(), ObjectiveCriteria.AIR, Component.nullToEmpty(title),
                    ObjectiveCriteria.RenderType.INTEGER, false, null);
            objective.setNumberFormat(BlankFormat.INSTANCE);
            Utils.sendPacket(new ClientboundSetObjectivePacket(objective, 0), craftPlayer);
            Utils.sendPacket(new ClientboundSetDisplayObjectivePacket(SIDEBAR, objective), craftPlayer);
            int i = 100;
            for(String s : getLine(player)) {
                Utils.sendPacket(new ClientboundSetScorePacket(s, player.getName(), i, Optional.ofNullable(
                        Component.nullToEmpty(s)
                ), Optional.ofNullable(objective.numberFormat())
                        ), craftPlayer);
            }
        }
    }

    private List<String> getLine(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("Баланс 1000");
        lines.add("Не баланс 2000");
        lines.add("У баланса 8000");
        lines.add("ХИХИ");
        lines.add("ТЫ ТЫ ТЫ ТЫ");
        return lines;
    }
}
