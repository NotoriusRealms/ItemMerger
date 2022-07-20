package it.giuseppeimpesi.itemmerger.commands.subcommands;

import it.giuseppeimpesi.itemmerger.commands.Subcommand;
import it.giuseppeimpesi.itemmerger.manager.PluginManager;
import it.giuseppeimpesi.itemmerger.utils.ColorFormatter;
import org.bukkit.entity.Player;

public final class ListSubcommand implements Subcommand {
    private final PluginManager manager;

    public ListSubcommand(PluginManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "itemmerger.list";
    }

    @Override
    public void perform(Player player, String[] args) {
        for (String recipeName : manager.getRecipeManager().getCachedRecipes().keySet())
            player.sendMessage(ColorFormatter.colorize("&8&l» &e" + recipeName));
    }
}
