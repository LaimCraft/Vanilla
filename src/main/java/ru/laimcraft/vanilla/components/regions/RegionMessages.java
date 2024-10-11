package ru.laimcraft.vanilla.components.regions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.commands.RegionCommand;
import ru.laimcraft.vanilla.database.mysql.MySQLChunks;
import ru.laimcraft.vanilla.database.mysql.MySQLRegions;

import java.util.List;

public class RegionMessages {
    public static int regionCreateSpawn = 64;
    public static int regionChunkSpawn = 16;
    public static int spawnRadius = 62;
    public static double chunkRent = 0.1d;
    public static int regionCreate = 128;
    public static int regionChunk = 1;

    public static void sendRegionCostMessage(Player player) {
        player.sendMessage(ChatColor.RED + "Информация об цене привата устарела! " + "create " + regionCreateSpawn + " new Chunk " + regionChunkSpawn);
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

    public static void regionError(Player player, String debugInfo) {
        player.sendMessage(ChatColor.RED + "При попытке выполнения команды произошла обшибка обратитесь к администрации!");
        player.sendMessage(ChatColor.GOLD + "debugInfo: " + debugInfo);
    }

    public static void regionCreated(Player player) {
        player.sendMessage(ChatColor.DARK_GREEN + "Вы успешно создали регион!");
    }

    public static void regionNoCreated(Player player) {
        player.sendMessage(ChatColor.RED + "Такой регион уже существует!");
    }

    public static void regionOtherChunk(Player player) {
        player.sendMessage(ChatColor.RED + "Такой региона не существует но вы пытаетесь заприватить чужой чанк попробуйте где то в другом месте!");
    }

    public static void regionCreateNoMP(Player player, int mp) {
        player.sendMessage(ChatColor.DARK_AQUA + "У вас недостаточно маны для создания региона вам нужно " + mp);
    }

    public static void chunkCreateNoMP(Player player, int mp) {
        player.sendMessage(ChatColor.DARK_AQUA + "У вас недостаточно маны для привата чанка вам нужно " + mp);
    }

    public static void payDayMessage(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "Каждый ваш регион поглощает ману для своего существования оно зависит от кол-ва чанков которые вы заприватили");
        player.sendMessage(ChatColor.AQUA + "На данный момент цена за поддержание привата одного чанка состояляет " + chunkRent + "" +
                "\nP.s. Цена не будет выходить из диапазона 0.1-0.01 ед. маны в день(Если экономика маны не будет переделана)");
        int chunkAmount = 0;
        double mp = 0;
        List<String> regions = MySQLRegions.getRegions(player.getName());
        if(regions.size() == 0) {
            player.sendMessage(ChatColor.DARK_GRAY + "Ваше потребление маны в день: " + mp + "mp");
        return;}
        for(String region : regions) {
            chunkAmount = chunkAmount + MySQLChunks.getChunks(region).size();}
        mp = chunkAmount * chunkRent;
        player.sendMessage(ChatColor.DARK_GRAY + "Ваше потребление маны в день: " + mp + "mp");
    }

    public static void chunkCost(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "Цена привата одного чанка состовляет " + regionChunkSpawn + "ед. маны");
    }

    public static void chunkAddMessage(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "Для привата чанка необходимо " + regionChunkSpawn + "ед. маны");
        player.sendMessage(ChatColor.GOLD + "Введите /region chunk add <Название региона>");
    }

    public static void regionNo(Player player) {
        player.sendMessage(ChatColor.RED + "Региона с таким названием не существует!");
    }
}
