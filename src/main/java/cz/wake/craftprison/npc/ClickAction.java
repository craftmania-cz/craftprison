package cz.wake.craftprison.npc;

import net.citizensnpcs.api.event.NPCClickEvent;

import java.util.function.Consumer;

public class ClickAction {

    private Consumer<NPCClickEvent> consumer;

    public ClickAction(Consumer<NPCClickEvent> consumer) {
        this.consumer = consumer;
    }

    public static ClickAction run(Consumer<NPCClickEvent> consumer) {
        return new ClickAction(consumer);
    }

    public void run(NPCClickEvent event) {
        consumer.accept(event);
    }
}
