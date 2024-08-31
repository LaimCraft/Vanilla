package ru.laimcraft.vanilla.Components.Auth;

public enum AuthType {
    PlayerJoin("PlayerJoin"),
    PlayerQuit("PlayerQuit"),
    PlayerAuth("PlayerAuth"),
    PlayerRegister("PlayerRegister");

    private final String value;

    private AuthType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
