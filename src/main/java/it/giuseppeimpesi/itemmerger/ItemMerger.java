package it.giuseppeimpesi.itemmerger;

import it.giuseppeimpesi.itemmerger.commands.ItemMergerCommand;
import it.giuseppeimpesi.itemmerger.listener.MergeListener;
import it.giuseppeimpesi.itemmerger.manager.PluginManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class ItemMerger extends JavaPlugin {
    private PluginManager manager;

    @Override
    public void onEnable() {
        manager = new PluginManager(this);

        registerListener();
        registerCommands();

        getLogger().info("ItemMerger enabled correctly.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new MergeListener(manager), this);
    }

    private void registerCommands() {
        getCommand("itemmerger").setExecutor(new ItemMergerCommand(manager));
    }
}
