package it.giuseppeimpesi.itemmerger.manager;

import it.giuseppeimpesi.itemmerger.ItemMerger;
import it.giuseppeimpesi.itemmerger.config.ConfigurationFile;
import it.giuseppeimpesi.itemmerger.config.controller.ConfigurationFileController;
import it.giuseppeimpesi.itemmerger.objects.recipe.impl.RecipeManager;
import lombok.Getter;

@Getter
public class PluginManager {
    private final ItemMerger plugin;

    private final ConfigurationFileController configController;
    private final RecipeManager recipeManager;

    public PluginManager(ItemMerger plugin) {
        this.plugin = plugin;

        configController = new ConfigurationFileController();
        initializeConfiguration();

        recipeManager = new RecipeManager(this);
        recipeManager.loadRecipesFromConfig();
    }

    private void initializeConfiguration() {
        configController.add("recipes", new ConfigurationFile(plugin, "recipes"));
        configController.add("lang", new ConfigurationFile(plugin, "lang"));
    }
}
