package cz.wake.craftprison.objects;

import org.bukkit.ChatColor;

public enum MineDifficulty {

    TUTORIAL(ChatColor.GREEN),
    STANDARD(ChatColor.YELLOW),
    HARD(ChatColor.RED);

    private ChatColor color;

    MineDifficulty() {
    }

    MineDifficulty(ChatColor c) {
        this.color = c;
    }

    public ChatColor getColor() {
        return this.color;
    }
}
