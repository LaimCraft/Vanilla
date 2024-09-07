package ru.laimcraft.vanilla.components.auth;

public enum AuthRESULT {
    PlayerJoin("PlayerJoin"), // Игрок зашёл и при этом уже был зарегистрирован
    PlayerFirstJoin("PlayerFirstJoin"), // Игрок зашёл хотя ещё не зарегистрировался
    IncorrectUsername("PlayerIncorrectUsername"), // Выгнать игрока из-за неправильного имени пользователя
    EXIT("PlayerEXIT"); // Выгнать игрока из-за какой-то причины скорее всего системный баг

    private final String value;

    private AuthRESULT(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
