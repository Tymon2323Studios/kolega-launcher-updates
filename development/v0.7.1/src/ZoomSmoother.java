package pl.kolegaclient.input;

/**
 * Frame-rate independent, critically damped zoom animation.
 * Keeps the zoom FOV interpolation independent of Minecraft's 20Hz game tick.
 * The time source is injectable so it can be tested without Minecraft or GLFW.
 */
public final class ZoomSmoother {
    private double factor = 1.0;
    private double velocity;
    private long lastFrameNanos = Long.MIN_VALUE;

    /** @param target 1.0 (normal view) or desired zoom multiplier, between 2 and 8. */
    public double sample(double target, long frameNanos) {
        target = Math.max(1.0, Math.min(8.0, target));
        if (lastFrameNanos == Long.MIN_VALUE) {
            lastFrameNanos = frameNanos;
            return factor;
        }
        long elapsed = frameNanos - lastFrameNanos;
        lastFrameNanos = frameNanos;
        if (elapsed <= 0L) return factor;
        // A pause/alt-tab must not fast-forward a long animation unexpectedly.
        if (elapsed > 500_000_000L) {
            factor = target;
            velocity = 0.0;
            return factor;
        }

        double dt = elapsed * 1e-9;
        double omega = target >= factor ? 22.0 : 19.0;
        double distance = factor - target;
        double c = velocity + omega * distance;
        double decay = Math.exp(-omega * dt);
        double next = target + (distance + c * dt) * decay;
        double nextVelocity = (velocity - omega * c * dt) * decay;

        factor = Math.max(1.0, Math.min(8.0, next));
        velocity = factor == next ? nextVelocity : 0.0;
        if (Math.abs(factor - target) < 0.001 && Math.abs(velocity) < 0.01) {
            factor = target;
            velocity = 0.0;
        }
        return factor;
    }

    public double current() { return factor; }
}
