package cz.wake.craftprison.modules;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.objects.CraftPlayer;
import org.bukkit.entity.Player;

public class PrisCoins {

    private static PrisonManager pm = new PrisonManager();

    public static int getCoins(Player p) {
        CraftPlayer cp = pm.getCraftPlayer(p);
        return cp.getPrisCoins();
    }

    public static void giveCoins(Player p, int value) {
        CraftPlayer cp = pm.getCraftPlayer(p);

        int finalValue = cp.getPrisCoins() + value;
        if(finalValue < 0){
            finalValue = 0;
        }
        cp.setPrisCoins(finalValue);
        Main.getInstance().getMySQL().setPrisCoins(p.getName(), finalValue);
    }

    public static void takeCoins(Player p, int value) {
        giveCoins(p, value * (-1));
    }

    public static void setCoins(Player p, int value) {
        CraftPlayer cp = pm.getCraftPlayer(p);

        cp.setPrisCoins(value);
        Main.getInstance().getMySQL().setPrisCoins(p.getName(), value);
    }
}
