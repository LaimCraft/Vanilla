package ru.laimcraft.vanilla.Components;

import org.bukkit.Bukkit;
import ru.laimcraft.vanilla.Core;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Utils {
    private Core core;
    public Utils(Core core) {this.core=core;}

    public String getSHA512(String input){
        String toReturn = null; try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));} catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(e.toString());}return toReturn;}
}
