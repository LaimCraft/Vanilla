package ru.laimcraft.vanilla.components.regions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.commands.RegionCommand;

public class RegionMessages {
    public static int regionCreateSpawn = 256;
    public static int regionChunkSpawn = 8;
    public static int spawnRadius = 62;
    public static double chunkRent = 0.05d;
    public static int regionCreate = 128;
    public static int regionChunk = 1;

    public static void sendRegionCostMessage(Player player) {
        player.sendMessage(ChatColor.DARK_GREEN + "Цена создания региона зависит от того где вы его хотите создать");
        player.sendMessage(ChatColor.GOLD + "Есть территория спавна где цена на создание региона x2 и цена привата чанка x8");
        player.sendMessage(ChatColor.GOLD + "Цены на спавне:");
        player.sendMessage(ChatColor.AQUA + " Создание региона -> " + regionCreateSpawn + "mp");
        player.sendMessage(ChatColor.AQUA + " Приват чанка -> " + regionChunkSpawn + "mp");
        player.sendMessage(ChatColor.GOLD + "Цены вне спавна:");
        player.sendMessage(ChatColor.AQUA + " Создание региона -> " + regionCreate + "mp");
        player.sendMessage(ChatColor.AQUA + " Приват чанка -> " + regionChunk + "mp");
        player.sendMessage(ChatColor.GOLD + "Территория спавна это квадрат идущий в каждую сторону на 62 чанка(124 на 124 чанка)\n" +
                ChatColor.RED + "Приват каждый день поглащает по ("+chunkRent+"mp*Кол-во чанков) Если у не хватит маны то приват будет удалён!");
    }

    public static void sendHelpMessage(Player player) {
        player.sendMessage(ChatColor.GOLD + "Команды для упралвения регионами");
        player.sendMessage(ChatColor.YELLOW + " /region create <Название_на_англиском> -> Создать регион");
        player.sendMessage(ChatColor.YELLOW + " /region createCost -> Узнать сколько вы должны потратить мыны для создания региона");
        player.sendMessage(ChatColor.YELLOW + " /region chunk info -> Узнать информацию о чанке в котором вы находитесь");
        player.sendMessage(ChatColor.YELLOW + " /region chunk payday -> Цена которую вы будете платить за чанк каждый день");
        player.sendMessage(ChatColor.YELLOW + " /region chunk cost -> Цена добавления чанка в регион");
        player.sendMessage(ChatColor.YELLOW + " /region chunk add -> Добавить чанк в ваш регион");
        player.sendMessage(ChatColor.YELLOW + " /region chunk remove -> Удаление чанка из региона");
        player.sendMessage(ChatColor.RED + "Ванимение: Если вы удалите последний чанк вашего региона он исчезнет! \n" +
                "А так же если у вас не хватит маны на поддержание чанков в регионе он так же пропадёт!");
        player.sendMessage(ChatColor.YELLOW + " /region flag <flag> yes/no -> Включить/Отключить правило внутри региона");
        player.sendMessage(ChatColor.YELLOW + " /region flag info -> Узнать информацию об всех Флагах в вашем регионе");
        player.sendMessage(ChatColor.YELLOW + " /region flags -> Узнать список всех флагов и за что они отвечают");
        player.sendMessage(ChatColor.GREEN + " /region chunk view <Название региона> -> Включить эксперементальную функцию просмотра границ чанка");
        player.sendMessage(ChatColor.DARK_RED + " /region delete <Название региона> \n" +
                "Внимание: если вам нужно уменьшить размер региона используйте /region chunk remove!");
        player.sendMessage(ChatColor.RED + " /region ignore -> Отключает/Включить текст о том что вы в чужом регионе");
    }

    public static void sendRegionCreateMessage(Player player) {
        player.sendMessage(ChatColor.GOLD + " /region create <Название> -> Создать регион");
    }

    public static void sendChunkMessage(Player player) {
        player.sendMessage(ChatColor.YELLOW + " /region chunk info -> Узнать информацию о чанке в котором вы находитесь");
        player.sendMessage(ChatColor.YELLOW + " /region chunk payday -> Цена которую вы будете платить за чанк каждый день");
        player.sendMessage(ChatColor.YELLOW + " /region chunk cost -> Цена добавления чанка в регион");
        player.sendMessage(ChatColor.YELLOW + " /region chunk add -> Добавить чанк в ваш регион");
        player.sendMessage(ChatColor.YELLOW + " /region chunk remove -> Удаление чанка из региона");
        player.sendMessage(ChatColor.RED + "Ванимение: Если вы удалите последний чанк вашего региона он исчезнет! \n" +
                "А так же если у вас не хватит маны на поддержание чанков в регионе он так же пропадёт!");
    }

    public static void sendFlagMessage(Player player) {
        player.sendMessage(ChatColor.YELLOW + " /region flag <flag> yes/no -> Включить/Отключить правило внутри региона");
        player.sendMessage(ChatColor.YELLOW + " /region flag info -> Узнать информацию об всех Флагах в вашем регионе");
        player.sendMessage(ChatColor.YELLOW + " /region flags -> Узнать список всех флагов и за что они отвечают");
    }

    public static void sendDeleteMessage(Player player) {
        player.sendMessage(ChatColor.DARK_RED + " /region delete <Название региона> \n" +
                "Внимание: если вам нужно уменьшить размер региона используйте /region chunk remove!");
    }

    public static void createRegionErrorEX(Player player) {
        player.sendMessage(ChatColor.RED + "При попытке создания региона произошла ошибка обратитесь к администрации!");
    }

    public static void regionCreated(Player player) {
        player.sendMessage(ChatColor.DARK_GREEN + "Вы успешно создали регион!");
    }

    public static void regionNoCreated(Player player) {
        player.sendMessage(ChatColor.RED + "Такой регион уже существует!");
    }
}
