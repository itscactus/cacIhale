package tr.com.itscactus.ihale;

import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import tr.com.itscactus.ihale.guis.IhaleGUI;
import tr.com.itscactus.ihale.utils.IhaleData;
import tr.com.itscactus.ihale.utils.IhaleItem;

import java.util.HashMap;
import java.util.Set;

public final class Ihale extends JavaPlugin {
    public static Ihale instance;
    public HashMap<Integer, IhaleItem> ihaleItems = new HashMap<>();
    int ihaleId = 0;
    private IhaleData ihaleData;
    @Override
    public void onEnable() {
        // Plugin startup logic
        ihaleData = new IhaleData(this);
        saveDefaultConfig();
        new InventoryAPI(this).init();
        instance = this;
        getCommand("ihale").setExecutor(this);
        // TODO: ihaleItems listini data.yml e kaydetme (Her saniye) + duration checker
        if(getData().isSet("items")) {
            ConfigurationSection section = getData().getConfigurationSection("items");
            for (String x : section.getKeys(false)) {
                Double price = getData().getDouble("items." + x + ".price");
                Integer duration = getData().getInt("items." + x + ".duration");
                Integer amount = getData().getInt("items." + x + ".amount");
                String ownerName = getData().getString("items." + x + ".ownerName");
                ItemStack itemStack = getData().getItemStack("items." + x + ".itemStack");
                IhaleItem item = new IhaleItem();

                item.setDuration(duration);
                item.setItem(itemStack);
                item.setPrice(price);
                item.setOwner(ownerName);
                item.setAmount(amount);
                item.setId(ihaleId);

                ihaleItems.put(ihaleId, item);
                ihaleId++;
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(command.getName().equalsIgnoreCase("ihale")) {
                if(args.length <= 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLütfen bir argüman giriniz &e(ekle/menü) &c."));
                    return false;
                }
                if(args[0].equalsIgnoreCase("ekle")) {
                    // TODO: Eşya amount ayarlama ve fiyat belirleme eklenicek
                    ItemStack itemToAdd = player.getItemInHand();
                    player.getInventory().remove(itemToAdd);
                    IhaleItem item = new IhaleItem();
                    item.setAmount(itemToAdd.getAmount());
                    item.setPrice(31.00);
                    item.setDuration(60);
                    item.setOwner(player.getName());
                    item.setItem(itemToAdd);
                    item.setId(ihaleId);
                    ihaleItems.put(ihaleId, item);
                    ihaleId++;
                } else if(args[0].equalsIgnoreCase("menü")) {


                    /*for(IhaleItem item : ihaleItems.values()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + item.getOwnerName() + "&c kişisinin İhale eşyası."));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fFiyat: &e" + item.getPrice()));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fItem: &e" + item.getItem().getType()));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fEşya Adı: &e" + item.getItem().getItemMeta().getDisplayName()));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fAdet: &e" + item.getPrice()));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fSüre: &e" + item.getDuration()));
                    }*/
                    IhaleGUI gui = new IhaleGUI(player);
                    gui.open();
                }
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cBu komutu sadece oyuncular kullanabilir."));
        }
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public FileConfiguration getData() {
        return ihaleData.getConfiguration();
    }

}
