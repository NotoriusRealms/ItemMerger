package it.giuseppeimpesi.itemmerger.commands;

import org.bukkit.entity.Player;

public interface Subcommand {

    String getPermission();

    void perform(Player player, String[] args);

}