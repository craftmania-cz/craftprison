package cz.wake.craftprison.utils;

import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.entity.Player;

public class PermFixes {

    PrisonManager pm = new PrisonManager();

    public void fixFor(final Player p) {

        // Oprava chybejiciho prava na prodej v PVP # 16.7.2018
        /*if(p.hasPermission("craftprison.pvp") && !p.hasPermission("quicksell.shop.pvp")) {
            PlayerUtils.addPermission(p, "quicksell.shop.pvp");
        }

        // Oprava chybejiciho NightVision pro hrace, kteri byli na serveru v pripade bugu s rankupy A -> B # 16.7.2018
        if(pm.getPlayerRank(p) != Rank.A && !p.hasPermission("craftprison.mine.nightvision")) {
            PlayerUtils.addPermission(p, "craftprison.mine.nightvision");
        }

        // Oprava pristupu na doly # 18.7.2018
        for (Rank r : Rank.values()){
            if(r.getWeight() <= pm.getPlayerRank(p).getWeight()){ // A - 1, Zoidberg - 5
                if(!p.hasPermission("essentials.warps." + r.getName().toLowerCase())){
                    PlayerUtils.addPermission(p, "essentials.warps." + r.getName().toLowerCase());
                }
            }
        }

        // Oprava chybejicich prav na Chestshop 29.7.2018
        if(pm.getPlayerRank(p).getWeight() >= 26 && !p.hasPermission("shop.create")) { // Optimus
            PlayerUtils.addPermission(p, "shop.create");
            PlayerUtils.addPermission(p, "shop.create.buy");
            PlayerUtils.addPermission(p, "shop.create.sell");
        }*/

    }
}
