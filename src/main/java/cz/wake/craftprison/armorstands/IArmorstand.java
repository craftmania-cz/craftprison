package cz.wake.craftprison.armorstands;

public interface IArmorstand {

    /**
     * Spawn ArmorStandu.
     */
    void spawn();

    /**
     * Spawn hologramu
     */
    default void hologramSpawn() {
        return;
    }

    /**
     * Provedeni akce pri pravem kliknuti
     */
    default void onRighClick() {
        return;
    }

    /**
     * Provedeni akce pro levem kliknuti
     */
    default void onLeftClick() {
        return;
    }

    /**
     * Odebráni armorstandu
     */
    void remove();

    /**
     * Detekce spawnuteho ArmorStandu
     */
    default boolean isActive() {
        return true;
    }
}
