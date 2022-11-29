package fr.ziriskeep.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

//Classe faite par Kzics/SweeftyZ.
public class ItemUtils {

    private Material mat;

    private int amount;

    private ItemStack item;

    private String displayName;

    private HashMap<Enchantment, Integer> enchantmentList = new HashMap<>();

    private List<String> loreList = new ArrayList<>();

    private ItemFlag flag;

    private short durability;


    public ItemUtils(Material mat, int amount) {
        this.mat = mat;
        this.amount = amount;
        this.item = new ItemStack(mat, amount);
    }


    public ItemUtils setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemUtils addEnchants(Enchantment enchantment, int level) {
        enchantmentList.put(enchantment, level);
        return this;
    }

    public ItemUtils addLores(String... lore) {
        this.loreList.addAll(Arrays.asList(lore));
        return this;
    }
    public ItemUtils addLores(List<String> lorelist) {
        lorelist.forEach(l->lorelist.set(lorelist.indexOf(l),ColorsUtil.translate.apply(l)));
        this.loreList = lorelist;
        return this;
    }

    public ItemUtils addFlag(ItemFlag flag) {
        this.flag = flag;
        return this;
    }

    public ItemUtils setDurability(short durability) {
        this.durability = durability;

        return this;
    }

    public ItemStack build() {
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(displayName);
        if (!loreList.isEmpty()) {
            meta.setLore(loreList);
        }
        if (!enchantmentList.isEmpty()) {
            Set<Enchantment> enchantments = enchantmentList.keySet();

            enchantments.forEach(enchant -> meta.addEnchant(enchant, enchantmentList.get(enchant), true));
        }

        if (flag != null) {
            meta.addItemFlags(flag);
        }
        if (durability != -1) {
            item.setDurability(durability);
        }

        item.setItemMeta(meta);

        return item;

    }
}