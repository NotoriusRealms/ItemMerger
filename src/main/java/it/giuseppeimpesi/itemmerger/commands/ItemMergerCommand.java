package it.giuseppeimpesi.itemmerger.commands;

import com.google.common.collect.Maps;
import it.giuseppeimpesi.itemmerger.manager.PluginManager;
import it.giuseppeimpesi.itemmerger.commands.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

import static it.giuseppeimpesi.itemmerger.config.enums.Messages.NO_PERMISSIONS;
import static it.giuseppeimpesi.itemmerger.config.enums.Messages.SYNTAX_ERROR;

public final class ItemMergerCommand implements CommandExecutor {
    private final PluginManager manager;

    private final Map<String, Subcommand> subCommands;

    public ItemMergerCommand(PluginManager manager) {
        this.manager = manager;

        subCommands = Maps.newHashMap();
        subCommands.put("create", new CreateSubcommand(manager));
        subCommands.put("first", new FirstSubcommand(manager));
        subCommands.put("second", new SecondSubcommand(manager));
        subCommands.put("result", new ResultSubcommand(manager));
        subCommands.put("reload", new ReloadSubcommand(manager));
        subCommands.put("list", new ListSubcommand(manager));
        subCommands.put("info", new InfoSubcommand(manager));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(SYNTAX_ERROR.getMessage());
            return true;
        }

        if (!subCommands.containsKey(args[0].toLowerCase())) {
            player.sendMessage(SYNTAX_ERROR.getMessage());
            return false;
        }

        Subcommand subCommand = subCommands.get(args[0].toLowerCase());

        if (!player.hasPermission(subCommand.getPermission())) {
            player.sendMessage(NO_PERMISSIONS.getMessage());
            return false;
        }

        subCommand.perform(player, args);
        return true;
    }
}
