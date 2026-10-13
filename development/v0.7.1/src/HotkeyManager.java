package pl.kolegaclient.input;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import pl.kolegaclient.KolegaClient;
import pl.kolegaclient.module.Module;

/** Keybinds toggle on game ticks; zoom is sampled independently every render frame. */
public final class HotkeyManager {
    private final KeybindState state = new KeybindState();
    private final Map<String, Boolean> previous = new HashMap<>();
    private final ZoomSmoother zoom = new ZoomSmoother();

    public HotkeyManager() { state.register("zoom", GLFW.GLFW_KEY_C); }
    public void registerModules() {
        for (Module module : KolegaClient.MODULES.all()) state.register(module.id(), KeybindState.UNBOUND);
    }
    public KeybindState state() { return state; }

    /** Called by the GameRenderer mixin for smooth rendering even at high FPS. */
    public float smoothFactor() {
        Minecraft client = Minecraft.getInstance();
        int code = state.key("zoom");
        Module module = KolegaClient.MODULES.find("zoom");
        boolean valid = client.player != null && client.level != null && client.screen == null
            && client.isWindowActive() && code >= 32 && code <= 348
            && module != null && module.enabled();
        boolean down = valid && GLFW.glfwGetKey(client.getWindow().handle(), code) == GLFW.GLFW_PRESS;
        return (float) zoom.sample(down ? state.zoomFactor() : 1.0, System.nanoTime());
    }

    public void tick(Minecraft client) {
        boolean world = client.player != null && client.level != null && client.screen == null
            && client.isWindowActive();
        long handle = client.getWindow().handle();
        for (var entry : state.all().entrySet()) {
            String id = entry.getKey();
            // Hold-to-zoom; per-frame polling avoids 50ms tick input quantization.
            if (id.equals("zoom")) continue;
            int key = entry.getValue();
            boolean down = world && key >= 32 && key <= 348 &&
                GLFW.glfwGetKey(handle, key) == GLFW.GLFW_PRESS;
            boolean wasDown = previous.getOrDefault(id, false);
            previous.put(id, down);
            if (down && !wasDown) {
                Module module = KolegaClient.MODULES.find(id);
                if (module != null) KolegaClient.MODULES.toggle(module);
            }
        }
    }
}
