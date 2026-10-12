# Práctica 1 – Java (Programación Orientada a Objetos)

Proyecto de práctica en Java desarrollado en Campuslands. Reúne cinco ejercicios de consola que trabajan los pilares de la programación orientada a objetos (clases, encapsulamiento, herencia), además de condicionales, ciclos, colecciones, validación de datos, manejo de excepciones y lectura y escritura de archivos. Cada ejercicio incluye la versión base y sus expansiones.

## Ejercicios

### 1. Cajero automático (`CajeroAutomatico`)
Simula un cajero con inicio de sesión por titular y PIN (máximo 3 intentos), y un menú para consultar saldo, retirar, depositar, transferir y ver los últimos movimientos.

- Transferencias entre cuentas.
- Historial con las últimas 5 transacciones.
- Varias cuentas administradas por una clase `Banco`.
- Herencia: `Cuenta` (abstracta), `CuentaBancaria`, `CuentaAhorro` (interés al depositar) y `CuentaCorriente` (límite de sobregiro).

### 2. Registro académico (`RegistroAcademico`)
Registra estudiantes con sus asignaturas y calcula estadísticas del grupo.

- Cada estudiante tiene una lista de asignaturas y su promedio.
- Control de asistencia: aprueba con promedio mínimo de 6 y menos de 10 faltas.
- Clase `Curso` con promedio general, aprobados, reprobados, mejor y peor promedio.
- Búsqueda de estudiantes, modificación de notas y reporte del curso en un archivo `.txt`.

### 3. Tienda online (`TiendaOnline`)
Catálogo de productos con carrito de compras y factura final.

- Categorías y filtrado del catálogo.
- Descuento del 10% cuando el subtotal supera los 1000.
- Herencia: `Producto`, `ProductoAlimenticio` y `ProductoElectronico`.
- Clase `Cliente` con inicio de sesión por email.
- Clase `Inventario` que controla el stock.

### 4. Agenda de contactos (`AgendaContactos`)
Agenda para registrar, buscar, listar y eliminar contactos.

- Almacenamiento en `ArrayList`, sin límite de tamaño.
- Validación de teléfono (solo números y longitud definida) y de email.
- Búsqueda parcial por nombre.
- Contactos organizados por grupo (Familia, Trabajo, Amigos).
- Persistencia en el archivo `agenda.txt`.

### 5. Sistema de notas con curva (`SistemaNotas`)
Calcula notas finales con ponderaciones y aplica una curva cuando el promedio del curso es menor a 6.

- Ponderaciones: parcial 1 (30%), parcial 2 (30%) y examen final (40%).
- Tres tipos de curva: puntos fijos, porcentual y basada en el mejor alumno.
- Manejo de `InputMismatchException` al ingresar las notas.
- Estadísticas finales: nueva media, desviación estándar y alumnos que aprueban gracias a la curva.

## Estructura del proyecto

Cada ejercicio sigue la misma organización:

- **Clases de datos y lógica:** representan los objetos del problema y no leen ni imprimen nada.
- **Clase de gestión:** concentra el `Scanner`, los menús y los mensajes por consola.
- **`Main`:** crea la clase de gestión y la inicia.

```
src/main/java
├── CajeroAutomatico
├── RegistroAcademico
├── TiendaOnline
├── AgendaContactos
├── SistemaNotas
└── Principal        <- menú que permite abrir cualquiera de los cinco ejercicios
```

## Cómo ejecutarlo

1. Tener instalado un JDK (se recomienda la versión 17 o superior).
2. Abrir el proyecto en IntelliJ IDEA.
3. Ejecutar el método `main` de la clase `Principal` y elegir un ejercicio desde el menú.

Cada ejercicio también se puede ejecutar por separado desde su propia clase `Main`.

## Conceptos aplicados

- Clases, objetos, constructores y encapsulamiento.
- Herencia, clases abstractas y sobrescritura de métodos (`@Override`, `super`).
- Colecciones (`ArrayList`) y arreglos.
- Condicionales, ciclos y menús con `switch`.
- Validación de datos y manejo de excepciones.
- Lectura y escritura de archivos de texto.

## Autora

Adriana Reatigui
