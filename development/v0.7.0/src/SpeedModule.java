package pl.kolegaclient.module.impl;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import pl.kolegaclient.KolegaClient;
import pl.kolegaclient.module.Category;
import pl.kolegaclient.module.Module;
import pl.kolegaclient.ui.HudTheme;
public final class SpeedModule extends Module {
    public SpeedModule() { super("speed", "Speed HUD", "Predkosc pozioma w blokach/s", Category.HUD, false); }
    @Override public void onHud(Minecraft c, GuiGraphics g) {
        if (c.player == null) return;
        var v = c.player.getDeltaMovement();
        double bps = Math.hypot(v.x, v.z) * 20;
        var box = KolegaClient.HUD_LAYOUT.bounds(id(), g.guiWidth(), g.guiHeight());
        HudTheme.label(g, c, String.format(Locale.ROOT, "SPEED  %.1f b/s", bps), box.x(), box.y(), box.width());
    }
}
