package it.giuseppeimpesi.itemmerger.config;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

@Getter
public class ConfigurationFile {

    private final JavaPlugin plugin;
    private final String fileName;

    private final File file;
    private FileConfiguration fileConfiguration;

    public ConfigurationFile(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;

        String extendedFileName = fileName + ".yml";

        file = new File(plugin.getDataFolder(), extendedFileName);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(extendedFileName, false);
        }

        reload();
    }

    public void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}