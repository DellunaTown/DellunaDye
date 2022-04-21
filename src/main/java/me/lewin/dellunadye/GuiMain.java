package me.lewin.dellunadye;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class GuiMain implements Listener {
    public static HashMap<Player, Boolean> map = new HashMap<>();

    public static Inventory getInventory(Integer page, ItemStack item_in, ItemStack item_out) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(Main.class).getDataFolder(),"gui.yml"));
        Inventory inv = Bukkit.getServer().createInventory(null, config.getInt("size"), config.getString("title"));

        for (int i = 0; i < config.getInt("size"); i++) {
            inv.setItem(i, iconNull());
        }

        for (String key : config.getConfigurationSection("item").getKeys(false)) {
            switch (config.getString("item." + key + ".type")) {
                case "COSMETIC_IN":
                    if (item_in != null) {
                        inv.setItem(config.getInt("item." + key + ".slots"), item_in);
                    }
                    else {
                        inv.clear(config.getInt("item." + key + ".slots"));
                    }
                    break;
                case "COSMETIC_OUT":
                    if (item_out != null) {
                        inv.setItem(config.getInt("item." + key + ".slots"), item_out);
                    }
                    else {
                        inv.clear(config.getInt("item." + key + ".slots"));
                    }
                    break;
                case "COLOR_MAIN":
                case "COLOR_SUB":
                    inv.setItem(config.getInt("item." + key + ".slots"), ColorIcon(key, page));
                    break;
            }
        }

        return inv;
    }

    private static ItemStack ColorIcon(String key, Integer page) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(Main.class).getDataFolder(),"gui.yml"));

        String name = config.getString("item." + key + ".name");
        String type = config.getString("item." + key + ".type");
        List<String> lore = config.getStringList("item." + key + ".lore");
        List<Integer> color = config.getIntegerList("item." + key + ".dye");

        ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        meta.setDisplayName(name);
        meta.setCustomModelData(1000);
        if (lore.size() != 0) meta.setLore(lore);

        if (type.equals("COLOR_MAIN")) {
            meta.setColor(Color.fromRGB(color.get(0), color.get(1), color.get(2)));
        }
        if (type.equals("COLOR_SUB")) {
            String[] colorSub = config.getStringList("color.subColor" + page).get(Integer.parseInt(key.replaceAll("[^0-9]", ""))-1).split(";");
            meta.setColor(Color.fromRGB(Integer.parseInt(colorSub[0]), Integer.parseInt(colorSub[1]), Integer.parseInt(colorSub[2])));
        }

        meta.addItemFlags(ItemFlag.HIDE_DYE);
        item.setItemMeta(meta);
        return item;
    }
    private static ItemStack iconNull() {
        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1023);
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(Main.class).getDataFolder(),"gui.yml"));

        if (event.getView().getTitle().equals(config.getString("title"))) {
            if (event.getClickedInventory() == null) return;
            if (event.getClickedInventory().equals(event.getView().getBottomInventory())) return;
            Player player = (Player) event.getWhoClicked();
            switch (event.getSlot()) {
                case 3:
                    map.put(player, false);
                    player.openInventory(new GuiMain().getInventory(1, event.getClickedInventory().getItem(10), event.getClickedInventory().getItem(16)));
                    map.put(player, true);
                    break;
                case 4:
                    map.put(player, false);
                    player.openInventory(new GuiMain().getInventory(2, event.getClickedInventory().getItem(10), event.getClickedInventory().getItem(16)));
                    map.put(player, true);
                    break;
                case 5:
                    map.put(player, false);
                    player.openInventory(new GuiMain().getInventory(3, event.getClickedInventory().getItem(10), event.getClickedInventory().getItem(16)));
                    map.put(player, true);
                    break;
                case 12:
                    map.put(player, false);
                    player.openInventory(new GuiMain().getInventory(4, event.getClickedInventory().getItem(10), event.getClickedInventory().getItem(16)));
                    map.put(player, true);
                    break;
                case 13:
                    map.put(player, false);
                    player.openInventory(new GuiMain().getInventory(5, event.getClickedInventory().getItem(10), event.getClickedInventory().getItem(16)));
                    map.put(player, true);
                    break;
                case 14:
                    map.put(player, false);
                    player.openInventory(new GuiMain().getInventory(6, event.getClickedInventory().getItem(10), event.getClickedInventory().getItem(16)));
                    map.put(player, true);
                    break;
                case 21:
                    map.put(player, false);
                    player.openInventory(new GuiMain().getInventory(7, event.getClickedInventory().getItem(10), event.getClickedInventory().getItem(16)));
                    map.put(player, true);
                    break;
                case 22:
                    map.put(player, false);
                    player.openInventory(new GuiMain().getInventory(8, event.getClickedInventory().getItem(10), event.getClickedInventory().getItem(16)));
                    map.put(player, true);
                    break;
                case 23:
                    map.put(player, false);
                    player.openInventory(new GuiMain().getInventory(9, event.getClickedInventory().getItem(10), event.getClickedInventory().getItem(16)));
                    map.put(player, true);
                    break;

                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                    ItemStack input = event.getClickedInventory().getItem(10);
                    if (input == null) break;
                    if (!input.getType().equals(Material.LEATHER_HORSE_ARMOR) || !input.hasItemMeta() || !input.getItemMeta().hasCustomModelData()) {
                        map.put(player, true);
                        player.closeInventory();
                        player.sendMessage("§7[§c ! §7] §c 염색할 수 없는 아이템입니다.");
                        break;
                    }
                    LeatherArmorMeta meta = (LeatherArmorMeta) input.getItemMeta();
                    meta.setColor(((LeatherArmorMeta)event.getCurrentItem().getItemMeta()).getColor());
                    meta.addItemFlags(ItemFlag.HIDE_DYE);
                    ItemStack output = event.getClickedInventory().getItem(10).clone();
                    output.setItemMeta(meta);
                    event.getClickedInventory().setItem(16, output);
                    break;

                case 10:
                    if (event.getClickedInventory().getItem(10) != null) {
                        event.getClickedInventory().clear(16);
                    }
                    return;
                case 16:
                    if (player.getItemInHand().equals(config.getItemStack("pay"))) {
                        player.setItemInHand(null);
                        event.getClickedInventory().clear(10);
                        player.getInventory().addItem(event.getClickedInventory().getItem(16));
                        player.closeInventory();
                        return;
                    }
                    else {
                        player.sendMessage("§7[§c ! §7] §c염색권을 손에 들고 진행해주세요.");
                        map.put(player, true);
                        player.closeInventory();
                    }
            }
            event.setCancelled(true);
        }

    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(JavaPlugin.getPlugin(Main.class).getDataFolder(),"gui.yml"));

        if (event.getView().getTitle().equals(config.getString("title"))) {
            if (event.getInventory().getItem(10) != null && map.get(event.getPlayer())) {
                event.getPlayer().getInventory().addItem(event.getInventory().getItem(10));
                map.remove(event.getPlayer());
            }
        }
    }
}
