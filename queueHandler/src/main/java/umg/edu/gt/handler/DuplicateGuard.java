package umg.edu.gt.handler;

/**
 * Validación para evitar canciones duplicadas (estructura propia con arreglo dinámico).
 * Clave: title|artist (case-insensitive).
 */
public class DuplicateGuard {

    private String[] keys = new String[10];
    private int size = 0;

    public boolean isDuplicate(Song song) {
        String key = makeKey(song);
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) return true;
        }
        return false;
    }

    public void register(Song song) {
        String key = makeKey(song);
        if (size == keys.length) {
            String[] newArr = new String[keys.length * 2];
            for (int i = 0; i < keys.length; i++) newArr[i] = keys[i];
            keys = newArr;
        }
        keys[size++] = key;
    }

    private String makeKey(Song song) {
        return (song.getTitle() + "|" + song.getArtist()).toLowerCase();
    }
}
