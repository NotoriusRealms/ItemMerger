package it.giuseppeimpesi.itemmerger.commands.subcommands;

import it.giuseppeimpesi.itemmerger.manager.PluginManager;
import it.giuseppeimpesi.itemmerger.commands.Subcommand;
import it.giuseppeimpesi.itemmerger.objects.recipe.Recipe;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

import static it.giuseppeimpesi.itemmerger.config.enums.Messages.*;

public final class ResultSubcommand implements Subcommand {

    private final PluginManager manager;

    public ResultSubcommand(PluginManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "itemmerger.result";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.getEquipment().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage(NO_ITEM_IN_HAND.getMessage());
            return;
        }

        Optional<Recipe> optional = manager.getRecipeManager().getRecipeByName(args[1]);

        if (!optional.isPresent())
            return;

        Recipe recipe = optional.get();

        final ItemStack item = player.getEquipment().getItemInMainHand().clone();
        manager.getRecipeManager().setResult(recipe, item);

        player.sendMessage(RESULT_ITEM.getMessage().replaceAll("%recipe_name%", recipe.getName()));
    }

}
