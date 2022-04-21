package me.lewin.dellunadye;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        if (!sender.isOp()) return false;

        if (args.length > 0) {
            if (args[0].equals("set")){
                YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(Main.class).getDataFolder(),"gui.yml"));
                config.set("pay", ((Player) sender).getItemInHand());
                saveDataFile(config, new File(JavaPlugin.getPlugin(Main.class).getDataFolder(),"gui.yml"));
                sender.sendMessage("§7[§a ! §7] §a설정 완료");
                return true;
            }
        }

        Player player = (Player) sender;

        player.openInventory(new GuiMain().getInventory(1, null, null));

        return true;
    }

    private static void saveDataFile(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("§cFile I/O Error!!");
        }
    }
}
