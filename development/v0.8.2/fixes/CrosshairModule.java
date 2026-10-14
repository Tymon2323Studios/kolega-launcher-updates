package pl.kolegaclient.module.impl;

import net.minecraft.client.Minecraft;
import pl.kolegaclient.KolegaClient;
import pl.kolegaclient.module.Category;
import pl.kolegaclient.module.Module;

/** No onHud() here: crosshair now replaces the vanilla HUD layer. */
public final class CrosshairModule extends Module {
    public CrosshairModule() {
        super("crosshair", "Custom Crosshair", "Ksztalt, kolor i podglad na zywo", Category.RENDER, false);
    }
    public static boolean canReplaceVanilla(Minecraft client) {
        var module = KolegaClient.MODULES.find("crosshair");
        return module != null && module.enabled()
            && client.player != null
            && client.screen == null
            && !client.options.hideGui
            && client.options.getCameraType().isFirstPerson()
            && !client.player.isSpectator();
    }
}
