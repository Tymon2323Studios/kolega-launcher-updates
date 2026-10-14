package pl.kolegaclient.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import pl.kolegaclient.KolegaClient;
import pl.kolegaclient.module.impl.CrosshairModule;

/** Renders in the vanilla CROSSHAIR layer only, not the generic overlay. */
public final class CrosshairHudBridge {
    private CrosshairHudBridge() { }
    public static boolean renderIfActive(Minecraft client, GuiGraphics graphics) {
        if (!CrosshairModule.canReplaceVanilla(client)) return false;
        var velocity = client.player.getDeltaMovement();
        boolean moving = velocity.x * velocity.x + velocity.z * velocity.z > 0.002;
        CrosshairPainter.drawAtScreenCenter(graphics, KolegaClient.CROSSHAIR, HudTheme.accent(), moving);
        return true;
    }
}
