package pl.kolegaclient.module.impl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import pl.kolegaclient.module.Category;
import pl.kolegaclient.module.Module;
import pl.kolegaclient.ui.HudTheme;
public final class CrosshairModule extends Module {
    public CrosshairModule() { super("crosshair", "Crosshair Accent", "Kolorowy znacznik celownika", Category.RENDER, false); }
    @Override public void onHud(Minecraft c, GuiGraphics g) {
        if (c.player == null || c.screen != null || c.options.hideGui) return;
        int x = g.guiWidth()/2, y = g.guiHeight()/2;
        int color = HudTheme.accent();
        g.fill(x-1, y-1, x+2, y+2, color);
        g.fill(x-1, y-9, x+1, y-5, color);
        g.fill(x-1, y+6, x+1, y+10, color);
        g.fill(x-9, y-1, x-5, y+1, color);
        g.fill(x+6, y-1, x+10, y+1, color);
    }
}
