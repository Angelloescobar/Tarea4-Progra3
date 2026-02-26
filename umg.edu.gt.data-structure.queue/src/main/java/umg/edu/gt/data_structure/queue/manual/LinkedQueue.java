package umg.edu.gt.data_structure.queue.manual;

import umg.edu.gt.data_structure.queue.Queue;
import umg.edu.gt.data_structure.queue.QueueEmptyException;

/**
 * Cola enlazada genérica con referencias head/tail.
 * enqueue y dequeue son O(1).
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> head; // primero en salir
    private Node<T> tail; // último en entrar
    private int size;

    @Override
    public void enqueue(T item) {
        Node<T> node = new Node<>(item);

        if (isEmpty()) {
            head = tail = node;
        } else {
            // O(1): insertamos directo al tail
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public T dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Cannot dequeue from an empty queue.");
        }

        T value = head.value;
        head = head.next;
        size--;

        // si quedó vacía, tail también debe quedar null
        if (head == null) {
            tail = null;
        }

        return value;
    }

    @Override
    public T peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Cannot peek from an empty queue.");
        }
        return head.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
