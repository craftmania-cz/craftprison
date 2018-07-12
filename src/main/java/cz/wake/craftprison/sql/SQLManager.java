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
import java.sql.ResultSet;

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
            ps = conn.prepareStatement("SELECT * FROM players_data WHERE uuid = ?;");
            ps.setString(1, p.getUniqueId().toString());
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
            ps = conn.prepareStatement("SELECT * FROM players_data WHERE uuid = ?;");
            ps.setString(1, p.getUniqueId().toString());
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
            ps = conn.prepareStatement("SELECT minedblocks FROM players_data WHERE uuid = ?;");
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
            preparedStatement = connection.prepareStatement("UPDATE players_data SET minedblocks = ?, deaths = ?, kills = ? WHERE uuid = ?;");
            preparedStatement.setInt(1, craftPlayer.getMinedBlocks());
            preparedStatement.setInt(2, craftPlayer.getDeaths());
            preparedStatement.setInt(3, craftPlayer.getKills());
            preparedStatement.setString(4, player.getUniqueId().toString());
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
            ps = conn.prepareStatement("SELECT priscoins FROM players_data WHERE uuid = ?;");
            ps.setString(1, p.getUniqueId().toString());
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
            ps = conn.prepareStatement("UPDATE players_data SET priscoins = ? WHERE uuid = ?");
            ps.setInt(1, value);
            ps.setString(2, p.getUniqueId().toString());
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
            ps = conn.prepareStatement("UPDATE players_data SET rank = ? WHERE uuid = ?");
            ps.setString(1, rank.getName());
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
    }
}
