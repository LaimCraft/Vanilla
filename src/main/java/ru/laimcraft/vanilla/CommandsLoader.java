package ru.laimcraft.vanilla;

import ru.laimcraft.vanilla.Commands.*;

import java.util.Objects;

public class CommandsLoader {
    private Core core;
    public CommandsLoader(Core core) {
        Objects.requireNonNull(core.getCommand("login")).setExecutor(new LoginCommand(core));
        Objects.requireNonNull(core.getCommand("register")).setExecutor(new RegisterCommand(core));
        Objects.requireNonNull(core.getCommand("chest")).setExecutor(new ChestCommand(core));
        Objects.requireNonNull(core.getCommand("coins")).setExecutor(new CoinsCommand(core));
        Objects.requireNonNull(core.getCommand("vanish")).setExecutor(new VanishCommand(core));
        //Objects.requireNonNull(core.getCommand("changepassword")).setExecutor(new ChangePasswordCommand(core));
    }
}
