package umg.edu.gt.handler;

/**
 * Historial de canciones reproducidas (estructura propia).
 * Implementación con lista enlazada simple.
 */
public class SongHistory {

    private static class Node {
        Song value;
        Node next;
        Node(Song value) { this.value = value; }
    }

    private Node head;
    private Node tail;
    private int size;

    public void add(Song song) {
        Node n = new Node(song);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    public int size() { return size; }

    public void print() {
        Logger.log("History (" + size + "):");
        Node cur = head;
        int i = 1;
        while (cur != null) {
            Logger.log("  " + (i++) + ") " + cur.value.displayName() + " | priority=" + cur.value.getPriority());
            cur = cur.next;
        }
    }
}
