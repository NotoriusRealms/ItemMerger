package it.giuseppeimpesi.itemmerger.objects.recipe;

import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public interface IRecipeManager {

    Optional<Recipe> getRecipeByName(String name);

    void addRecipe(Recipe recipe);

    void setFirst(Recipe recipe, ItemStack itemStack);

    void setSecond(Recipe recipe, ItemStack itemStack);

    void setResult(Recipe recipe, ItemStack itemStack);

    boolean isComplete(Recipe recipe);

}
