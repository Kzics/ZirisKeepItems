package fr.ziriskeep;

import fr.ziriskeep.commands.GearCommand;
import fr.ziriskeep.events.DeathEvents;
import fr.ziriskeep.events.JoinsEvents;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {


    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        if(!this.getDataFolder().exists()){
            this.getDataFolder().mkdir();
        }

        if(!configExists()){
            File file = new File(this.getDataFolder(),"config.yml");
            try {
                file.createNewFile();
                System.out.println("created");
                FileUtils.copyInputStreamToFile(this.getResource("config.yml"),file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //commands
        this.getCommand("zirisgear").setExecutor(new GearCommand(this));

        registerEvents();



    }

    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new DeathEvents(this),this);
        pm.registerEvents(new JoinsEvents(this),this);
    }

    private boolean configExists(){
        return new File(this.getDataFolder(),"config.yml").exists();
    }

}
