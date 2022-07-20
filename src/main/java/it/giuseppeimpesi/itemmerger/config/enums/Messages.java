package it.giuseppeimpesi.itemmerger.config.enums;

import it.giuseppeimpesi.itemmerger.ItemMerger;
import it.giuseppeimpesi.itemmerger.utils.ColorFormatter;
import org.bukkit.plugin.java.JavaPlugin;

public enum Messages {
    ALREADY_EXISTS("already_exists"),
    RECIPE_CREATED("recipe_created"),

    NO_ITEM_IN_HAND("no_item_in_hand"),
    FIRST_ITEM("first_item"),
    SECOND_ITEM("second_item"),
    RESULT_ITEM("result_item"),

    CONFIGURATION_RELOADED("configuration_reload"),

    SYNTAX_ERROR("syntax_error"),
    NO_PERMISSIONS("no_permissions");

    private final String path;
    private final ItemMerger plugin = JavaPlugin.getPlugin(ItemMerger.class);

    Messages(String path) {
        this.path = path;
    }

    public String getMessage() {
        return ColorFormatter.colorize(
                plugin.getManager().getConfigController().get("lang").getFileConfiguration().getString(path));
    }

}
