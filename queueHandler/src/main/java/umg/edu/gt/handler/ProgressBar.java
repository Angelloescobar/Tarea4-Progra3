package umg.edu.gt.handler;

/**
 * Barra de progreso visual:
 * [#####-----] 5s / 10s
 */
public final class ProgressBar {

    private ProgressBar() {}

    public static String render(int current, int total) {
        int width = 20;
        if (total <= 0) total = 1;
        if (current < 0) current = 0;
        if (current > total) current = total;

        int filled = (int) Math.round((current * 1.0 / total) * width);
        if (filled < 0) filled = 0;
        if (filled > width) filled = width;

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < filled; i++) sb.append("#");
        for (int i = filled; i < width; i++) sb.append("-");
        sb.append("] ").append(current).append("s / ").append(total).append("s");
        return sb.toString();
    }
}
