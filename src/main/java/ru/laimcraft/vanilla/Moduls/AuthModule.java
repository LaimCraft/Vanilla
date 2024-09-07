package ru.laimcraft.vanilla.Moduls;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.laimcraft.vanilla.components.auth.AuthRESULT;
import ru.laimcraft.vanilla.components.auth.AuthType;
import ru.laimcraft.vanilla.Core;

public class AuthModule {
    private Core core;
    private PlayerJoinEvent event;
    private AuthRESULT RESULT = AuthRESULT.EXIT;

    public AuthModule(Core core, PlayerJoinEvent event, AuthType type) {
        this.core = core;
        this.event = event;

        switch (type) {
            case PlayerJoin:
                playerJoin();
                break;
        }
    }

    private void playerJoin() {
        String username = core.accounts.getUsernameByLogin(event.getPlayer().getName());
        if(username == null) {
            event.getPlayer().sendMessage(ChatColor.DARK_GREEN + "Для регистрации введите /register <Пароль>");
            RESULT = AuthRESULT.PlayerFirstJoin; return;}
        if(username.equalsIgnoreCase(event.getPlayer().getName())) {
            if(username.equals(event.getPlayer().getName())) {
                event.getPlayer().sendMessage(ChatColor.GREEN + "Для авторизации введите в чат /login <Пароль>");
                RESULT = AuthRESULT.PlayerJoin; return;}
            event.getPlayer().kickPlayer(ChatColor.RED + "Вы зашли под ником " + event.getPlayer().getName() + ", а ваш аккаунт зарегистрирован под ником " + username);
            RESULT = AuthRESULT.IncorrectUsername; return;}
        if(username.equals("ex")) {RESULT = AuthRESULT.EXIT; return;}
    }

    /*public AuthRESULT IncorrectUsername(String UserName) {
        String UserNameToLogin = new MySQLAccounts().getUsernameToLogin(UserName);
        if(UserNameToLogin == null || UserNameToLogin.isEmpty()) return AuthRESULT.PlayerJoin;
        if(UserNameToLogin.equalsIgnoreCase(UserName)) {
            if(UserNameToLogin.equals(UserName)) {
                return AuthRESULT.PlayerJoin; }
            return AuthRESULT.IncorrectUsername;
        } if(UserNameToLogin.equals("ex")) return AuthRESULT.EXIT;
        return AuthRESULT.EXIT;
    }*/

    public AuthRESULT getRESULT() {
        return RESULT;
    }
}
