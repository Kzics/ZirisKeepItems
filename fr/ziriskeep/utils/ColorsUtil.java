package fr.ziriskeep.utils;

import org.bukkit.ChatColor;

import java.util.function.Function;

public class ColorsUtil {


    public static Function<String,String> translate = (str)-> ChatColor.translateAlternateColorCodes('&',str);
}
