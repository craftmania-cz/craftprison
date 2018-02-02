package cz.wake.craftprison.sql;

import com.zaxxer.hikari.HikariDataSource;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLManager {

    private final Main plugin;
    private final ConnectionPoolManager pool;
    private HikariDataSource dataSource;

    public SQLManager(Main plugin) {
        this.plugin = plugin;
        pool = new ConnectionPoolManager(plugin);
    }

    public void onDisable() {
        pool.closePool();
    }

    public ConnectionPoolManager getPool() {
        return pool;
    }

    public final boolean hasData(final Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT * FROM players_data WHERE nick = ?;");
            ps.setString(1, p.getName());
            ps.executeQuery();
            return ps.getResultSet().next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            pool.close(conn, ps, null);
        }
    }

    public final CraftPlayer getCraftPlayerFromSQL(final Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT * FROM players_data WHERE nick = ?;");
            ps.setString(1, p.getName());
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return new CraftPlayer(p, Rank.getByName(ps.getResultSet().getString("rank")),
                        ps.getResultSet().getInt("priscoins"),
                        ps.getResultSet().getInt("minedblocks"),
                        ps.getResultSet().getInt("kills"),
                        ps.getResultSet().getInt("deaths"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return null;
    }

    public final void insertDefaultData(final Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("INSERT INTO players_data (nick) VALUES (?);");
                    ps.setString(1, p.getName());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public void setMinedBlocksFromCache(Player player) {
        PrisonManager prisonManager = new PrisonManager();
        CraftPlayer craftPlayer = prisonManager.getCraftPlayer(player);

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = pool.getConnection();
            preparedStatement = conn.prepareStatement("UPDATE players_data SET minedblocks = ? WHERE nick=?;");
            preparedStatement.setInt(1, craftPlayer.getMinedBlocks() + plugin.getStatistics().getBlocksBroken(player));
            preparedStatement.setString(2, player.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, preparedStatement, null);
        }

    }

    public void setDeathsFromCache(Player player) {
        PrisonManager prisonManager = new PrisonManager();
        CraftPlayer craftPlayer = prisonManager.getCraftPlayer(player);

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = pool.getConnection();
            preparedStatement = conn.prepareStatement("UPDATE players_data SET deaths = ? WHERE nick=?;");
            preparedStatement.setInt(1, craftPlayer.getDeaths() + plugin.getStatistics().getDeaths(player));
            preparedStatement.setString(2, player.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, preparedStatement, null);
        }
    }

    public void setKillsFromCache(Player player) {
        PrisonManager prisonManager = new PrisonManager();
        CraftPlayer craftPlayer = prisonManager.getCraftPlayer(player);

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = pool.getConnection();
            preparedStatement = conn.prepareStatement("UPDATE players_data SET kills = ? WHERE nick=?;");
            preparedStatement.setInt(1, craftPlayer.getKills() + plugin.getStatistics().getKills(player));
            preparedStatement.setString(2, player.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, preparedStatement, null);
        }
    }


}
