<p align="center" style="overflow: hidden; max-height: 220px; border-radius: 8px;">
  <img src="https://github.com/user-attachments/assets/25e4f01a-3630-4b3d-98b8-db9cac54ba1f"
       alt="Brasa en Casa - Experiencias al Carbón"
       width="100%"
       style="object-fit: cover; object-position: center 40%; margin-top: -60px; margin-bottom: -60px;"/>
</p>

# 🔥 Brasa en Casa — Sistema de Pedidos

> Sistema de gestión de pedidos en consola para el restaurante **Brasa en Casa**, desarrollado en Java. Permite a los clientes navegar la carta por categorías, armar su pedido con variantes de cada plato y confirmar el total. Incluye panel de administración con reporte de ventas y gestión de precios.

---

## Descripción

**Brasa en Casa** es un restaurante de parrilla colombiana ubicado en Medellín. Este sistema simula el flujo completo de atención al cliente desde consola:

- Navegación de la carta agrupada por secciones
- Selección de variantes por producto (tamaño / porción)
- Pedido acumulado con subtotales y total en tiempo real
- Confirmación y cierre de pedido
- Panel de administrador con clave de acceso

---

## Estructura del menú

| Sección        | Categorías incluidas                              |
|----------------|---------------------------------------------------|
| Entradas       | Entradas                                          |
| Platos Fuertes | Ensaladas, Cortes Gruesos, Mixtos 3 Carnes        |
| Picadas        | Picadas, Picadas de la Casa                       |
| Sánduches      | Sánduches                                         |
| Bebidas        | Limonadas, Cócteles, Otros                        |
| Postres        | Postres de la Brasa                               |

---

## Requisitos

- Java 11 o superior
- Sin dependencias externas

---

## Estructura del proyecto

```
BrasaEnCasa_ConsolaApp/
├── src/
│   ├── App.java              — Clase principal (flujo, menús, caja, admin)
│   └── Models/
│       ├── Variante.java     — Tamaño + precio de una presentación
│       ├── Producto.java     — Nombre, descripción, categoría, variantes
│       └── ItemPedido.java   — Producto + variante elegida + cantidad
├── bin/                      — Clases compiladas (.class)
└── README.md
```

---

## Cómo ejecutar

Desde la raíz del proyecto:

```bash
# Compilar (genera los .class en bin/)
javac -encoding UTF-8 -d bin src/App.java src/Models/*.java

# Ejecutar
java -cp bin App
```

> El flag `-encoding UTF-8` asegura que tildes y caracteres especiales se muestren correctamente.

---

## Flujo del cliente

```
Menú principal
  └── Ver carta
        └── Seleccionar sección  (Entradas / Platos Fuertes / Picadas / Sánduches / Bebidas / Postres)
              └── Seleccionar producto
                    └── Seleccionar variante/tamaño  (si aplica)
                          └── Ingresar cantidad
                                └── Ítem agregado al pedido ✔
  └── Ver pedido actual         → Ver ítems, subtotales, total | Eliminar ítems
  └── Cerrar pedido y pagar     → Confirmación → Pedido enviado a cocina
  └── Cancelar pedido
```

---

## Panel de Administración

Clave de acceso: `brasa2026`

| Opción                  | Descripción                                      |
|-------------------------|--------------------------------------------------|
| Reporte de ventas       | Total vendido y saldo en caja                    |
| Ver carta completa      | Listado de todos los productos con precios       |
| Cambiar precio          | Modificar precio por producto y variante         |
| Retirar dinero de caja  | Retiro con validación de saldo disponible        |

---

## Arquitectura

El sistema está separado en una clase principal y un paquete de modelos:

### `src/App.java` — Clase principal

Contiene el estado global (carta, secciones, pedido activo, caja) y todos los métodos del flujo:

```
App.java
├── inicializarCarta()       — carga los 30+ productos del menú real
├── inicializarSecciones()   — agrupa categorías en secciones del menú
├── main()                   — bucle principal con menú raíz
├── menuCarta()              — navegación por secciones
├── mostrarSeccion()         — lista productos por categoría interna
├── agregarAlPedido()        — selección de variante y cantidad
├── verPedido()              — resumen del pedido activo
├── eliminarItem()           — eliminar ítems del pedido
├── cerrarPedido()           — confirmación y registro en caja
├── cancelarPedido()         — descarte del pedido activo
├── autenticarAdmin()        — validación de clave de admin
├── menuAdmin()              — panel de administración
├── reporteVentas()          — total vendido y saldo en caja
├── verStockAdmin()          — carta completa con precios
├── cambiarPrecioAdmin()     — editar precios por variante
└── retirarDineroAdmin()     — retiro con validación de saldo
```

### `src/Models/` — Modelos del dominio

| Clase         | Responsabilidad                                            |
|---------------|------------------------------------------------------------|
| `Variante`    | Tamaño y precio de una presentación de un producto         |
| `Producto`    | Nombre, descripción, categoría y lista de variantes        |
| `ItemPedido`  | Producto + variante elegida + cantidad, con `subtotal()`   |

---

## Autores

Desarrollado como proyecto académico inspirado en el restaurante real **Brasa en Casa**.

| Nombre                       | Rol           |
|------------------------------|---------------|
| Luis Alejandro Londoño Valle | Desarrollador |
| Yeison Alejandro Zapata      | Desarrollador |

---

## Licencia

Proyecto académico. Inspirado en la carta real del restaurante **Brasa en Casa**, Medellín, Colombia. 🇨🇴
