package it.giuseppeimpesi.itemmerger.manager;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public interface Merger {

    void mergeItems(Event event, ItemStack first, ItemStack second, ItemStack result);

}
