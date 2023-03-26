package tr.com.itscactus.ihale.guis;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import mc.obliviate.inventory.pagination.PaginationManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tr.com.itscactus.ihale.Ihale;
import tr.com.itscactus.ihale.utils.IhaleItem;

import java.util.ArrayList;
import java.util.List;

public class IhaleGUI extends Gui {
    private final PaginationManager paginationManager = new PaginationManager(this);
    public IhaleGUI(Player player) {
        super(player, "gui", ChatColor.translateAlternateColorCodes('&', "&cİhale"), 6);
    }
    @Override
    public void onOpen(InventoryOpenEvent e) {

        paginationManager.registerPageSlotsBetween(0, (5*9)-1);
        // TODO: Bit olayı falan
        for(IhaleItem item : Ihale.instance.ihaleItems.values()) {
            ItemStack itemUp = item.getItem();
            ItemMeta itemMeta = itemUp.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("§dKalan Süre: §f" + item.getDuration());
            lore.add("§dSahibi: §f" + item.getOwnerName());
            lore.add("§dFiyat: §f" + item.getPrice());
            lore.add("§dAdet: §f" + item.getAmount());
            itemMeta.setLore(lore);
            itemUp.setItemMeta(itemMeta);
            paginationManager.addItem(new Icon(itemUp));
        }

        ItemStack nextpaginateItem = new ItemStack(Material.PAPER);
        ItemMeta nextItemMeta = nextpaginateItem.getItemMeta();
        nextItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aSonraki Sayfa"));
        nextpaginateItem.setItemMeta(nextItemMeta);
        Icon nextIcon = new Icon(nextpaginateItem);
        addItem(53, nextIcon);
        nextIcon.onClick((event) -> {
            event.setCancelled(true);
            paginationManager.goNextPage();
            paginationManager.update();
        });

        ItemStack prevpaginateItem = new ItemStack(Material.PAPER);
        ItemMeta prevItemMeta = prevpaginateItem.getItemMeta();
        prevItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aÖnceki Sayfa"));
        prevpaginateItem.setItemMeta(nextItemMeta);
        Icon prevIcon = new Icon(prevpaginateItem);
        addItem(45, prevIcon);
        prevIcon.onClick((event) -> {
            event.setCancelled(true);
            paginationManager.goPreviousPage();
            paginationManager.update();
        });

        ItemStack book = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta bookItemMeta = book.getItemMeta();
        bookItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&dSayfa: &f" + paginationManager.getCurrentPage() + "&7/&f" + paginationManager.getLastPage()));
        book.setItemMeta(bookItemMeta);
        addItem(49, book);
        paginationManager.update();
    }

    @Override
    public boolean onClick(InventoryClickEvent event) {
        super.onClick(event);
        event.setCancelled(true);

        return true;
    } @Override
    public boolean onDrag(InventoryDragEvent event) {
        super.onDrag(event);
        event.setCancelled(true);

        return true;
    }
}
