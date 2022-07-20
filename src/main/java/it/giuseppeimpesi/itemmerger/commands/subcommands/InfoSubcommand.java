package it.giuseppeimpesi.itemmerger.commands.subcommands;

import it.giuseppeimpesi.itemmerger.commands.Subcommand;
import it.giuseppeimpesi.itemmerger.manager.PluginManager;
import it.giuseppeimpesi.itemmerger.objects.recipe.Recipe;
import it.giuseppeimpesi.itemmerger.utils.ColorFormatter;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Optional;

public final class InfoSubcommand implements Subcommand {
    private final PluginManager manager;

    public InfoSubcommand(PluginManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "itemmerger.info";
    }

    @Override
    public void perform(Player player, String[] args) {
        Optional<Recipe> optional = manager.getRecipeManager().getRecipeByName(args[1]);

        if (!optional.isPresent())
            return;

        Recipe recipe = optional.get();

        player.sendMessage(ColorFormatter.colorize("&e&lInformation"));
        player.sendMessage(ColorFormatter.colorize(" &8» &7Name: &f" + recipe.getName()));
        player.sendMessage(ColorFormatter.colorize(" &8» &7Permission: &f" + recipe.getPermission()));
        player.sendMessage(ColorFormatter.colorize(" &8» &7Blacklisted Worlds: "));
        for (World blacklistedWorld : recipe.getBlacklistedWorlds())
            player.sendMessage(ColorFormatter.colorize("  &8- &7" + blacklistedWorld.getName()));

        player.sendMessage(ColorFormatter.colorize(" &8» &71st item: &f" + recipe.getFirst().getType()));
        player.sendMessage(ColorFormatter.colorize(" &8» &72nd item: &f" + recipe.getSecond().getType()));
        player.sendMessage(ColorFormatter.colorize(" &8» &7Result item: &f" + recipe.getResult().getType()));
    }
}
