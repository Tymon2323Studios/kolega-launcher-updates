package pl.kolegaclient.module.impl;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import pl.kolegaclient.KolegaClient;
import pl.kolegaclient.module.Category;
import pl.kolegaclient.module.Module;
import pl.kolegaclient.ui.HudTheme;
public final class ClockModule extends Module {
    private static final DateTimeFormatter CLOCK = DateTimeFormatter.ofPattern("HH:mm");
    public ClockModule() { super("clock", "Clock HUD", "Zegar czasu lokalnego", Category.HUD, false); }
    @Override public void onHud(Minecraft c, GuiGraphics g) {
        var box = KolegaClient.HUD_LAYOUT.bounds(id(), g.guiWidth(), g.guiHeight());
        HudTheme.label(g, c, "TIME  " + LocalTime.now().format(CLOCK), box.x(), box.y(), box.width());
    }
}
