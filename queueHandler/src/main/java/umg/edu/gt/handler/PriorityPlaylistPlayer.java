package umg.edu.gt.handler;

import umg.edu.gt.data_structure.queue.Queue;
import umg.edu.gt.data_structure.queue.QueueEmptyException;
import umg.edu.gt.data_structure.queue.manual.LinkedQueue;

/**
 * Reproductor con prioridad:
 * - priority=1 (alta) se reproduce antes
 * - dentro de cada prioridad se mantiene FIFO
 *
 * Extensiones implementadas:
 * - Historial de canciones
 * - Contador total reproducidas
 * - Tiempo total acumulado
 * - Barra de progreso visual
 * - Validación anti-duplicados
 */
public class PriorityPlaylistPlayer {

    private final Queue<Song> high = new LinkedQueue<>();
    private final Queue<Song> normal = new LinkedQueue<>();

    private final SongHistory history = new SongHistory();
    private final DuplicateGuard duplicateGuard = new DuplicateGuard();

    private int totalPlayedSongs = 0;
    private int totalPlayedSeconds = 0;

    public void addSong(Song song) {
        if (duplicateGuard.isDuplicate(song)) {
            Logger.warn("Duplicate ignored: " + song.displayName());
            return;
        }
        duplicateGuard.register(song);

        if (song.getPriority() == 1) {
            high.enqueue(song);
            Logger.log("Added (HIGH): " + song.displayName());
        } else {
            normal.enqueue(song);
            Logger.log("Added (NORMAL): " + song.displayName());
        }
    }

    public boolean isEmpty() {
        return high.isEmpty() && normal.isEmpty();
    }

    public void playAll() {
        Logger.log("Starting playlist...");

        while (!isEmpty()) {
            Song next = pollNextSong();
            playSong(next);
        }

        Logger.log("Playlist finished.");
        Logger.log("Total played songs: " + totalPlayedSongs);
        Logger.log("Total played time: " + totalPlayedSeconds + "s");
    }

    private Song pollNextSong() {
        try {
            if (!high.isEmpty()) {
                return high.dequeue();
            }
            return normal.dequeue();
        } catch (QueueEmptyException e) {
            // No debería pasar por el while(!isEmpty()), pero lo dejamos seguro.
            throw new IllegalStateException("Playlist inconsistent state: " + e.getMessage(), e);
        }
    }

    private void playSong(Song song) {
        Logger.log("Now playing: " + song.displayName() + " | priority=" + song.getPriority());

        int duration = song.getDurationSeconds();
        for (int second = 1; second <= duration; second++) {
            Logger.log("Playing: " + song.getTitle() + " | " + ProgressBar.render(second, duration));
            sleepOneSecond();
        }

        Logger.log("Finished: " + song.getTitle());
        history.add(song);

        totalPlayedSongs++;
        totalPlayedSeconds += duration;
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.warn("Playback interrupted.");
        }
    }

    public void printHistory() {
        history.print();
    }
}
