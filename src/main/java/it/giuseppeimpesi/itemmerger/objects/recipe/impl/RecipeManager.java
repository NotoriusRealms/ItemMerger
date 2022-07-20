package it.giuseppeimpesi.itemmerger.objects.recipe.impl;

import com.google.common.collect.Maps;
import it.giuseppeimpesi.itemmerger.manager.PluginManager;
import it.giuseppeimpesi.itemmerger.objects.recipe.IRecipeManager;
import it.giuseppeimpesi.itemmerger.objects.recipe.Recipe;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;

@Getter
public class RecipeManager implements IRecipeManager {
    private final PluginManager manager;

    private final Map<String, Recipe> cachedRecipes = Maps.newConcurrentMap();

    public RecipeManager(PluginManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Recipe> getRecipeByName(final String name) {
        for (Recipe recipe : cachedRecipes.values())
            if (recipe.getName().equals(name))
                return Optional.of(recipe);

        return Optional.empty();
    }

    @Override
    public void addRecipe(final Recipe recipe) {
        cachedRecipes.put(recipe.getName(), recipe);

        final FileConfiguration configuration = manager.getConfigController().get("recipes").getFileConfiguration();
        final ConfigurationSection recipeSection = configuration.createSection("recipes." + recipe.getName());

        recipeSection.set("permission", recipe.getName() + ".use");

        manager.getConfigController().get("recipes").save();
        manager.getRecipeManager().loadRecipesFromConfig();
    }

    @Override
    public void setFirst(final Recipe recipe, final ItemStack itemStack) {
        recipe.setFirst(itemStack);

        final FileConfiguration configuration = manager.getConfigController().get("recipes").getFileConfiguration();

        final ConfigurationSection recipeSection = configuration.getConfigurationSection("recipes." + recipe.getName());
        recipeSection.set("first_item", itemStack);

        manager.getConfigController().get("recipes").save();
        manager.getRecipeManager().loadRecipesFromConfig();
    }

    @Override
    public void setSecond(final Recipe recipe, final ItemStack itemStack) {
        recipe.setSecond(itemStack);

        final FileConfiguration configuration = manager.getConfigController().get("recipes").getFileConfiguration();

        final ConfigurationSection recipeSection = configuration.getConfigurationSection("recipes." + recipe.getName());
        recipeSection.set("second_item", itemStack);

        manager.getConfigController().get("recipes").save();
        manager.getRecipeManager().loadRecipesFromConfig();
    }

    @Override
    public void setResult(final Recipe recipe, final ItemStack itemStack) {
        recipe.setResult(itemStack);

        final FileConfiguration configuration = manager.getConfigController().get("recipes").getFileConfiguration();

        final ConfigurationSection recipeSection = configuration.getConfigurationSection("recipes." + recipe.getName());
        recipeSection.set("result_item", itemStack);

        manager.getConfigController().get("recipes").save();
        manager.getRecipeManager().loadRecipesFromConfig();
    }

    @Override
    public boolean isComplete(final Recipe recipe) {
        return recipe.isComplete();
    }

    public void loadRecipesFromConfig() {
        cachedRecipes.clear();
        final FileConfiguration configuration = manager.getConfigController().get("recipes").getFileConfiguration();

        configuration.getConfigurationSection("recipes").getKeys(false).forEach(recipeName -> {
            final Recipe recipe = new Recipe(recipeName);
            final ConfigurationSection section = configuration.getConfigurationSection("recipes." + recipeName);

            recipe.setPermission(section.getString("permission"));
            section.getStringList("blacklisted_worlds").forEach(worldName -> {
                World blacklistedWorld = Bukkit.getWorld(worldName);
                if (blacklistedWorld != null)
                    recipe.getBlacklistedWorlds().add(Bukkit.getWorld(worldName));
            });

            recipe.setFirst(section.getItemStack("first_item"));
            recipe.setSecond(section.getItemStack("second_item"));
            recipe.setResult(section.getItemStack("result_item"));

            cachedRecipes.put(recipeName, recipe);
        });
    }
}
