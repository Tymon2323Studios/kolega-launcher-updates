package pl.kolegaclient.input;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Key assignments remain separate from module toggles and Minecraft's own controls. */
public final class KeybindState {
    public static final int UNBOUND = -1;
    private final Map<String, Integer> keys = new LinkedHashMap<>();
    private int zoomFactor = 4;

    public void register(String action, int defaultKey) { keys.putIfAbsent(action, defaultKey); }
    public int key(String action) { return keys.getOrDefault(action, UNBOUND); }
    public Map<String, Integer> all() { return Collections.unmodifiableMap(keys); }

    /** Reject duplicate Kolega binds, invalid GLFW codes and Escape / default GUI hotkey. */
    public boolean assign(String action, int code) {
        if (!keys.containsKey(action) || (code != UNBOUND && (code < 32 || code > 348 || code == 256 || code == 344))) return false;
        if (code != UNBOUND && keys.entrySet().stream().anyMatch(e -> !e.getKey().equals(action) && e.getValue() == code)) return false;
        keys.put(action, code);
        return true;
    }
    public int zoomFactor() { return zoomFactor; }
    public void zoomFactor(int zoom) { zoomFactor = Math.min(8, Math.max(2, zoom)); }
}
