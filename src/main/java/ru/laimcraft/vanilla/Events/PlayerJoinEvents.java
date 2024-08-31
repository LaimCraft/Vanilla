package ru.laimcraft.vanilla.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.laimcraft.vanilla.Components.Auth.AuthRESULT;
import ru.laimcraft.vanilla.Components.Auth.AuthType;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.Moduls.AuthModule;

public class PlayerJoinEvents {
    private Core core;
    private PlayerJoinEvent event;
    public PlayerJoinEvents(Core core, PlayerJoinEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        event.setJoinMessage(null);
        AuthModule authModule = new AuthModule(core, event, AuthType.PlayerJoin);
        AuthRESULT authRESULT = authModule.getRESULT();
        if(authRESULT == AuthRESULT.EXIT) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Системная ошибка в модуле авторизации [n0001]");
            event.getPlayer().kickPlayer(ChatColor.RED + "Произошла системная ошибка, администрация уже уведомлена о ней \n Но вы можете так же сообщить о ней" +
                    "в нашем Discord канале: https://discord.gg/YkcDnBZFWj \n или на нашем GitHub https://github.com/LaimCraft/Vanilla "); return;}
        if(authRESULT == AuthRESULT.IncorrectUsername) return;
    }
}
