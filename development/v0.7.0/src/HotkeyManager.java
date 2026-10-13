package pl.kolegaclient.input;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import pl.kolegaclient.KolegaClient;
import pl.kolegaclient.module.Module;

/** Reads only local keyboard state while in-world, never generates game inputs. */
public final class HotkeyManager {
    private final KeybindState state = new KeybindState();
    private final Map<String, Boolean> previous = new HashMap<>();
    private float smoothFactor = 1.0f;
    public HotkeyManager() { state.register("zoom", GLFW.GLFW_KEY_C); }
    public void registerModules() {
        for (Module module : KolegaClient.MODULES.all()) state.register(module.id(), KeybindState.UNBOUND);
    }
    public KeybindState state() { return state; }
    public float smoothFactor() { return smoothFactor; }

    public void tick(Minecraft client) {
        boolean world = client.player != null && client.level != null && client.screen == null
            && client.isWindowActive();
        long handle = client.getWindow().handle();
        boolean zoomHeld = false;
        for (var entry : state.all().entrySet()) {
            String id = entry.getKey();
            int key = entry.getValue();
            boolean down = world && key >= 32 && key <= 348 &&
                GLFW.glfwGetKey(handle, key) == GLFW.GLFW_PRESS;
            boolean wasDown = previous.getOrDefault(id, false);
            previous.put(id, down);
            if (id.equals("zoom")) {
                Module zoom = KolegaClient.MODULES.find("zoom");
                zoomHeld = down && zoom != null && zoom.enabled();
            } else if (down && !wasDown) {
                Module module = KolegaClient.MODULES.find(id);
                if (module != null) KolegaClient.MODULES.toggle(module);
            }
        }
        float target = zoomHeld ? state.zoomFactor() : 1.0f;
        smoothFactor += (target - smoothFactor) * 0.35f;
        if (Math.abs(smoothFactor - target) < 0.005f) smoothFactor = target;
    }
}
