package pl.kolegaclient.ui;

/** Persistent local-only crosshair appearance. Does not affect Minecraft input or packets. */
public final class CrosshairSettings {
    public enum Shape { PLUS, DOT, CIRCLE, X, DIAMOND, DYNAMIC }
    public enum ColorMode { ACCENT, CUSTOM, RAINBOW }
    private Shape shape = Shape.PLUS;
    private ColorMode colorMode = ColorMode.ACCENT;
    private int size = 7, gap = 4, thickness = 2, opacity = 100;
    private int customColor = 0xC47AFF;
    private boolean outline = true, centerDot = false;
    public Shape shape() { return shape; }
    public void shape(Shape x) { if (x != null) shape = x; }
    public ColorMode colorMode() { return colorMode; }
    public void colorMode(ColorMode x) { if (x != null) colorMode = x; }
    public int size() { return size; }
    public void size(int x) { size = clamp(x, 2, 18); }
    public int gap() { return gap; }
    public void gap(int x) { gap = clamp(x, 0, 14); }
    public int thickness() { return thickness; }
    public void thickness(int x) { thickness = clamp(x, 1, 5); }
    public int opacity() { return opacity; }
    public void opacity(int x) { opacity = clamp(x, 20, 100); }
    public int customColor() { return customColor; }
    public void customColor(int x) { customColor = x & 0xFFFFFF; }
    public boolean outline() { return outline; }
    public void outline(boolean x) { outline = x; }
    public boolean centerDot() { return centerDot; }
    public void centerDot(boolean x) { centerDot = x; }
    public int channel(int index) { return (customColor >> ((2 - index) * 8)) & 255; }
    public void channel(int index, int value) {
        if (index < 0 || index > 2) return;
        int bit = (2-index) * 8;
        customColor = (customColor & ~(255 << bit)) | (clamp(value,0,255) << bit);
    }
    public int argb(int accent, long timeMs) {
        int rgb = switch(colorMode) {
            case ACCENT -> accent & 0xFFFFFF;
            case CUSTOM -> customColor;
            case RAINBOW -> java.awt.Color.HSBtoRGB((timeMs % 8000) / 8000.0f, 0.72f, 1.0f) & 0xFFFFFF;
        };
        return ((opacity * 255 / 100) << 24) | rgb;
    }
    public void reset() {
        shape = Shape.PLUS; colorMode = ColorMode.ACCENT;
        size = 7; gap = 4; thickness = 2; opacity = 100; customColor=0xC47AFF;
        outline = true; centerDot = false;
    }
    private static int clamp(int n, int lo, int hi) { return Math.max(lo, Math.min(hi, n)); }
}