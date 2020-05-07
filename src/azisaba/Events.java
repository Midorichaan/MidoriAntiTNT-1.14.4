package azisaba;

import java.sql.Timestamp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;


public class Events implements Listener{
	
    String prefix = "§8[§aAnti§2TNT§8]§r ";
	String noperm = "§cYou don't have Permission.";
	
	@EventHandler(priority=EventPriority.HIGHEST)
    public void explodeHeight(EntityExplodeEvent e) { 
        if(e.getEntityType() == EntityType.PRIMED_TNT || e.getEntityType() == EntityType.MINECART_TNT) {
            e.blockList().clear();
            for(Player ps : Bukkit.getOnlinePlayers()) {
            	if(ps.hasPermission("midoriantitnt.notify")) {
            		Location loc = e.getLocation();
            		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            		String[] str = {
            				prefix + "§a --------------------------",
            				"   TNTDetector v2.0",
            				"   World: " + loc.getWorld().getName(),
            				"   Location: " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ(),
            				"   Time: " + timestamp,
            				prefix + "§a --------------------------"
            		};
            		
            		ps.sendMessage(str);
            	}
            }
            e.setCancelled(true);
        }
    }
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e){  
		Player p = e.getPlayer();
		String[] str = {
				prefix + "§a" + p.getName() + "さんがTNTを設置しました。",
				prefix + "§a繰り返し表示される場合は /report <Player> <Reason> を実行してください。"
		};
		
        if(e.getBlockPlaced().getType().equals(Material.TNT) || e.getBlockPlaced().getType().equals(Material.TNT_MINECART)){
        	if(p.hasPermission("midoriantitnt.canplace") || p.isOp() == true) {
        		return;
        	} else {
        		p.sendMessage(prefix + "§aTNTの設置が抑制されました。");
            	for(Player pl : Bukkit.getOnlinePlayers()) {
            		pl.sendMessage(str);
            		pl.setCooldown(Material.TNT, 1200);

            	}
            	e.setCancelled(true);
        	}
        }
    }
	

}