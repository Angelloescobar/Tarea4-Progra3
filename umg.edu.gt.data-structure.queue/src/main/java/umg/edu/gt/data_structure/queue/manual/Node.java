package umg.edu.gt.data_structure.queue.manual;

/**
 * Nodo genérico enlazado.
 * No se expone fuera de la librería (package-private).
 */
class Node<T> {
    T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }
}
