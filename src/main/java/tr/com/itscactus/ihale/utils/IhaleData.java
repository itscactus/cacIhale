package tr.com.itscactus.ihale.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import tr.com.itscactus.ihale.Ihale;

import java.io.File;
import java.io.IOException;

public class IhaleData {
    private FileConfiguration fileConfiguration;
    private File file;
    private Ihale plugin;
    public IhaleData(Ihale plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "data.yml");
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource("data.yml", false);
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfiguration() {
        return fileConfiguration;
    }
}
