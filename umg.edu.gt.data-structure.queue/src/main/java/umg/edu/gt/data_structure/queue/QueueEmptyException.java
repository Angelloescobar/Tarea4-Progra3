package umg.edu.gt.data_structure.queue;

/**
 * Excepción controlada para operaciones inválidas en una cola vacía.
 */
public class QueueEmptyException extends Exception {

    public QueueEmptyException(String message) {
        super(message);
    }
}
