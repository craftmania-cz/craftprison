package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.PrestigeMines;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("mine|dul")
@Description("Teleport na aktuální důl pro tvůj rank nebo teleport na jiný důl")
public class MineCommand extends BaseCommand {

    final PrisonManager pm = new PrisonManager();

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lMine příkaz nápověda:");
        help.showHelp();
    }

    @Default
    public void mineTeleport(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Rank actualRank = this.pm.getCraftPlayer(player).getRank();
            player.sendMessage("§eTeleportuji tě na důl: §f" + actualRank.getName().toUpperCase());
            player.teleport(actualRank.getLocation());
        } else {
            Logger.danger("Tento příkaz může použít jen hráč.");
        }
    }

    @Default
    @CommandCompletion("@ranks")
    @Syntax("<rank> - Teleport na určitý rank")
    public void teleportToSpecificRank(CommandSender sender, String rank) {
        if (!(sender instanceof Player)) {
            Logger.danger("Tento příkaz může použít jen hráč.");
            return;
        }
        Player player = (Player) sender;
        if (Rank.getByName(rank.toUpperCase()) != null) { // Když důl není v rankách
            Rank actualRank = this.pm.getCraftPlayer(player).getRank();
            Rank requestedRank = Rank.getByName(rank.toUpperCase());
            assert requestedRank != null;
            if (actualRank.isAtLeast(requestedRank)) {
                if (requestedRank.getLocation() == null) {
                    player.sendMessage("§c§l[!] §cTento rank nemá nastavený důl. Zřejmě se jedná o chybu...");
                    return;
                }
                player.sendMessage("§eTeleportuji tě na důl: §f" + requestedRank.getName().toUpperCase());
                player.teleport(requestedRank.getLocation());
            } else {
                player.sendMessage("§c§l[!] §eNemáš dostatečně vysoký rank, aby jsi se mohl teleportovat na tento důl.");
            }
        } else if (PrestigeMines.getByName(rank.toUpperCase()) != null) { // Hráč chce prestige rank
            int playerPrestige = this.pm.getCraftPlayer(player).getPrestige();
            PrestigeMines prestigeMine = PrestigeMines.getByName(rank.toUpperCase());
            assert prestigeMine != null;
            if (playerPrestige >= prestigeMine.getRequiredPrestige()) {
                player.sendMessage("§eTeleportuji tě na důl: §f" + prestigeMine.getName().toUpperCase());
                player.teleport(prestigeMine.getLocation());
            } else {
                player.sendMessage("§c§l[!] §eNemáš dostatečně vysokou Prestige, aby jsi se mohl teleportovat na tento důl.");
            }
        } else {
            player.sendMessage("§c§l[!] §cZadaný důl neexituje.");
        }
    }
}
