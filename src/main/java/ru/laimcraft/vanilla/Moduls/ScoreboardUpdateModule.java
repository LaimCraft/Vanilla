package ru.laimcraft.vanilla.Moduls;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardUpdateModule {
    //private Vanilla core;
    private String title = ChatColor.GREEN + "Статус";

   // public ScoreboardUpdateModule(Vanilla core) {
        //this.core = core; start();}
    private void start() {
        /*for(Player player : Bukkit.getOnlinePlayers()) {
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
        }*/
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
