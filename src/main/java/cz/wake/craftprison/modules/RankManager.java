package cz.wake.craftprison.modules;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.exceptions.PlayerNotInCacheException;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RankManager {

    public static HashMap<Player, Rank> cacheRank = new HashMap<>();

    public static void addToRankCache(Player p, Rank r){
        cacheRank.put(p, r);
    }

    public static void removeFromCache(Player p){
        cacheRank.remove(p);
    }

    public Rank getPlayerRank(final Player p){
        return cacheRank.containsKey(p) ? Rank.getByName(cacheRank.get(p).getName()) : Rank.TUTORIAL_A;
    }

    public Rank getPlayerNextRank(final Player p){
        return getPlayerRank(p).getNext(); //todo: kontrola posledniho ranku
    }

    public double getNextRankPrice(final Player p){
        return (double) getPlayerNextRank(p).getPrice();
    }

    public double getActualRankPrice(final Player p){
        return (double) getPlayerRank(p).getPrice();
    }

    public void rankUp(final Player p) throws PlayerNotInCacheException {
        if(cacheRank.containsKey(p)){
            double playerMoney = Main.getEconomy().getBalance(p);
            Rank actualRank = getPlayerRank(p);
            if(!(actualRank == Rank.OCTOPUS)){ // POSLEDNI RANK
                Rank nextRank = actualRank.getNext();
                if(nextRank.getPrice() <= playerMoney){
                    if(!(actualRank == Rank.TUTORIAL_A)){ // V zakladu hrac nema zadny rank pravo
                        PlayerUtils.removePermission(p, actualRank.getPermission());
                    }
                    PlayerUtils.addPermission(p, nextRank.getPermission());
                    PlayerUtils.sendRankUpMessage(p, nextRank);
                    cacheRank.put(p, nextRank);
                    //todo: sql
                    //todo: efekty, menu overovani
                } else {
                    p.sendMessage("§cNemas dostatek penez na rankup!");
                }
            } else {
                p.sendMessage("§cNo dal to nejde! Zkus /reset a zacni odznova!");
            }
        } else {
            throw(new PlayerNotInCacheException("Hrac neni v cache!")); // Jenom pro efekt!
        }
    }
}
