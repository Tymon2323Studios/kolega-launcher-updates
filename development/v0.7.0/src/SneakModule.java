package pl.kolegaclient.module.impl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import pl.kolegaclient.KolegaClient;
import pl.kolegaclient.module.Category;
import pl.kolegaclient.module.Module;
import pl.kolegaclient.ui.HudTheme;
public final class SneakModule extends Module {
    public SneakModule() { super("sneak", "Sneak HUD", "Wskaznik skradania", Category.HUD, false); }
    @Override public void onHud(Minecraft c, GuiGraphics g) {
        if (c.player == null) return;
        var box = KolegaClient.HUD_LAYOUT.bounds(id(), g.guiWidth(), g.guiHeight());
        HudTheme.label(g, c, c.player.isCrouching() ? "SNEAK  ACTIVE" : "SNEAK  OFF", box.x(), box.y(), box.width());
    }
}
