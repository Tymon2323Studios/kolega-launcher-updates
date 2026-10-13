import pl.kolegaclient.input.ZoomSmoother;

public final class ZoomSmootherTest {
    private static void ok(boolean yes, String msg) { if (!yes) throw new AssertionError(msg); }
    private static double atFps(int fps, double seconds, double zoom) {
        ZoomSmoother smoother = new ZoomSmoother();
        smoother.sample(1, 1_000_000L);
        int frames = (int) Math.round(seconds * fps);
        long start = 1_000_000L;
        for (int i = 1; i <= frames; i++) {
            smoother.sample(zoom, start + (long) (1_000_000_000.0 * i / fps));
        }
        return smoother.current();
    }
    public static void main(String[] args) {
        for (int fps: new int[]{30, 60, 144, 240}) {
            ZoomSmoother z = new ZoomSmoother();
            z.sample(1, 1_000_000L);
            double last = 1.0;
            for (int f = 1; f < fps; f++) {
                double value = z.sample(4.0, 1_000_000L + (long)(1e9 * f / fps));
                ok(value >= last - 1e-9 && value <= 4.0, "Bad zoom in progression at " + fps);
                last = value;
            }
            ok(last >= 3.99, "Zoom in never converges at " + fps);
        }
        double a = atFps(30, .25, 4.0), b = atFps(60, .25, 4.0), c = atFps(144, .25, 4.0);
        ok(Math.abs(a-b) < .12 && Math.abs(b-c) < .12, "FPS-dependent easing");
        ZoomSmoother z = new ZoomSmoother();
        long t = 10_000L;
        z.sample(1, t);
        for (int i=0; i<11; i++) { t += 16_666_667L; z.sample(7, t); }
        double before = z.current();
        t += 16_666_667L;
        double after = z.sample(1, t);
        ok(after >= 1 && after <= 7, "Out of bounds on reversal");
        ok(Math.abs(after-before) < 0.4, "Snap when reversing zoom");
        for (int i=0; i<100; i++) {t += 16_666_667L; z.sample(1, t);}
        ok(Math.abs(z.current()-1) < .01, "Zoom out not converged");
        double current = z.current();
        ok(z.sample(1, t) == current, "Repeated frame changed zoom");
        ok(z.sample(1, t-10) == current, "Reversed frame time changed zoom");
        ok(z.sample(4, t+600_000_000L) == 4.0, "Pause recovery failed");
        ok(z.sample(-5, t+600_000_001L) <= 4.0, "Invalid target leaked");
        System.out.println("[OK] Smooth Zoom: FPS independence, bounds, reversing, pauses and timestamps");
    }
}
