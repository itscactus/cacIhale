package tr.com.itscactus.ihale.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IhaleItem {
    private ItemStack itemStack;
    private Integer amount;
    private Double price;
    private String ownerName;
    private Integer duration;
    private Integer id;
    public IhaleItem() {

    }

    public void setItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setOwner(String owner) {
        this.ownerName = owner;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAmount() {
        return amount;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public ItemStack getItem() {
        return itemStack;
    }
    public Double getPrice() {
        return price;
    }
    public Integer getDuration() {
        return duration;
    }
    public Integer getId() {
        return id;
    }
}
