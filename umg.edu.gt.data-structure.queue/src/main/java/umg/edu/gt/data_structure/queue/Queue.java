package umg.edu.gt.data_structure.queue;

/**
 * Cola FIFO (First-In, First-Out) implementada manualmente.
 * No depende de java.util.Queue.
 */
public interface Queue<T> {

    /** Inserta al final (tail). Debe ser O(1). */
    void enqueue(T item);

    /** Elimina y retorna el primero (head). Debe ser O(1). */
    T dequeue() throws QueueEmptyException;

    /** Retorna el primero sin eliminarlo. */
    T peek() throws QueueEmptyException;

    boolean isEmpty();

    int size();
}
