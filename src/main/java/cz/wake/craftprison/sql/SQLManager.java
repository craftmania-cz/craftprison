package cz.wake.craftprison.sql;

import com.zaxxer.hikari.HikariDataSource;
import cz.wake.craftprison.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;

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




}
