package fr.ziriskeep.events;

import fr.ziriskeep.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class JoinsEvents implements Listener {

    private final Main instance;

    public JoinsEvents(final Main instance){
        this.instance = instance;
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        if(DeathEvents.getSpecialItems().containsKey(player.getUniqueId())){
            List<ItemStack> itemStacks = DeathEvents.getSpecialItems().get(player.getUniqueId());
            instance.getConfig().set("waiting." + player.getUniqueId(),itemStacks);
            instance.saveConfig();
        }



    }

}
