# 🎵 Spotify Simulation using Custom Queue (FIFO)

## 📌 Descripción del Proyecto

Este proyecto implementa una simulación tipo Spotify utilizando una estructura de datos **Cola (FIFO)** desarrollada completamente desde cero en Java.

El sistema fue desarrollado con arquitectura modular utilizando **Maven**, separando:

- 📦 Librería de estructura de datos
- 🎧 Proyecto consumidor (simulación de reproducción)

No se utilizan estructuras del JDK como `Queue`, `LinkedList`, `ArrayDeque` ni `PriorityQueue`.

---

# 🏗 Arquitectura del Repositorio

/umg.edu.gt.data-structure.queue  
/queueHandler  
/README.md  
/evidencias  

---

# 🧱 Parte A — Librería de Cola Propia

Proyecto:

umg.edu.gt.data-structure.queue

## 🔹 Implementación

Se implementó una cola genérica:

Queue<T>

Basada en:

- Clase interna `Node<T>`
- Referencias privadas `head` y `tail`
- Variable interna `size`
- Encapsulamiento completo

## 🔹 Métodos implementados

- `enqueue(T item)` → O(1)
- `dequeue()` → O(1)
- `peek()`
- `isEmpty()`
- `size()`

## 🔹 Decisiones Técnicas

- `enqueue()` es O(1) porque se inserta directamente en `tail`.
- `dequeue()` es O(1) porque se elimina directamente desde `head`.
- Cuando la cola queda vacía se asigna `head = null` y `tail = null`.
- Se lanza una excepción controlada si se hace `dequeue()` en una cola vacía.
- No se exponen nodos internos.

---

# ⚙ Cómo Compilar la Librería

Desde la carpeta:

umg.edu.gt.data-structure.queue

Ejecutar:

mvn clean install

Esto instala la librería en el repositorio local (`.m2`).

---

# 🎵 Parte B — Simulación de Reproducción

Proyecto:

queueHandler

Este proyecto consume la librería personalizada.

## 🔹 Modelo Song

Cada canción contiene:

- `title`
- `artist`
- `duration` (entre 5 y 30 segundos)
- `priority` (1 = alta, 2 = normal)

Las duraciones varían para simular comportamiento real.

---

# ▶️ Simulación Realista

La reproducción se realiza segundo a segundo usando:

Thread.sleep(1000)

Comportamiento implementado:

- `[LOG] Starting playlist...`
- `[LOG] Now playing: ...`
- Progreso por segundo:
  `[LOG] Playing: Song | 5s / 12s`
- Barra visual de progreso:
  `[#####-----] 5s / 10s`
- `[LOG] Finished: ...`
- `[LOG] Playlist finished.`

No se utilizan librerías externas de logging.

---

# 🔥 Parte C — Sistema de Prioridad

Para implementar prioridad sin usar `PriorityQueue`, se utilizaron:

- Una cola para prioridad alta
- Una cola para prioridad normal

### Funcionamiento:

1. Primero se reproducen todas las canciones de prioridad 1.
2. Luego se reproducen las de prioridad 2.
3. Dentro de cada prioridad se respeta FIFO.

Ejemplo:

Alta:
A1, A2

Normal:
N1, N2, N3

Salida:
A1, A2, N1, N2, N3

---

# 🚀 Parte D — Extensiones Implementadas

Se implementaron las siguientes mejoras:

- ✅ Historial de canciones reproducidas
- ✅ Contador total de canciones reproducidas
- ✅ Tiempo total acumulado reproducido
- ✅ Barra de progreso visual
- ✅ Validación para evitar canciones duplicadas

---

# 📦 Cómo Compilar el Handler

Desde la carpeta:

queueHandler

Ejecutar:

mvn clean package

---

# ▶️ Cómo Ejecutar Desde Consola

Dentro de:

queueHandler

Ejecutar:

java -jar target/queueHandler-1.0.0-jar-with-dependencies.jar

El sistema ejecutará la simulación completa mostrando:

- Logs detallados
- Progreso segundo a segundo
- Sistema de prioridad funcionando
- Historial final

---

# 🧠 Explicación del Diseño

Se optó por una arquitectura modular para separar responsabilidades:

- La librería contiene exclusivamente la implementación de la estructura de datos.
- El handler contiene la lógica de negocio y simulación.

Esto permite reutilización de la estructura de datos en otros proyectos.

La prioridad se resolvió utilizando dos colas internas para mantener O(1) en las operaciones y respetar FIFO sin romper el diseño original.

La simulación usa pausas reales con `Thread.sleep` para garantizar una experiencia realista.

---

# 📸 Evidencias

La carpeta `evidencias/` incluye capturas de:

- Instalación de la librería
- Compilación del handler
- Ejecución completa desde consola
- Reproducción segundo a segundo
- Sistema de prioridad funcionando

---

# 🛠 Requisitos

- Java 8 o superior
- Maven

---

# ✅ Estado del Proyecto

✔ Compila correctamente  
✔ Ejecuta desde consola  
✔ Implementa FIFO manual  
✔ Soporta prioridad  
✔ Simulación realista  
✔ Logs detallados  
✔ Arquitectura modular Maven  
