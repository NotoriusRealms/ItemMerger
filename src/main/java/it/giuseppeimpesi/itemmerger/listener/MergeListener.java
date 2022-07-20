package it.giuseppeimpesi.itemmerger.listener;

import it.giuseppeimpesi.itemmerger.manager.Merger;
import it.giuseppeimpesi.itemmerger.manager.PluginManager;
import it.giuseppeimpesi.itemmerger.objects.recipe.Recipe;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MergeListener implements Listener, Merger {
    private final PluginManager manager;

    public MergeListener(PluginManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMerge(InventoryClickEvent ev) {
        Player player = (Player) ev.getWhoClicked();

        if (player.getGameMode() != GameMode.SURVIVAL)
            return;

        ItemStack cursor = ev.getCursor();
        ItemStack clickedItem = ev.getCurrentItem();

        if (cursor == null || clickedItem == null)
            return;

        if (cursor.getType() == Material.AIR || clickedItem.getType() == Material.AIR)
            return;

        for (Recipe recipe : manager.getRecipeManager().getCachedRecipes().values()) {
            if (!recipe.isComplete()) continue;
            if (!player.hasPermission(recipe.getPermission())) continue;
            if (recipe.getBlacklistedWorlds().contains(player.getWorld())) continue;

            if (recipe.getFirst().equals(cursor) && recipe.getSecond().equals(clickedItem)) {
                mergeItems(ev, recipe.getFirst(), recipe.getSecond(), recipe.getResult());
                break;
            }
        }

    }

    @Override
    public void mergeItems(final Event event, final ItemStack first, final ItemStack second, final ItemStack result) {
        InventoryClickEvent inventoryClickEvent = (InventoryClickEvent) event;

        Player player = (Player) inventoryClickEvent.getWhoClicked();

        ItemStack cursor = inventoryClickEvent.getCursor();
        ItemStack clickedItem = inventoryClickEvent.getCurrentItem();

        if ((cursor.equals(first) && clickedItem.equals(second))) {
            cursor.setType(Material.AIR);
            clickedItem.setType(Material.AIR);

            player.setItemOnCursor(result);
        }
    }

}
