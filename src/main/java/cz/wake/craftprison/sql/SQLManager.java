package cz.wake.craftprison.sql;

import com.zaxxer.hikari.HikariDataSource;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.objects.StatsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public final boolean hasDataByName(final String p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT * FROM players_data WHERE nick = ?;");
            ps.setString(1, p);
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

    //TODO: Rewrite + podpora UUID
    public int getMinedBlocks(final String player) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT minedblocks FROM players_data WHERE nick = ?;");
            ps.setString(1, player);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return ps.getResultSet().getInt("minedblocks");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return 0;
    }

    public int getKills(final String player) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT kills FROM players_data WHERE nick = ?;");
            ps.setString(1, player);
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt("kills");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return 0;
    }

    public int getDeaths(final String player) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT deaths FROM players_data WHERE nick = ?;");
            ps.setString(1, player);
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt("deaths");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return 0;
    }

    public final void insertDefaultData(final Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("INSERT INTO players_data (uuid,nick) VALUES (?,?);");
                    ps.setString(1, p.getUniqueId().toString());
                    ps.setString(2, p.getName());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public void setAllFromCache(Player player) {
        PrisonManager prisonManager = new PrisonManager();
        CraftPlayer craftPlayer = prisonManager.getCraftPlayer(player);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE players_data SET minedblocks = ?, deaths = ?, kills = ? WHERE nick = ?;");
            preparedStatement.setInt(1, craftPlayer.getMinedBlocks());
            preparedStatement.setInt(2, craftPlayer.getDeaths());
            preparedStatement.setInt(3, craftPlayer.getKills());
            preparedStatement.setString(4, player.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(connection, preparedStatement, null);
        }
    }

    public int getPrisCoins(final Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT priscoins FROM players_data WHERE nick = ?;");
            ps.setString(1, p.getName());
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt("priscoins");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return 0;
    }

    public void setPrisCoins(final Player p, final int value) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("UPDATE players_data SET priscoins = ? WHERE nick = ?");
            ps.setInt(1, value);
            ps.setString(2, p.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
    }

    public void rankupPlayerSQL(final Player p, final Rank rank) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("UPDATE players_data SET rank = ? WHERE nick = ?");
            ps.setString(1, rank.getName());
            ps.setString(2, p.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
    }

    public List<CraftPlayer> getTopPCoins(final int limit) {
        List<CraftPlayer> lb = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT * FROM players_data ORDER BY priscoins DESC LIMIT 0, " + limit + ";");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                lb.add(new CraftPlayer(Bukkit.getPlayer(ps.getResultSet().getString("nick")),
                        Rank.getByName(ps.getResultSet().getString("rank")),
                        ps.getResultSet().getInt("priscoins"),
                        ps.getResultSet().getInt("minedblocks"),
                        ps.getResultSet().getInt("kills"),
                        ps.getResultSet().getInt("deaths")));
            }
            return lb;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return lb;
    }

    public List<StatsPlayer> getTopMinedBlocks(final int limit) {
        List<StatsPlayer> lb = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT * FROM players_data ORDER BY minedblocks DESC LIMIT 0, " + limit + ";");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                lb.add(new StatsPlayer(Bukkit.getPlayer(ps.getResultSet().getString("nick")),
                        ps.getResultSet().getInt("priscoins"),
                        ps.getResultSet().getInt("minedblocks"),
                        ps.getResultSet().getInt("kills"),
                        ps.getResultSet().getInt("deaths")));
            }
            return lb;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return lb;
    }

    public List<StatsPlayer> getTopKills(final int limit) {
        List<StatsPlayer> lb = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT * FROM players_data ORDER BY kills DESC LIMIT 0, " + limit + ";");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                lb.add(new StatsPlayer(Bukkit.getPlayer(ps.getResultSet().getString("nick")),
                        ps.getResultSet().getInt("priscoins"),
                        ps.getResultSet().getInt("minedblocks"),
                        ps.getResultSet().getInt("kills"),
                        ps.getResultSet().getInt("deaths")));
            }
            return lb;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return lb;
    }

    public List<StatsPlayer> getTopDeaths(final int limit) {
        List<StatsPlayer> lb = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT * FROM players_data ORDER BY deaths DESC LIMIT 0, " + limit + ";");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                lb.add(new StatsPlayer(Bukkit.getPlayer(ps.getResultSet().getString("nick")),
                        ps.getResultSet().getInt("priscoins"),
                        ps.getResultSet().getInt("minedblocks"),
                        ps.getResultSet().getInt("kills"),
                        ps.getResultSet().getInt("deaths")));
            }
            return lb;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return lb;
    }

    /*
    SELECT count( * ) AS number
    FROM `Hlasovani` t1
    WHERE t1.pocethlasovani >= (
    SELECT t2.pocethlasovani
    FROM `Hlasovani` t2
    WHERE t2.servernick = 'KontraTeror' )
     */

    public int getTopPCoinsPosition(final Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT count( * ) AS number FROM `players_data` WHERE t1.priscoins >= ( SELECT t2.priscoins FROM `players_data` t2 WHERE t2.serverNick = ?);");
            ps.setString(1, p.getName());
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt("number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return -1;
    }

    public int getTopMinedBlocksPosition(final Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT count( * ) AS number FROM `players_data` WHERE t1.minedblocks >= ( SELECT t2.minedblocks FROM `players_data` t2 WHERE t2.serverNick = ?);");
            ps.setString(1, p.getName());
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt("number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return -1;
    }

    public int getTopKillsPosition(final Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT count( * ) AS number FROM `players_data` WHERE t1.kills >= ( SELECT t2.kills FROM `players_data` t2 WHERE t2.serverNick = ?);");
            ps.setString(1, p.getName());
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt("number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return -1;
    }

    public int getTopDeathsPosition(final Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT count( * ) AS number FROM `players_data` WHERE t1.deaths >= ( SELECT t2.deaths FROM `players_data` t2 WHERE t2.serverNick = ?);");
            ps.setString(1, p.getName());
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt("number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return -1;
    }
}
