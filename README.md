# 🚢 Batalla Naval 2.0

Autores:  
Nicolás Antonio Sánchez Bautista  
Juan José Herrada Coronell  
Andrés Felipe Sarmiento Martínez  
Manuel Arturo Fajardo Contreras  
Santiago Mauricio Navarro Sánchez  

Departamento de Ingeniería de Sistemas e Industrial  
Universidad Nacional de Colombia - Sede Bogotá  

---

## 🎯 Objetivos

- Aplicar los principios de Programación Orientada a Objetos (POO) para desarrollar un juego tipo Battleship.
- Desarrollar una interfaz de usuario simple que permita visualizar el progreso del juego.
- Fomentar buenas prácticas de programación: código limpio, comentado y con nombres significativos.

---

## 📘 Contexto y justificación

Este proyecto implementa el clásico juego de Battleship como ejercicio práctico para aplicar los fundamentos de la POO: clases, objetos, encapsulamiento, herencia y polimorfismo.  
Permite modelar barcos, tableros y jugadores, logrando una estructura clara, modular y reutilizable. Además, busca afianzar habilidades que serán útiles para proyectos más grandes y profesionales.

---

## 🔗 Repositorio del proyecto

[https://github.com/zuchus/Proyecto-POO](https://github.com/zuchus/Proyecto-POO)

---

## ✅ Requisitos funcionales

- Registro de jugadores con nombre personalizado.
- Generación visual e interactiva de tableros 10x10 por jugador.
- Posicionamiento manual o automático de barcos, con validación de límites y colisiones.
- Mecánica de turnos con ataques por coordenadas.
- Notificación visual de impactos, hundimientos y estado de flota.
- Detección automática de victoria y finalización de partida.
- Registro del progreso (monedas, estadísticas) en archivos locales.

---

## 🚫 Requisitos no funcionales

- Carga inicial del juego en menos de 8 segundos.
- Interacciones rápidas y fluidas.
- Código organizado en clases separadas con nombres claros.
- Arquitectura pensada para agregar nuevos elementos fácilmente.
- Experiencia de usuario clara e intuitiva.
- Almacenamiento de datos local (sin perder progreso).
- Manejo de errores con mensajes amigables.
- Funcionamiento completamente offline.
- Compatibilidad con Windows (y potencialmente Linux/Mac/Android).

---

## 🧩 Historias de Usuario

1. Menú inicial con opciones: Jugar, Instrucciones, Salir.
2. Crear nombre de jugador.
3. Jugar sin conexión a Internet.
4. Asignar dificultad a la CPU.
5. Seleccionar tamaño del tablero.
6. Visualizar el tablero y barcos disponibles.
7. Posicionar barcos manualmente (horizontal o vertical).
8. Posición automática de barcos.
9. Atacar coordenadas del tablero enemigo.
10. Notificación de impactos.
11. Sistema de turnos alternos.
12. Ver ataques recibidos y sus efectos.
13. Notificación al destruir un barco.
14. Mensaje de victoria cuando alguien gana.
15. Opción de reiniciar o cerrar al finalizar.
16. Historial de partidas ganadas/perdidas.
17. Acceso a estadísticas del jugador.
18. Guardar progreso para retomar después.

---

## ✅ Historias de Usuario Implementadas (2ª entrega)

- **HU-01:** Menú inicial con validación de entrada.
- **HU-02:** Registro de nombre del jugador.
- **HU-03:** Selección del tamaño del tablero (5-15).
- **HU-04:** Posicionamiento automático de barcos válidos.
- **HU-05:** Ataques por coordenadas (humano y CPU).
- **HU-06:** Finalización automática al hundir toda la flota.

---

## 📐 Diagrama de clases UML

*(El diagrama debe insertarse aquí como imagen, si está disponible)*
