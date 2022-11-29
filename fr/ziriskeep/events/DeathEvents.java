package fr.ziriskeep.events;

import fr.ziriskeep.Main;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class DeathEvents implements Listener {


    private final Main instance;

    private static HashMap<UUID,List<ItemStack>> specialItem = new HashMap<>();
    public DeathEvents(final Main instance){
        this.instance = instance;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        if(hasSpecialItem(player.getInventory())){
            List<ItemStack> it = this.getSpecialItems(player.getInventory());
            removeAllSpecial(player.getInventory(),e);
            specialItem.put(player.getUniqueId(),it);

            System.out.println(it);

        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player player = e.getPlayer();
        if(specialItem.containsKey(player.getUniqueId()) || instance.getConfig().get("waiting." + player.getUniqueId()) != null) {
            if(instance.getConfig().get("waiting." + player.getUniqueId())!= null){
                instance.getConfig().set("waiting." + player.getUniqueId(),null);
                instance.saveConfig();
            }
            specialItem.get(player.getUniqueId()).forEach(it -> player.getInventory().addItem(it));

            specialItem.remove(player.getUniqueId());
        }

    }

    private void removeAllSpecial(Inventory inv,PlayerDeathEvent e){
        this.getSpecialItems(inv).forEach(it->e.getDrops().remove(it));
    }

    private boolean hasSpecialSword(Inventory inv){
        return Arrays.stream(inv.getContents()).anyMatch(it-> it != null && it.hasItemMeta() && it.getItemMeta()
                .getDisplayName().equals(instance.getConfig().get("epee.Name")));
    }
    private boolean hasSpacialBow(Inventory inv){
        return Arrays.stream(inv.getContents()).anyMatch(it->it != null && it.hasItemMeta() && it.getItemMeta()
                .getDisplayName().equals(instance.getConfig().get("arc.Name")));
    }
    private boolean hasSpecialRod(Inventory inv){
        return Arrays.stream(inv.getContents()).anyMatch(it->it != null && it.hasItemMeta() && it.getItemMeta()
                .getDisplayName().equals(instance.getConfig().get("rod.Name")));
    }

    private boolean hasSpecialItem(Inventory inv){
        return this.hasSpecialSword(inv) || this.hasSpacialBow(inv) || this.hasSpecialRod(inv);
    }


    private List<ItemStack> getSpecialSwords(Inventory inv){
        return Arrays.stream(inv.getContents()).filter(it->it != null && it.hasItemMeta() && it.getItemMeta()
                .getDisplayName().equals(instance.getConfig().get("epee.Name"))).collect(Collectors.toList());

    }
    private List<ItemStack> getSpecialBows(Inventory inv){
        return Arrays.stream(inv.getContents()).filter(it-> it != null && it.hasItemMeta() && it.getItemMeta()
                .getDisplayName().equals(instance.getConfig().get("arc.Name"))).collect(Collectors.toList());
    }
    private List<ItemStack> getSpecialRods(Inventory inv){
        return Arrays.stream(inv.getContents()).filter(it->it != null && it.hasItemMeta() && it.getItemMeta()
                .getDisplayName().equals(instance.getConfig().get("rod.Name"))).collect(Collectors.toList());

    }

    private List<ItemStack> getSpecialItems(Inventory inv){

        List<ItemStack> allItems = new ArrayList<>(this.getSpecialSwords(inv));

        allItems.addAll(this.getSpecialBows(inv));
        allItems.addAll(this.getSpecialRods(inv));

        return allItems;
    }


    public static HashMap<UUID, List<ItemStack>> getSpecialItems(){
        return specialItem;
    }



}
