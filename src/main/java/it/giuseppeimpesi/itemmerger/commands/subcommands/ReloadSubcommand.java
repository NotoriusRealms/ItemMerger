package it.giuseppeimpesi.itemmerger.commands.subcommands;

import it.giuseppeimpesi.itemmerger.commands.Subcommand;
import it.giuseppeimpesi.itemmerger.manager.PluginManager;
import org.bukkit.entity.Player;

import static it.giuseppeimpesi.itemmerger.config.enums.Messages.CONFIGURATION_RELOADED;

public final class ReloadSubcommand implements Subcommand {
    private final PluginManager manager;

    public ReloadSubcommand(PluginManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "itemmerger.reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        manager.getConfigController().reloadAll();
        manager.getRecipeManager().loadRecipesFromConfig();

        player.sendMessage(CONFIGURATION_RELOADED.getMessage());
    }
}
