package it.giuseppeimpesi.itemmerger.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class ColorFormatter {
    public String colorize(final String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
