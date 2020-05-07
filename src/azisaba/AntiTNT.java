package azisaba;

import org.bukkit.plugin.java.JavaPlugin;

public class AntiTNT extends JavaPlugin{
	
	public AntiTNT() {
		
	}
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(), this);
	}
	
	@Override
	public void onDisable() {
		
	}

}
