package fr.ziriskeep.commands;

import fr.ziriskeep.Main;
import fr.ziriskeep.utils.ColorsUtil;
import fr.ziriskeep.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GearCommand implements CommandExecutor {


    private final Main instance;
    public GearCommand(final Main instance){
        this.instance = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(args.length == 1) {
            if (label.equals("zirisgear")) {
                if (args[0].equalsIgnoreCase("epee")) {
                    ItemStack sword = new ItemUtils(Material.DIAMOND_SWORD, 1)
                            .addEnchants(Enchantment.DAMAGE_ALL, 5)
                            .addEnchants(Enchantment.DURABILITY,3)
                            .addEnchants(Enchantment.FIRE_ASPECT,2)
                            .setDisplayName(ColorsUtil.translate.apply(instance.getConfig().getString("epee.Name")))
                            .addLores(instance.getConfig().getStringList("epee.Lore"))
                            .build();

                    player.getInventory().addItem(sword);

                }else if(args[0].equalsIgnoreCase("arc")){
                    ItemStack bow = new ItemUtils(Material.BOW, 1)
                            .addEnchants(Enchantment.ARROW_DAMAGE,3)
                            .addEnchants(Enchantment.DURABILITY,3)
                            .addEnchants(Enchantment.ARROW_FIRE,1)
                            .setDisplayName(ColorsUtil.translate.apply(instance.getConfig().getString("arc.Name")))
                            .addLores(instance.getConfig().getStringList("arc.Lore"))
                            .build();

                    player.getInventory().addItem(bow);

                }else if(args[0].equalsIgnoreCase("rod")){
                    ItemStack rod = new ItemUtils(Material.FISHING_ROD, 1)
                            .setDisplayName(ColorsUtil.translate.apply(instance.getConfig().getString("rod.Name")))
                            .addLores(instance.getConfig().getStringList("rod.Lore"))
                            .build();

                    player.getInventory().addItem(rod);
                }
            }
        }
        return false;
    }
}
