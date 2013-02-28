package com.mariogrip.glowjoin;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.mariogrip.glowjoin.addon.Metrics;

public class Main extends JavaPlugin
{
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("gset")) {
      for (Player player : getServer().getOnlinePlayers()) {
        if (player.hasPermission("glowjoin.permission.op")) {
          if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "error: useage /gset <Config value> <Your value>");
            sender.sendMessage(ChatColor.RED + " See '/gset help' to see the config values! ");

            return true;
          }

          if ((args[0].equalsIgnoreCase("Help")) || (args[0].equalsIgnoreCase("help"))) {
            sender.sendMessage(ChatColor.YELLOW + "Help menu to Glowjoin command /gset");
            sender.sendMessage(ChatColor.YELLOW + "%player% = the players name   And   %tplayer% = Total players join!");
            sender.sendMessage(ChatColor.YELLOW + "To Edit 'Message_of_The_Day' use /gset motd <value>");
            sender.sendMessage(ChatColor.YELLOW + "To Edit 'New_Player_Join_Message'  use /gset np <value> ");
            sender.sendMessage(ChatColor.YELLOW + "To Edit 'Returning_Player_Join_Message' use /gset rp <value>");
            sender.sendMessage(ChatColor.YELLOW + "To Edit 'Total_players_Message' use /gset tp <value>");
            sender.sendMessage(ChatColor.YELLOW + "To Edit 'Quit_player_Message' use /gset qp <value>");
          }

          if (args[0].equalsIgnoreCase("motd")) {
            getConfig().set("Message_of_The_Day", args[1]);
            saveConfig();
            getServer().reload();
            sender.sendMessage(ChatColor.YELLOW + "The Message_of_The_Day is set to" + args[1]);
          }

          if (args[0].equalsIgnoreCase("np")) {
            getConfig().set("New_Player_Join_Message", args[1]);
            saveConfig();
            getServer().reload();
            sender.sendMessage(ChatColor.YELLOW + "The New_Player_Join_Message is set to" + args[1]);
          }
          if (args[0].equalsIgnoreCase("rp")) {
            getConfig().set("Returning_Player_Join_Message", args[1]);
            saveConfig();
            getServer().reload();
            sender.sendMessage(ChatColor.YELLOW + "The Returning_Player_Join_Message is set to" + args[1]);
          }
          if (args[0].equalsIgnoreCase("tp")) {
            getConfig().set("Total_players_Message", args[1]);
            saveConfig();
            getServer().reload();
            sender.sendMessage(ChatColor.YELLOW + "The Total_players_Message is set to" + args[1]);
          }
          if (args[0].equalsIgnoreCase("qp")) {
            getConfig().set("Quit_player_Message", args[1]);
            saveConfig();
            getServer().reload();
            sender.sendMessage(ChatColor.YELLOW + "The Quit_player_Message is set to" + args[1]);
          }
          return true;
        }

        sender.sendMessage(ChatColor.RED + "You dont have permissions to performs this command!");
      }

    }

    if (cmd.getName().equalsIgnoreCase("greset")) {
      for (Player player : getServer().getOnlinePlayers()) {
        if (player.hasPermission("glowjoin.permission.op"))
        {
          getConfig().set("Total_Joined_the_server_Players", Integer.valueOf("0"));
          saveConfig();
          sender.sendMessage(ChatColor.YELLOW + "you have reset the Total Joined players to 0");
          getServer().reload();
          return true;
        }

        sender.sendMessage(ChatColor.RED + "You dont have permissions to performs this command!");
      }
    }

    if (cmd.getName().equalsIgnoreCase("gresetconfig")) {
      for (Player player : getServer().getOnlinePlayers()) {
        if (player.hasPermission("glowjoin.permission.op")) {
          getConfig().set("Message_of_The_Day", Integer.getInteger(null));
          getConfig().set("Message_of_The_Day", Integer.getInteger(null));
          getConfig().set("Message_of_The_Day", Integer.getInteger(null));
          getConfig().set("Message_of_The_Day", Integer.getInteger(null));
          getConfig().set("Message_of_The_Day", Integer.getInteger(null));
          getConfig().set("Total_Joined_the_server_Players", Integer.valueOf("0"));
          saveConfig();
          sender.sendMessage(ChatColor.YELLOW + "You have now reset the Glowjoin Config!");
          getServer().reload();
          return true;
        }

        sender.sendMessage(ChatColor.RED + "You dont have permissions to performs this command!");
      }

    }

    if (cmd.getName().equalsIgnoreCase("glowjoin"))
    {
      sender.sendMessage(ChatColor.YELLOW + "All glowjoin commands");
      sender.sendMessage(ChatColor.YELLOW + "/greset = reset the value of total joined players");
      sender.sendMessage(ChatColor.YELLOW + "/gset <Config value> <value> Or See /gset help = set your it to your value");
      sender.sendMessage(ChatColor.YELLOW + "gresetconfig = reset all the Config value's");
      sender.sendMessage(ChatColor.YELLOW + "");
      return true;
    }
    return false;
  }

  public void onEnable()
  {
    Bukkit.getServer().getPluginManager().registerEvents(new player(this), this);
    loadConfiguration();
    try {
	    Metrics metrics = new Metrics(this);
	    metrics.start();
	} catch (IOException e) {
	    // Failed to submit the stats :-(
		getLogger().info("Metrics Failed to Submit.... :(");
	}
  }

  public void loadConfiguration() {
    getConfig().options().copyDefaults(true);

    saveConfig();
  }
}
