package umg.edu.gt.handler;

import java.util.Random;

/**
 * Ejecuta la simulación desde consola.
 *
 * Para correr:
 * 1) Instalar librería (Parte A): mvn clean install   (dentro de umg.edu.gt.data-structure.queue)
 * 2) Empaquetar handler:          mvn clean package  (dentro de queueHandler)
 * 3) Ejecutar: java -jar target/queueHandler-1.0.0-jar-with-dependencies.jar
 */
public class Main {

    public static void main(String[] args) {
        PriorityPlaylistPlayer player = new PriorityPlaylistPlayer();

        // Canciones de demo (duraciones 5..30, variando; prioridades mezcladas)
        Random r = new Random();
        player.addSong(new Song("A1", "Artist A", randomDuration(r), 1));
        player.addSong(new Song("A2", "Artist A", randomDuration(r), 1));
        player.addSong(new Song("N1", "Artist N", randomDuration(r), 2));
        player.addSong(new Song("N2", "Artist N", randomDuration(r), 2));
        player.addSong(new Song("N3", "Artist N", randomDuration(r), 2));

        // Evidencia de anti-duplicados (se ignora)
        player.addSong(new Song("N2", "Artist N", randomDuration(r), 2));

        // Play
        player.playAll();

        // Mostrar historial (extensión)
        player.printHistory();
    }

    private static int randomDuration(Random r) {
        // 5..30 inclusive
        return 5 + r.nextInt(26);
    }
}
