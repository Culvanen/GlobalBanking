/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tjs238.plugins.globalbank.delayedTasks;

/**
 *
 * @author tjs238
 */
import java.util.ArrayList;

import me.tjs238.plugins.globalbank.Globalbank;
import me.tjs238.plugins.globalbank.PlayerState;
import me.tjs238.plugins.globalbank.PlayerState.PlayerStatus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.commons.inventory.DoubleChestInventory;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spout.inventory.CustomInventory;

public class DelayedBank implements Runnable {
	public Player p;
	public Globalbank b;
	SpoutPlayer sp;
        Player pl;

	public DelayedBank(Player p, Globalbank b) {
		this.p = p;
		this.b = b;
		this.sp = SpoutManager.getPlayer(p);
                this.pl = SpoutManager.getPlayer(p);
	}

	@Override
	public void run() {
		b.removeContents(p);
		ItemStack[] content = new ItemStack[54];
		ArrayList<ItemStack> iss = new ArrayList<ItemStack>();
		for (int i = 0; (i < content.length); i++) {
			ItemStack is = new ItemStack(Material.CHEST, i + 1);
			content[i] = is;
			iss.add(is);

		}
		b.isk.put(p, iss);
		String s = p.getName();
		if (p.getName().length() > 11)
			s = s.substring(0, 10);
		;
		CustomInventory ci = new CustomInventory(content, "Bank:" + s);
		sp.openInventoryWindow(ci);
		PlayerState.getPlayerState(p).setPs(PlayerStatus.CHEST_SELECT);
	}
}
