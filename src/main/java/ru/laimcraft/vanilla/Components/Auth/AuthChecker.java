package ru.laimcraft.vanilla.Components.Auth;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import ru.laimcraft.vanilla.Core;

public class AuthChecker {
    private boolean result = true;
    // true Игрок не авторизирован!
    // false Игрок авторизирован!
    public AuthChecker(Core core, PlayerMoveEvent event) {if(core.AuthPlayers.contains(event.getPlayer().getName().toLowerCase())) this.result = false;}
    public AuthChecker(Core core, PlayerDropItemEvent event) {if(core.AuthPlayers.contains(event.getPlayer().getName().toLowerCase())) this.result = false;}
    public AuthChecker(Core core, PlayerCommandPreprocessEvent event) {if(core.AuthPlayers.contains(event.getPlayer().getName().toLowerCase())) this.result = false;}
    public AuthChecker(Core core, AsyncPlayerChatEvent event) {if(core.AuthPlayers.contains(event.getPlayer().getName().toLowerCase())) this.result = false;}
    public AuthChecker(Core core, BlockPlaceEvent event) {if(core.AuthPlayers.contains(event.getPlayer().getName().toLowerCase())) this.result = false;}
    public AuthChecker(Core core, BlockBreakEvent event) {if(core.AuthPlayers.contains(event.getPlayer().getName().toLowerCase())) this.result = false;}
    public AuthChecker(Core core, PlayerInteractEvent event) {if(core.AuthPlayers.contains(event.getPlayer().getName().toLowerCase())) this.result = false;}
    public AuthChecker(Core core, InventoryClickEvent event) {if(!(event.getWhoClicked() instanceof Player player)) {this.result = false; return;}
        if(core.AuthPlayers.contains(player.getName().toLowerCase())) this.result = false;}
    public AuthChecker(Core core, EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            this.result = false;
            return;
        }
        if (!(event.getDamager() instanceof Player player2)) {
            this.result = false;
            return;
        }
        boolean a = core.AuthPlayers.contains(player.getName().toLowerCase());
        boolean b = core.AuthPlayers.contains(player2.getName().toLowerCase());
        if(a && b) {
            this.result = false;
            return;
        }
    }



    public boolean getResult() {
        return this.result;
    }
}
