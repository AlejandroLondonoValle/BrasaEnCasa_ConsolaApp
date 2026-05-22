import java.util.*;
import Models.*;

/**
 * ============================================================
 *   BRASA EN CASA - Sistema de Pedidos
 *   Restaurante | Medellín, Colombia
 * ============================================================
 */

public class App {

    // =========================================================
    //  MODELOS
    // =========================================================

    // static class Variante {
    //     String tamano;
    //     int precio;

    //     Variante(String tamano, int precio) {
    //         this.tamano = tamano;
    //         this.precio = precio;
    //     }
    // }

    // static class Producto {
    //     String nombre;
    //     String descripcion;
    //     String categoria;
    //     List<Variante> variantes;

    //     Producto(String nombre, String descripcion, String categoria, Variante... variantes) {
    //         this.nombre      = nombre;
    //         this.descripcion = descripcion;
    //         this.categoria   = categoria;
    //         this.variantes   = new ArrayList<>(Arrays.asList(variantes));
    //     }

    //     /** Precio mínimo (para mostrar "desde $X" cuando hay varias variantes) */
    //     int precioMinimo() {
    //         return variantes.stream().mapToInt(v -> v.getPrecio()).min().orElse(0);
    //     }
    // }

    // static class ItemPedido {
    //     Producto producto;
    //     Variante variante;
    //     int cantidad;

    //     ItemPedido(Producto producto, Variante variante, int cantidad) {
    //         this.producto  = producto;
    //         this.variante  = variante;
    //         this.cantidad  = cantidad;
    //     }

    //     int subtotal() {
    //         return variante.getPrecio() * cantidad;
    //     }
    // }

    // =========================================================
    //  ESTADO GLOBAL
    // =========================================================

    static final String CLAVE_ADMIN = "brasa2026";

    // Carta completa
    static final List<Producto> CARTA = new ArrayList<>();

    // Agrupación de categorías de la carta en secciones del menú
    static final Map<String, List<String>> SECCIONES = new LinkedHashMap<>();

    // Pedido activo
    static List<ItemPedido> pedidoActual = new ArrayList<>();

    // Caja
    static int saldoCaja      = 0;
    static int ventasTotales  = 0;

    static Scanner sc = new Scanner(System.in);

    // =========================================================
    //  INICIALIZACIÓN DE DATOS
    // =========================================================

    static void inicializarCarta() {

        // --- ENTRADAS ---
        CARTA.add(new Producto("Ceviche de Chicharrón", "",
                "Entradas",
                new Variante("Única", 29500)));

        CARTA.add(new Producto("Patacón con Guacamole", "",
                "Entradas",
                new Variante("x5", 21000),
                new Variante("x10", 31000)));

        CARTA.add(new Producto("Queso Asado con Mermelada de la Casa", "",
                "Entradas",
                new Variante("100 gr", 21000),
                new Variante("180 gr", 35000)));

        CARTA.add(new Producto("Choripán Argentino", "",
                "Entradas",
                new Variante("Única", 27000)));

        CARTA.add(new Producto("Mini Hamburguesa", "",
                "Entradas",
                new Variante("x1", 15000),
                new Variante("x3", 38000)));

        CARTA.add(new Producto("Portobello Gratinado", "",
                "Entradas",
                new Variante("Única", 35000)));

        // --- ENSALADAS ---
        CARTA.add(new Producto("Ensalada Fuego Verde",
                "Mix de hojas verdes, aguacate, tomate cherry, zanahoria a la brasa, mazorca dulce asada, almendras y vinagreta al carbón.",
                "Ensaladas",
                new Variante("1/2 Porción", 12000),
                new Variante("1 Porción",   20000)));

        CARTA.add(new Producto("Ensalada César a la Brasa",
                "Cogollos europeos asados, pan focaccia, queso parmesano y aderezo César hecho en casa.",
                "Ensaladas",
                new Variante("1/2 Porción", 18000),
                new Variante("1 Porción",   36000)));

        CARTA.add(new Producto("Ensalada Tropical",
                "Mix de hojas verdes, piña asada, zanahoria laminada, zucchini asado, cebolla encurtida, tomate cherry y vinagreta al carbón.",
                "Ensaladas",
                new Variante("1/2 Porción", 12000),
                new Variante("1 Porción",   20000)));

        // --- CORTES GRUESOS ---
        CARTA.add(new Producto("Costilla",
                "Corte grueso a la brasa. (Gratina tu corte por $6.000 adicionales).",
                "Cortes Gruesos",
                new Variante("500 gr",  61000),
                new Variante("1000 gr", 122000)));

        CARTA.add(new Producto("Filete Mignon",
                "Corte grueso Certified Angus Beef. (Gratina tu corte por $6.000 adicionales).",
                "Cortes Gruesos",
                new Variante("Única", 67000)));

        CARTA.add(new Producto("Punta de Anca",
                "Corte a la brasa. Escoge 3 acompañantes.",
                "Cortes Gruesos",
                new Variante("250 gr", 55000)));

        CARTA.add(new Producto("Chata",
                "Corte a la brasa. Escoge 3 acompañantes.",
                "Cortes Gruesos",
                new Variante("250 gr", 55000)));

        CARTA.add(new Producto("Solomito",
                "Corte a la brasa. Escoge 3 acompañantes.",
                "Cortes Gruesos",
                new Variante("250 gr", 58000)));

        // --- MIXTOS 3 CARNES ---
        String descMixto = "Elija 3 cortes: chicharrón, contramuslo, chata, costilla o solomito (250 gr c/u). Con 5 acompañantes y salsa especial.";
        CARTA.add(new Producto("Mixto 3 Carnes - Memphis",          descMixto, "Mixtos 3 Carnes", new Variante("Única", 170000)));
        CARTA.add(new Producto("Mixto 3 Carnes - Carolina Gold",    descMixto, "Mixtos 3 Carnes", new Variante("Única", 170000)));
        CARTA.add(new Producto("Mixto 3 Carnes - Mantequilla Brasa",descMixto, "Mixtos 3 Carnes", new Variante("Única", 170000)));
        CARTA.add(new Producto("Mixto 3 Carnes - 5 Pimientas",      descMixto, "Mixtos 3 Carnes", new Variante("Única", 170000)));
        CARTA.add(new Producto("Mixto 3 Carnes - Vino Tinto",       descMixto, "Mixtos 3 Carnes", new Variante("Única", 170000)));

        // --- PICADAS ---
        CARTA.add(new Producto("Picada Ligera",
                "Contramuslo en finas hierbas, piña, queso asado y champiñones portobello con guacamole y arepas.",
                "Picadas",
                new Variante("Individual", 45000),
                new Variante("Doble",      90000),
                new Variante("Familiar",  180000)));

        CARTA.add(new Producto("Picada de Carne",
                "300 gr de asado de res, arepas doradas, papas cocidas y patacones con guacamole.",
                "Picadas",
                new Variante("Individual",  55000),
                new Variante("Doble",      110000),
                new Variante("Familiar",   220000)));

        CARTA.add(new Producto("Pollada",
                "300 gr de filete contramuslo a las finas hierbas, arepas, papas cocidas y patacones con guacamole.",
                "Picadas",
                new Variante("Individual",  36000),
                new Variante("Doble",       72000),
                new Variante("Familiar",   144000)));

        CARTA.add(new Producto("Picada Clásica",
                "Punta de anca, chicharrón, contramuslo, chorizo, morcilla, patacones con guacamole, papa cocida y arepas.",
                "Picadas de la Casa",
                new Variante("Individual",  48000),
                new Variante("Doble",       96000),
                new Variante("Familiar",   195000)));

        CARTA.add(new Producto("Picada Argentina",
                "200 gr de punta de anca, choripán argentino, queso asado, guacamole y todas nuestras salsas de la casa.",
                "Picadas de la Casa",
                new Variante("Individual",  54000),
                new Variante("Doble",      107000),
                new Variante("Familiar",   230000)));

        CARTA.add(new Producto("Chicharronada",
                "300 gr de chicharrón especial asado con arepas, papas cocidas y patacones con guacamole.",
                "Picadas de la Casa",
                new Variante("Individual",  39000),
                new Variante("Doble",       78000),
                new Variante("Familiar",   156000)));

        // --- SÁNDUCHES ---
        CARTA.add(new Producto("Sánduche Especial de Chicharrón",
                "200 gr de chicharrón sobre pan focaccia, sour cream parrillera, cebolla caramelizada en vino tinto. Con papas a la francesa.",
                "Sánduches",
                new Variante("Única", 39000)));

        CARTA.add(new Producto("Sánduche Tropical",
                "Pollo asado, cubos de queso asado, piña y salsa de la casa. Con papas a la francesa.",
                "Sánduches",
                new Variante("Única", 42000)));

        CARTA.add(new Producto("Sánduche de Portobellos",
                "200 gr de portobellos sobre pan focaccia, sour cream parrillera y cebolla caramelizada. Con papas a la francesa.",
                "Sánduches",
                new Variante("Única", 41000)));

        // --- BEBIDAS ---
        CARTA.add(new Producto("Limonada de Coco",         "", "Bebidas - Limonadas", new Variante("Única", 12000)));
        CARTA.add(new Producto("Limonadas Saborizadas",    "", "Bebidas - Limonadas", new Variante("Única", 11000)));
        CARTA.add(new Producto("Cóctel Daiquiri de Maracuyá", "", "Bebidas - Cocteles", new Variante("Única", 34000)));
        CARTA.add(new Producto("Cóctel Margarita de Fresa",  "", "Bebidas - Cocteles", new Variante("Única", 34000)));
        CARTA.add(new Producto("Jugos",              "", "Bebidas - Otros", new Variante("Única", 10000)));
        CARTA.add(new Producto("Sodas Saborizadas",  "", "Bebidas - Otros", new Variante("Única", 10000)));

        // --- POSTRES ---
        CARTA.add(new Producto("Postre de Brownie con Helado",
                "Brownie a las brasas con chocolate Hershey caliente, helado de vainilla y topping de barquillos.",
                "Postres de la Brasa",
                new Variante("Única", 27000)));

        CARTA.add(new Producto("Alfajor con Arequipe",
                "Alfajor bañado en chocolate blanco con arequipe caliente, helado de vainilla y topping de barquillos.",
                "Postres de la Brasa",
                new Variante("Única", 27000)));
    }

    static void inicializarSecciones() {
        SECCIONES.put("Entradas",       Arrays.asList("Entradas"));
        SECCIONES.put("Platos Fuertes", Arrays.asList("Ensaladas", "Cortes Gruesos", "Mixtos 3 Carnes"));
        SECCIONES.put("Picadas",        Arrays.asList("Picadas", "Picadas de la Casa"));
        SECCIONES.put("Sánduches",      Arrays.asList("Sánduches"));
        SECCIONES.put("Bebidas",        Arrays.asList("Bebidas - Limonadas", "Bebidas - Cocteles", "Bebidas - Otros"));
        SECCIONES.put("Postres",        Arrays.asList("Postres de la Brasa"));
    }

    // =========================================================
    //  UTILIDADES DE FORMATO
    // =========================================================

    static final int ANCHO = 62;

    static void linea()          { System.out.println("=".repeat(ANCHO)); }
    static void lineaDelgada()   { System.out.println("-".repeat(ANCHO)); }

    static void titulo(String texto) {
        linea();
        int padding = (ANCHO - texto.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + texto);
        linea();
    }

    static void subtitulo(String texto) {
        lineaDelgada();
        System.out.println("  " + texto.toUpperCase());
        lineaDelgada();
    }

    static String formatoPrecio(int precio) {
        // Formatea 170000 → $170.000
        return String.format("$%,d", precio).replace(",", ".");
    }

    // =========================================================
    //  MAIN
    // =========================================================

    public static void main(String[] args) {
        inicializarCarta();
        inicializarSecciones();

        boolean activo = true;
        while (activo) {
            titulo("  BRASA EN CASA  ");
            System.out.println("  Bienvenido a nuestra parrilla");
            System.out.println();

            if (!pedidoActual.isEmpty()) {
                System.out.printf("  Pedido activo: %d ítem(s)  |  Total: %s%n",
                        pedidoActual.size(), formatoPrecio(totalPedido()));
                lineaDelgada();
            }

            System.out.println("  1. Ver carta y agregar al pedido");
            System.out.println("  2. Ver pedido actual");
            System.out.println("  3. Cerrar pedido y pagar");
            System.out.println("  4. Cancelar pedido");
            System.out.println("  5. Modo Administrador");
            System.out.println("  0. Salir");
            linea();
            System.out.print("  Seleccione una opción: ");

            switch (leerLinea()) {
                case "1": menuCarta();       break;
                case "2": verPedido(true);   break;
                case "3": cerrarPedido();    break;
                case "4": cancelarPedido();  break;
                case "5": autenticarAdmin(); break;
                case "0":
                    titulo("  ¡Hasta pronto!  ");
                    System.out.println("  Gracias por visitar Brasa en Casa.");
                    linea();
                    activo = false;
                    break;
                default:
                    System.out.println("\n  Opción no válida. Intente de nuevo.\n");
            }
        }
    }

    // =========================================================
    //  FLUJO CLIENTE — CARTA
    // =========================================================

    static void menuCarta() {
        titulo("  NUESTRA CARTA  ");

        List<String> seccionesKeys = new ArrayList<>(SECCIONES.keySet());
        for (int i = 0; i < seccionesKeys.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, seccionesKeys.get(i));
        }
        System.out.println("  0. Volver");
        linea();
        System.out.print("  Seleccione una sección: ");

        String input = leerLinea();
        if (input.equals("0")) return;

        try {
            int idx = Integer.parseInt(input) - 1;
            if (idx >= 0 && idx < seccionesKeys.size()) {
                mostrarSeccion(seccionesKeys.get(idx));
            } else {
                System.out.println("\n  Opción fuera de rango.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n  Entrada inválida.\n");
        }
    }

    static void mostrarSeccion(String seccion) {
        // Reúne todos los productos de las categorías que pertenecen a esta sección
        List<String> categoriasSeccion = SECCIONES.get(seccion);
        List<Producto> productos = new ArrayList<>();

        for (String cat : categoriasSeccion) {
            for (Producto p : CARTA) {
                if (p.getCategoria().equals(cat)) productos.add(p);
            }
        }

        boolean seguirEnSeccion = true;
        while (seguirEnSeccion) {
            titulo("  " + seccion.toUpperCase() + "  ");

            // Mostrar productos agrupados por categoría interna
            int numero = 1;
            List<Integer> indicesGlobales = new ArrayList<>(); // mapeo numero → índice en 'productos'

            String catActual = "";
            for (Producto p : productos) {
                if (!p.getCategoria().equals(catActual)) {
                    catActual = p.getCategoria();
                    if (categoriasSeccion.size() > 1) subtitulo(catActual);
                }

                // Precio: si tiene 1 variante muestra el precio, si tiene varias muestra "desde $X"
                String precio = p.getVariantes().size() == 1
                        ? formatoPrecio(p.getVariantes().get(0).getPrecio())
                        : "desde " + formatoPrecio(p.precioMinimo());

                System.out.printf("  %-2d. %-38s %s%n", numero, p.getNombre(), precio);

                if (!p.getDescripcion().isEmpty()) {
                    // Descripción en líneas de máx. 55 chars
                    String desc = p.getDescripcion();
                    while (desc.length() > 55) {
                        System.out.println("       " + desc.substring(0, 55));
                        desc = desc.substring(55);
                    }
                    System.out.println("       " + desc);
                }

                if (p.getVariantes().size() > 1) {
                    for (Variante v : p.getVariantes()) {
                        System.out.printf("         · %-15s %s%n", v.getTamano(), formatoPrecio(v.getPrecio()));
                    }
                }

                System.out.println();
                indicesGlobales.add(productos.indexOf(p));
                numero++;
            }

            lineaDelgada();
            System.out.println("  Ingrese el número del producto para agregar al pedido");
            System.out.println("  0. Volver al menú principal");
            linea();
            System.out.print("  Opción: ");

            String input = leerLinea();
            if (input.equals("0")) {
                seguirEnSeccion = false;
            } else {
                try {
                    int sel = Integer.parseInt(input) - 1;
                    if (sel >= 0 && sel < productos.size()) {
                        agregarAlPedido(productos.get(sel));
                    } else {
                        System.out.println("\n  Número de producto inexistente.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\n  Entrada inválida.\n");
                }
            }
        }
    }

    static void agregarAlPedido(Producto p) {
        titulo("  AGREGAR AL PEDIDO  ");
        System.out.println("  Producto: " + p.getNombre());
        lineaDelgada();

        Variante varianteElegida;

        if (p.getVariantes().size() == 1) {
            varianteElegida = p.getVariantes().get(0);
            System.out.printf("  Tamaño : %s%n", varianteElegida.getTamano());
            System.out.printf("  Precio : %s%n", formatoPrecio(varianteElegida.getPrecio()));
        } else {
            System.out.println("  Seleccione el tamaño:");
            System.out.println();
            for (int i = 0; i < p.getVariantes().size(); i++) {
                Variante v = p.getVariantes().get(i);
                System.out.printf("  %d. %-20s %s%n", i + 1, v.getTamano(), formatoPrecio(v.getPrecio()));
            }
            System.out.println("  0. Cancelar");
            linea();
            System.out.print("  Opción: ");

            String input = leerLinea();
            if (input.equals("0")) return;

            try {
                int idxV = Integer.parseInt(input) - 1;
                if (idxV < 0 || idxV >= p.getVariantes().size()) {
                    System.out.println("\n  Opción inválida.\n");
                    return;
                }
                varianteElegida = p.getVariantes().get(idxV);
            } catch (NumberFormatException e) {
                System.out.println("\n  Entrada inválida.\n");
                return;
            }
        }

        lineaDelgada();
        System.out.print("  Cantidad: ");
        try {
            int cant = Integer.parseInt(leerLinea());
            if (cant <= 0) {
                System.out.println("\n  La cantidad debe ser mayor a 0.\n");
                return;
            }

            // Si ya existe ese producto+variante en el pedido, sumar cantidad
            boolean encontrado = false;
            for (ItemPedido item : pedidoActual) {
                if (item.getProducto() == p && item.getVariante() == varianteElegida) {
                    item.setCantidad(item.getCantidad() + cant);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                pedidoActual.add(new ItemPedido(p, varianteElegida, cant));
            }

            linea();
            System.out.printf("  ✔  %s (%s) x%d → %s%n",
                    p.getNombre(), varianteElegida.getTamano(), cant,
                    formatoPrecio(varianteElegida.getPrecio() * cant));
            System.out.println("     Agregado al pedido correctamente.");
            linea();
            System.out.println();

        } catch (NumberFormatException e) {
            System.out.println("\n  Cantidad inválida.\n");
        }
    }

    // =========================================================
    //  FLUJO CLIENTE — PEDIDO
    // =========================================================

    static int totalPedido() {
        return pedidoActual.stream().mapToInt(ItemPedido::subtotal).sum();
    }

    static void verPedido(boolean mostrarOpciones) {
        titulo("  PEDIDO ACTUAL  ");

        if (pedidoActual.isEmpty()) {
            System.out.println("  No hay ítems en el pedido.");
            linea();
            System.out.println();
            return;
        }

        System.out.printf("  %-3s %-36s %-10s %10s%n", "#", "Producto", "Tamaño", "Subtotal");
        lineaDelgada();

        for (int i = 0; i < pedidoActual.size(); i++) {
            ItemPedido item = pedidoActual.get(i);
            System.out.printf("  %-3d %-36s %-10s %10s%n",
                    i + 1,
                    item.getCantidad() > 1
                            ? item.getProducto().getNombre() + " x" + item.getCantidad()
                            : item.getProducto().getNombre(),
                    item.getVariante().getTamano(),
                    formatoPrecio(item.subtotal()));
        }

        lineaDelgada();
        System.out.printf("  %-51s %10s%n", "TOTAL", formatoPrecio(totalPedido()));
        linea();

        if (mostrarOpciones) {
            System.out.println("  1. Eliminar un ítem");
            System.out.println("  0. Volver");
            linea();
            System.out.print("  Opción: ");
            String op = leerLinea();
            if (op.equals("1")) eliminarItem();
        }

        System.out.println();
    }

    static void eliminarItem() {
        System.out.print("  Número del ítem a eliminar: ");
        try {
            int idx = Integer.parseInt(leerLinea()) - 1;
            if (idx >= 0 && idx < pedidoActual.size()) {
                ItemPedido eliminado = pedidoActual.remove(idx);
                System.out.printf("%n  ✘  '%s' eliminado del pedido.%n%n", eliminado.getProducto().getNombre());
            } else {
                System.out.println("\n  Número inválido.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n  Entrada inválida.\n");
        }
    }

    static void cerrarPedido() {
        if (pedidoActual.isEmpty()) {
            System.out.println("\n  No hay ítems en el pedido para cerrar.\n");
            return;
        }

        verPedido(false);

        System.out.println("  ¿Confirma el pedido?  (S / N): ");
        System.out.print("  → ");
        String resp = leerLinea().trim().toUpperCase();
        if (!resp.equals("S")) {
            System.out.println("\n  Pedido no confirmado. Puede seguir editándolo.\n");
            return;
        }

        int total = totalPedido();
        saldoCaja     += total;
        ventasTotales += total;

        titulo("  PEDIDO CONFIRMADO  ");
        System.out.println("  Su pedido ha sido enviado a cocina.");
        System.out.println();
        System.out.printf("  Total cobrado: %s%n", formatoPrecio(total));
        System.out.println("  ¡Buen provecho!");
        linea();
        System.out.println();

        pedidoActual.clear();
    }

    static void cancelarPedido() {
        if (pedidoActual.isEmpty()) {
            System.out.println("\n  No hay pedido activo que cancelar.\n");
            return;
        }
        System.out.print("\n  ¿Cancelar el pedido actual? Se perderán todos los ítems. (S / N): ");
        String resp = leerLinea().trim().toUpperCase();
        if (resp.equals("S")) {
            pedidoActual.clear();
            System.out.println("\n  Pedido cancelado.\n");
        }
    }

    // =========================================================
    //  PANEL ADMINISTRADOR
    // =========================================================

    static void autenticarAdmin() {
        System.out.print("\n  Clave de administrador: ");
        if (!leerLinea().equals(CLAVE_ADMIN)) {
            System.out.println("\n  Clave incorrecta.\n");
            return;
        }
        menuAdmin();
    }

    static void menuAdmin() {
        boolean volver = false;
        while (!volver) {
            titulo("  PANEL DE ADMINISTRACIÓN  ");
            System.out.println("  1. Reporte de ventas");
            System.out.println("  2. Ver stock por categoría");
            System.out.println("  3. Cambiar precio de un producto");
            System.out.println("  4. Retirar dinero de caja");
            System.out.println("  0. Salir del modo admin");
            linea();
            System.out.print("  Opción: ");

            switch (leerLinea()) {
                case "1": reporteVentas();          break;
                case "2": verStockAdmin();          break;
                case "3": cambiarPrecioAdmin();     break;
                case "4": retirarDineroAdmin();     break;
                case "0": volver = true;            break;
                default:  System.out.println("\n  Opción no válida.\n");
            }
        }
    }

    static void reporteVentas() {
        titulo("  REPORTE DE VENTAS  ");
        System.out.printf("  %-30s %s%n", "Ventas totales del día:", formatoPrecio(ventasTotales));
        System.out.printf("  %-30s %s%n", "Saldo en caja:",         formatoPrecio(saldoCaja));
        linea();
        System.out.println();
    }

    static void verStockAdmin() {
        titulo("  CARTA COMPLETA  ");
        String catActual = "";
        int num = 1;
        for (Producto p : CARTA) {
            if (!p.getCategoria().equals(catActual)) {
                catActual = p.getCategoria();
                subtitulo(catActual);
            }
            if (p.getVariantes().size() == 1) {
                System.out.printf("  %2d. %-38s %s%n",
                        num, p.getNombre(), formatoPrecio(p.getVariantes().get(0).getPrecio()));
            } else {
                System.out.printf("  %2d. %s%n", num, p.getNombre());
                for (Variante v : p.getVariantes()) {
                    System.out.printf("       · %-18s %s%n", v.getTamano(), formatoPrecio(v.getPrecio()));
                }
            }
            num++;
        }
        linea();
        System.out.println();
    }

    static void cambiarPrecioAdmin() {
        verStockAdmin();
        System.out.print("  Número del producto a modificar (0 para cancelar): ");
        try {
            int idx = Integer.parseInt(leerLinea()) - 1;
            if (idx == -1) return;
            if (idx < 0 || idx >= CARTA.size()) {
                System.out.println("\n  Número inválido.\n");
                return;
            }

            Producto p = CARTA.get(idx);
            System.out.println("\n  Producto: " + p.getNombre());

            Variante varianteEditar;
            if (p.getVariantes().size() == 1) {
                varianteEditar = p.getVariantes().get(0);
            } else {
                System.out.println("  Seleccione la variante:");
                for (int i = 0; i < p.getVariantes().size(); i++) {
                    Variante v = p.getVariantes().get(i);
                    System.out.printf("  %d. %-18s %s%n", i + 1, v.getTamano(), formatoPrecio(v.getPrecio()));
                }
                System.out.print("  Variante: ");
                int idxV = Integer.parseInt(leerLinea()) - 1;
                if (idxV < 0 || idxV >= p.getVariantes().size()) {
                    System.out.println("\n  Opción inválida.\n");
                    return;
                }
                varianteEditar = p.getVariantes().get(idxV);
            }

            System.out.printf("  Precio actual: %s%n", formatoPrecio(varianteEditar.getPrecio()));
            System.out.print("  Nuevo precio (COP): $");
            int nuevoPrecio = Integer.parseInt(leerLinea());
            if (nuevoPrecio <= 0) {
                System.out.println("\n  El precio debe ser mayor a 0.\n");
                return;
            }
            varianteEditar.setPrecio(nuevoPrecio);
            System.out.printf("%n  Precio actualizado a %s%n%n", formatoPrecio(nuevoPrecio));

        } catch (NumberFormatException e) {
            System.out.println("\n  Entrada inválida.\n");
        }
    }

    static void retirarDineroAdmin() {
        titulo("  RETIRO DE CAJA  ");
        System.out.printf("  Saldo disponible en caja: %s%n", formatoPrecio(saldoCaja));
        lineaDelgada();
        System.out.print("  Monto a retirar (0 para cancelar): $");
        try {
            int monto = Integer.parseInt(leerLinea());
            if (monto == 0) return;
            if (monto < 0) {
                System.out.println("\n  El monto debe ser positivo.\n");
                return;
            }
            if (monto > saldoCaja) {
                System.out.printf("%n  Error: el monto %s supera el saldo en caja (%s).%n%n",
                        formatoPrecio(monto), formatoPrecio(saldoCaja));
                return;
            }
            saldoCaja -= monto;
            System.out.printf("%n  Retiro exitoso de %s. Saldo restante: %s%n%n",
                    formatoPrecio(monto), formatoPrecio(saldoCaja));
        } catch (NumberFormatException e) {
            System.out.println("\n  Entrada inválida.\n");
        }
    }

    // =========================================================
    //  UTILIDAD
    // =========================================================

    static String leerLinea() {
        try {
            return sc.nextLine().trim();
        } catch (Exception e) {
            return "";
        }
    }
}