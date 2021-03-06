/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tjs238.plugins.globalbank;

/**
 *
 * @author tjs238
 */
import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Settings {
	private Globalbank m;
	private YamlConfiguration y;
	public Settings(Globalbank m){
		this.m=m;
	}

	public double costPerSlot = 20;
	public double multiplier = 2;
	public boolean useEconomy = true;
    public int startWithSlots = 5;

	public void loadSettings() {
		File f = new File(m.getDataFolder() + "/Config.yml");
		this.y = YamlConfiguration.loadConfiguration(f);
		y.addDefault("Economy.CostPerSlot", 20);
		y.addDefault("Economy.UseEconomy", true);
		y.addDefault("Economy.ProgressiveSlotMultiplier", 2);
		y.addDefault("Slot.BeginWith", 5);
        y.options().copyDefaults(true);
        try {
			y.save(f);
		} catch (IOException e) {
		}
	}
	public void getSettings(){
		this.costPerSlot = y.getDouble("Economy.CostPerSlot");
		this.useEconomy = y.getBoolean("Economy.UseEconomy");
		this.multiplier = y.getDouble("Economy.ProgressiveSlotMultiplier");
		this.startWithSlots = y.getInt("Slot.BeginWith");
	}

	public Object getValue(String s, Object o) {
		if (!this.y.contains(s)) {
			y.set(s, o);
		}
		return y.get(s, o);
	}

	public Integer getIntegerValue(String s,
			Integer i) {
		Object o = this.getValue(s, i);
		return (o instanceof Integer) ? (Integer) o : i;
	}

	public String getStringValue(String s, String i) {
		Object o = this.getValue(s, i);
		return (o instanceof String) ? (String) o : i;
	}

	public Boolean getBooleanValue(String s,
			Boolean i) {
		Object o = this.getValue(s, i);
		return (o instanceof Boolean) ? (Boolean) o : i;
	}
}
