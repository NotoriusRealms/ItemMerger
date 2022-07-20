package it.giuseppeimpesi.itemmerger.objects.recipe;

import com.google.common.collect.Lists;
import lombok.Data;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
public final class Recipe {
    private final String name;

    private String permission;
    private List<World> blacklistedWorlds = Lists.newArrayList();

    private ItemStack first;
    private ItemStack second;

    private ItemStack result;

    public Recipe(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return first != null && second != null && result != null;
    }
}
