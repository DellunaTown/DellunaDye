package me.lewin.dellunadye;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class BlockDye implements Listener {
    @EventHandler
    private void onCraft(CraftItemEvent event) {
        if (event.getCurrentItem().hasItemMeta()) {
            if (event.getCurrentItem().getItemMeta().hasCustomModelData()) {
                if (event.getCurrentItem().getType().equals(Material.LEATHER_HORSE_ARMOR)){
                    event.setCancelled(true);
                    event.getWhoClicked().sendMessage("§7[§c ! §7] §c리소스 아이템은 수동 염색이 불가능합니다.");
                }
            }
        }
    }
}
