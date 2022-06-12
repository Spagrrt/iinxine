package util;

public class Time {
    public static final float startTime = System.nanoTime();

    public static float getTime() {
        return (float)((System.nanoTime() - startTime) * 1E-9);
    }
}
