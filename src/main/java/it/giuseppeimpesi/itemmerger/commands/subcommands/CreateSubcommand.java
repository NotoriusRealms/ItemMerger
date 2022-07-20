package it.giuseppeimpesi.itemmerger.commands.subcommands;

import it.giuseppeimpesi.itemmerger.config.enums.Messages;
import it.giuseppeimpesi.itemmerger.manager.PluginManager;
import it.giuseppeimpesi.itemmerger.commands.Subcommand;
import it.giuseppeimpesi.itemmerger.objects.recipe.Recipe;
import org.bukkit.entity.Player;

import static it.giuseppeimpesi.itemmerger.config.enums.Messages.ALREADY_EXISTS;
import static it.giuseppeimpesi.itemmerger.config.enums.Messages.RECIPE_CREATED;

public final class CreateSubcommand implements Subcommand {

    private final PluginManager manager;

    public CreateSubcommand(PluginManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "itemmerger.create";
    }

    @Override
    public void perform(Player player, String[] args) {
        for (Recipe recipe : manager.getRecipeManager().getCachedRecipes().values()) {
            if (!recipe.getName().equals(args[1])) continue;

            player.sendMessage(ALREADY_EXISTS.getMessage());
            return;
        }

        manager.getRecipeManager().addRecipe(new Recipe(args[1]));
        player.sendMessage(RECIPE_CREATED.getMessage().replaceAll("%recipe_name%", args[1]));
    }
}
