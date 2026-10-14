package pl.kolegaclient.ui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class NotificationFeed {
    public record Message(String text, long timestamp) { }
    private final Deque<Message> queue = new ArrayDeque<>();
    private final int max;
    private final long lifetimeMs;
    public NotificationFeed(int max, long lifetimeMs) {
        if (max < 1 || lifetimeMs < 1) throw new IllegalArgumentException("Invalid feed bounds");
        this.max=max; this.lifetimeMs=lifetimeMs;
    }
    public void add(String text, long now) {
        if (text == null || text.isBlank()) return;
        prune(now);
        queue.addLast(new Message(text.length()>100?text.substring(0,100):text,now));
        while (queue.size() > max) queue.removeFirst();
    }
    public List<Message> view(long now) { prune(now); return List.copyOf(new ArrayList<>(queue)); }
    public long lifetimeMs() { return lifetimeMs; }
    private void prune(long now) {
        while (!queue.isEmpty() && (now < queue.peekFirst().timestamp() || now - queue.peekFirst().timestamp() >= lifetimeMs)) queue.removeFirst();
    }
}
