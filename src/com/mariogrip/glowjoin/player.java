package com.mariogrip.glowjoin;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class player
  implements Listener
{
  Main plugin;

  public player(Main instance)
  {
    this.plugin = instance;
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    String playerName = event.getPlayer().getName();
    Player p = event.getPlayer();
    if (!this.plugin.getConfig().getBoolean(playerName + "_have_Joined_the_server")) {
      this.plugin.getConfig().set(playerName + "_have_Joined_the_server", Boolean.valueOf(true));
      int first = this.plugin.getConfig().getInt("Total_Joined_the_server_Players");
      int total = first + 1;
      this.plugin.getConfig().set("Total_Joined_the_server_Players", Integer.valueOf(total));
      this.plugin.saveConfig();
      String TotalPlayers = this.plugin.getConfig().getString("Total_Joined_the_server_Players");
      event.setJoinMessage("");
      this.plugin.getServer().broadcastMessage(this.plugin.getConfig().getString("New_Player_Join_Message").replaceAll("(&([a-f0-9]))", "§$2").replace("%player%", playerName));
      this.plugin.getServer().broadcastMessage(this.plugin.getConfig().getString("Total_players_Message").replaceAll("(&([a-f0-9]))", "§$2").replace("%tplayers%", TotalPlayers));
      p.sendMessage(this.plugin.getConfig().getString("Message_of_The_Day").replace("%player%", playerName).replaceAll("(&([a-f0-9]))", "§$2"));
    } else {
      event.setJoinMessage("");
      this.plugin.getServer().broadcastMessage(this.plugin.getConfig().getString("Returning_Player_Join_Message").replaceAll("(&([a-f0-9]))", "§$2").replace("%player%", playerName));
      p.sendMessage(this.plugin.getConfig().getString("Message_of_The_Day").replace("%player%", playerName).replaceAll("(&([a-f0-9]))", "§$2"));
    }
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    String playerName = event.getPlayer().getName();
    event.setQuitMessage("");
    this.plugin.getServer().broadcastMessage(this.plugin.getConfig().getString("Quit_player_Message").replaceAll("(&([a-f0-9]))", "§$2").replace("%player%", playerName));
  }
}
