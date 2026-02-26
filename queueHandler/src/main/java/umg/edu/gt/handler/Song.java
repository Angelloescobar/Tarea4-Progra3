package umg.edu.gt.handler;

/**
 * Modelo de canción para la simulación.
 * duration: segundos (5 a 30)
 * priority: 1 alta, 2 normal
 */
public class Song {

    private final String title;
    private final String artist;
    private final int durationSeconds;
    private final int priority;

    public Song(String title, String artist, int durationSeconds, int priority) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("title is required");
        if (artist == null || artist.trim().isEmpty()) throw new IllegalArgumentException("artist is required");
        if (durationSeconds < 5 || durationSeconds > 30) throw new IllegalArgumentException("duration must be 5..30 seconds");
        if (priority != 1 && priority != 2) throw new IllegalArgumentException("priority must be 1 (high) or 2 (normal)");

        this.title = title.trim();
        this.artist = artist.trim();
        this.durationSeconds = durationSeconds;
        this.priority = priority;
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDurationSeconds() { return durationSeconds; }
    public int getPriority() { return priority; }

    public String displayName() {
        return title + " - " + artist + " (" + durationSeconds + "s)";
    }
}
