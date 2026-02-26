# Simulación tipo Spotify con Cola (FIFO) hecha desde cero (Java + Maven)

Repositorio con **dos proyectos Maven**:

- `umg.edu.gt.data-structure.queue/` → **Librería** (Parte A): implementación manual de una Cola genérica `Queue<T>` usando nodos enlazados.
- `queueHandler/` → **Aplicación** (Parte B/C/D): simulación de reproducción tipo Spotify que **consume la librería** y agrega prioridad + extensiones.

Estructura mínima solicitada:

```
/umg.edu.gt.data-structure.queue
/queueHandler
/README.md
/evidencias
```

---

## ✅ Requisitos

- Java 8 o superior
- Maven
- **No** se usa `Queue`, `LinkedList`, `ArrayDeque` ni otras estructuras del JDK para la cola.
- **No** se usan librerías externas de logging (se implementa `Logger` propio).

---

## 🧱 Parte A — Librería de Cola Propia

**Proyecto:** `umg.edu.gt.data-structure.queue`

### Diseño

- `Queue<T>` (interfaz): define `enqueue`, `dequeue`, `peek`, `isEmpty`, `size`.
- `LinkedQueue<T>`: implementación **FIFO** con nodos enlazados.
- `Node<T>`: nodo genérico enlazado (no se expone fuera del paquete).
- `QueueEmptyException`: **excepción controlada** para `dequeue()`/`peek()` en cola vacía.

### Complejidad

- `enqueue` → **O(1)** (se inserta directo en `tail`)
- `dequeue` → **O(1)** (se remueve directo de `head`)
- Se maneja correctamente cuando la cola queda vacía (`head == null` ⇒ `tail = null`).

### Compilar e instalar en local

Dentro de `umg.edu.gt.data-structure.queue/`:

```bash
mvn clean install
```

Esto instala el JAR en tu repositorio local (`~/.m2`) para que el proyecto consumidor pueda compilar.

---

## 🎵 Parte B — Simulación de reproducción (reproductor tipo Spotify)

**Proyecto:** `queueHandler`

### Modelo obligatorio

Clase `Song` con:

- `title`
- `artist`
- `duration` (5 a 30 segundos)
- `priority` (1 = alta, 2 = normal)

Las duraciones **varían** (se generan aleatoriamente entre 5 y 30).

### Logs y reproducción segundo a segundo (OBLIGATORIO)

- Inicio: `Starting playlist...`
- Al reproducir: `Now playing: ...`
- Progreso **cada segundo** con `Thread.sleep(1000)`
- Final: `Finished: ...`
- Al terminar: `Playlist finished.`

**Logging:** se implementa `Logger` propio (sin log4j, sin slf4j).

---

## 🔥 Parte C — Sistema de Prioridad

Estrategia usada: **dos colas internas**:

- Cola alta (`priority = 1`)
- Cola normal (`priority = 2`)

Reglas:

- Siempre se reproduce primero la cola alta.
- **Dentro de cada cola se respeta FIFO**.

Ejemplo:

Alta: `A1, A2`  
Normal: `N1, N2, N3`  
Salida: `A1, A2, N1, N2, N3`

---

## 🚀 Parte D — Extensiones implementadas (4)

Se implementaron **más de 2**:

1. **Historial** de canciones reproducidas (estructura propia `SongHistory`, lista enlazada simple).
2. **Contador total** de canciones reproducidas.
3. **Tiempo total acumulado** reproducido (segundos).
4. **Barra de progreso visual**:

   ```
   [#####---------------] 5s / 20s
   ```

5. **Validación anti-duplicados** (estructura propia `DuplicateGuard` con arreglo dinámico).

---

## 🧪 Cómo compilar el handler

> Importante: primero instala la librería (Parte A).

Dentro de `queueHandler/`:

```bash
mvn clean package
```

Se genera un JAR ejecutable con dependencias:

- `target/queueHandler-1.0.0-jar-with-dependencies.jar`

---

## ▶️ Cómo ejecutar desde consola

Dentro de `queueHandler/`:

```bash
java -jar target/queueHandler-1.0.0-jar-with-dependencies.jar
```

---

## 📸 Evidencias

Carpeta: `evidencias/`

Incluye archivos de texto con salidas de ejemplo.  
Si tu profesor pide **capturas**, ejecuta los comandos en tu PC y toma screenshots de:

- `mvn clean install` (librería)
- `mvn clean package` (handler)
- ejecución del `java -jar ...`
- logs con reproducción segundo a segundo
- prioridad funcionando (A1, A2 antes que N1, N2, N3)

---

## 📌 Nota de aprendizaje (explicación sin código)

**Cómo se logró O(1) en enqueue/dequeue:**

- Se guardan **dos referencias**:
  - `head`: primer nodo (el que sale primero)
  - `tail`: último nodo (donde se inserta)
- **enqueue**: se conecta `tail.next` al nuevo nodo y se actualiza `tail`.
- **dequeue**: se avanza `head = head.next`.
- Si después de sacar un elemento `head` queda `null`, entonces la cola está vacía y también `tail` se vuelve `null`.
- `size` se incrementa/disminuye para saber rápido cuántos elementos hay.

**Cómo se implementó la prioridad sin romper FIFO:**

- Se usan **dos colas**:
  - Una para prioridad 1
  - Una para prioridad 2
- Siempre se intenta sacar (`dequeue`) de la cola de prioridad 1.
- Cuando la cola 1 está vacía, se comienza a sacar de la cola 2.
- Cada cola por separado mantiene FIFO.

**Cómo se simuló la reproducción realista:**

- Para cada canción se recorre `segundo = 1..duration`
- En cada segundo:
  - Se imprime un log con el progreso
  - Se espera exactamente 1 segundo con `Thread.sleep(1000)`

---
