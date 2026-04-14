import java.util.*;

public class Main {
    static Scanner teclado = new Scanner(System.in);
    static int contEventos = 0;
    static int contGalerias = 0;

    public static void main(String[] args) {
        HashMap<String, Usuarios> usuarios = new HashMap<>();
        HashMap<Integer, Eventos> eventos = new HashMap<>();
        ArrayList<Favoritos> favoritos = new ArrayList<>();
        String opcionStr;

        do {
            imprimirCabecera("MENÚ PRINCIPAL HAPPINESS&Co");
            System.out.println(" [1] Añadir Usuario      [5] Añadir Galería");
            System.out.println(" [2] Eliminar Usuario    [6] Eliminar Galería");
            System.out.println(" [3] Añadir Evento       [7] Añadir Favorito");
            System.out.println(" [4] Eliminar Evento     [8] Eliminar Favorito");
            System.out.println(" [9] SALIR DEL SISTEMA");
            System.out.print("\n> Seleccione una opción: ");
            
            opcionStr = teclado.nextLine();

            switch (opcionStr) {
                case "1": añadirUsuario(usuarios); break;
                case "2": eliminarUsuario(usuarios); break;
                case "3": añadirEvento(eventos); break;
                case "4": eliminarEvento(eventos); break;
                case "5": añadirGaleria(eventos); break;
                case "6": eliminarGaleria(eventos); break;
                case "7": añadirFavorito(eventos, usuarios, favoritos); break;
                case "8": eliminarFavorito(favoritos); break;
                case "9": 
                    System.out.println("\nCerrando sesión..."); 
                    break;
                case "+": break; // Ya estamos en el menú
                default: 
                    if(!opcionStr.equals("9")) System.out.println("\nERROR: Opción no reconocida.");
            }

            if(!opcionStr.equals("9") && !opcionStr.equals("+")) pausar();
            
        } while (!opcionStr.equals("9"));
    }

    public static void imprimirCabecera(String titulo) {
        System.out.println("\n==================================================");
        System.out.println("        " + titulo);
        System.out.println("==================================================");
    }

    public static void pausar() {
        System.out.println("\nPresione ENTER para continuar...");
        teclado.nextLine();
    }

    public static void imprimirNavegacion() {
        System.out.println("\n[ < ] Volver atrás  |  [ + ] Menú Principal");
        System.out.println("--------------------------------------------------");
    }

    public static void añadirUsuario(HashMap<String, Usuarios> lista) {
        imprimirCabecera("REGISTRO DE USUARIO");
        imprimirNavegacion();
        System.out.print("E-mail corporativo: "); String email = teclado.nextLine();
        
        if (email.equals("<") || email.equals("+")) return;

        if (lista.containsKey(email)) {
            System.out.println("\nAVISO: El correo ya está registrado en el sistema.");
        } else {
            System.out.print("Nombre completo: "); String nom = teclado.nextLine();
            System.out.print("Contraseña: "); String pass = teclado.nextLine();
            lista.put(email, new Usuarios(nom, email, pass));
            System.out.println("\nÉXITO: Usuario " + nom + " creado correctamente.");
        }
    }

    public static void eliminarUsuario(HashMap<String, Usuarios> lista) {
        imprimirCabecera("DAR DE BAJA USUARIO");
        
        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
            return;
        }
        System.out.println("USUARIOS REGISTRADOS:");
        System.out.println("---------------------");
        for (Usuarios u : lista.values()) {
            System.out.println("- " + u.getNombre() + " (" + u.getEmail() + ")");
        }
        imprimirNavegacion();

        System.out.print("\nE-mail del usuario a eliminar: ");
        String email = teclado.nextLine();
        
        if (email.equals("<") || email.equals("+")) return;

        if (lista.containsKey(email)) {
            System.out.print("¿Está seguro de eliminar el usuario '" + email + "'? (s/n): ");
            if (teclado.nextLine().equalsIgnoreCase("s")) {
                lista.remove(email);
                System.out.println("\n✔ ÉXITO: Usuario eliminado.");
            } else {
                System.out.println("\nOperación cancelada.");
            }
        } else {
            System.out.println("\n[!] ERROR: El usuario no existe.");
        }
    }

    public static void añadirEvento(HashMap<Integer, Eventos> lista) {
        imprimirCabecera("CREAR NUEVO EVENTO");
        imprimirNavegacion();
        System.out.print("Nombre del evento: "); String tit = teclado.nextLine();
        
        if (tit.equals("<") || tit.equals("+")) return;

        System.out.print("Fecha prevista: "); String fec = teclado.nextLine();
        System.out.print("Lugar/Ubicación: "); String ubi = teclado.nextLine();
        System.out.print("Breve descripción: "); String des = teclado.nextLine();

        lista.put(contEventos, new Eventos(contEventos, fec, tit, ubi, des));
        System.out.println("\nÉXITO: Evento registrado con el ID #" + contEventos);
        contEventos++;
    }
    
    public static void eliminarEvento(HashMap<Integer, Eventos> lista) {
        imprimirCabecera("CANCELAR EVENTO");

        if (lista.isEmpty()) {
            System.out.println("No hay eventos programados.");
            return;
        }
        System.out.println("ID\tTÍTULO DEL EVENTO");
        System.out.println("--\t-----------------");
        for (Eventos e : lista.values()) {
            System.out.println(e.getId() + "\t" + e.getTitulo());
        }
        imprimirNavegacion();

        System.out.print("\nIntroduzca el ID del evento que quieres borrar: ");
        String entrada = teclado.nextLine();
        
        if (entrada.equals("<") || entrada.equals("+")) return;

        try {
            int id = Integer.parseInt(entrada);
            if (lista.containsKey(id)) {
                System.out.print("¿Está seguro de eliminar '" + lista.get(id).getTitulo() + "'? (s/n): ");
                if (teclado.nextLine().equalsIgnoreCase("s")) {
                    lista.remove(id);
                    System.out.println("\nÉXITO: Evento eliminado.");
                } else {
                    System.out.println("\nOperación cancelada.");
                }
            } else {
                System.out.println("\nERROR: El evento con ID #" + id + " no existe.");
            }
        } catch (Exception e) { System.out.println("Entrada inválida."); }
    }    

    public static void añadirGaleria(HashMap<Integer, Eventos> listaEv) {
        imprimirCabecera("GESTIÓN DE GALERÍAS");
        if (listaEv.isEmpty()) {
            System.out.println("No hay eventos registrados para añadir fotos.");
            return;
        }

        System.out.println("ID\tTÍTULO DEL EVENTO");
        System.out.println("--\t-----------------");
        for (Eventos e : listaEv.values()) {
            System.out.println(e.getId() + "\t" + e.getTitulo());
        }
        imprimirNavegacion();

        System.out.print("\nID del evento destino: ");
        String entrada = teclado.nextLine();
        
        if (entrada.equals("<") || entrada.equals("+")) return;

        try {
            int id = Integer.parseInt(entrada);
            if (!listaEv.containsKey(id)) {
                System.out.println("\nERROR: El ID #" + id + " no existe.");
            } else {
                System.out.print("Nombre de la galería: "); String t = teclado.nextLine();
                System.out.print("Link de la imagen: "); String u = teclado.nextLine();
                listaEv.get(id).getListaGalerias().add(new Galerias(contGalerias++, t, u));
                System.out.println("\nÉXITO: Galería vinculada al evento.");
            }
        } catch (Exception e) { System.out.println("Entrada inválida."); }
    }

    public static void eliminarGaleria(HashMap<Integer, Eventos> listaEv) {
        imprimirCabecera("ELIMINAR GALERÍA");
        if (listaEv.isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return;
        }

        System.out.println("ID\tTÍTULO DEL EVENTO");
        System.out.println("--\t-----------------");
        for (Eventos e : listaEv.values()) {
            System.out.println(e.getId() + "\t" + e.getTitulo());
        }
        imprimirNavegacion();

        System.out.print("\nID del evento: ");
        String entrada = teclado.nextLine();
        if (entrada.equals("<") || entrada.equals("+")) return;

        try {
            int idEvento = Integer.parseInt(entrada);
            if (!listaEv.containsKey(idEvento)) {
                System.out.println("\nERROR: El ID #" + idEvento + " no existe.");
                return;
            }

            Eventos evento = listaEv.get(idEvento);
            ArrayList<Galerias> galerias = evento.getListaGalerias();
            if (galerias.isEmpty()) {
                System.out.println("No hay galerías asociadas a este evento.");
                return;
            }

            System.out.println("ID GALERÍA\tNOMBRE");
            for (Galerias g : galerias) {
                System.out.println(g.getId() + "\t\t" + g.getTitulo());
            }
            System.out.print("\nID de la galería a eliminar: ");
            int idGaleria = Integer.parseInt(teclado.nextLine());

            Galerias galeriaAEliminar = null;
            for (Galerias g : galerias) {
                if (g.getId() == idGaleria) {
                    galeriaAEliminar = g;
                    break;
                }
            }

            if (galeriaAEliminar != null) {
                System.out.print("¿Está seguro de eliminar la galería '" + galeriaAEliminar.getTitulo() + "'? (s/n): ");
                if (teclado.nextLine().equalsIgnoreCase("s")) {
                    galerias.remove(galeriaAEliminar);
                    System.out.println("\nGalería eliminada.");
                } else {
                    System.out.println("\nOperación cancelada.");
                }
            } else {
                System.out.println("\nLa galería no existe.");
            }
        } catch (Exception e) { System.out.println("Entrada inválida."); }
    }

    public static void añadirFavorito(HashMap<Integer, Eventos> evs, HashMap<String, Usuarios> usrs, ArrayList<Favoritos> favs) {
        imprimirCabecera("MARCAR COMO FAVORITO");
        if (evs.isEmpty() || usrs.isEmpty()) {
            System.out.println("Se necesitan al menos un evento y un usuario en el sistema.");
            return;
        }

        System.out.println("--- LISTA DE CORREOS ---");
        for (Usuarios u : usrs.values()) System.out.println(" - " + u.getEmail());
        imprimirNavegacion();
        
        System.out.print("\nE-mail del usuario: "); String em = teclado.nextLine();
        if (em.equals("<") || em.equals("+")) return;

        System.out.print("ID del evento: "); 
        try {
            int id = Integer.parseInt(teclado.nextLine());
            if (evs.containsKey(id) && usrs.containsKey(em)) {
                favs.add(new Favoritos(id, em));
                System.out.println("\nÉXITO: Se ha añadido a la lista de favoritos.");
            } else {
                System.out.println("\nERROR: Datos de usuario o evento incorrectos.");
            }
        } catch (Exception e) { System.out.println("ID inválido."); }
    }

    public static void eliminarFavorito(ArrayList<Favoritos> favoritos) {
        imprimirCabecera("QUITAR DE FAVORITOS");
        if (favoritos.isEmpty()) {
            System.out.println("No hay favoritos registrados.");
            return;
        }

        System.out.println("LISTA DE FAVORITOS:");
        for (int i = 0; i < favoritos.size(); i++) {
            Favoritos fav = favoritos.get(i);
            System.out.println("[" + i + "] Usuario: " + fav.getEmailUsuario() + " | Evento ID: " + fav.getIdEvento());
        }
        imprimirNavegacion();

        System.out.print("\nIngrese el ID del favorito que quieres eliminar: ");
        String entrada = teclado.nextLine();
        if (entrada.equals("<") || entrada.equals("+")) return;

        try {
            int indice = Integer.parseInt(entrada);
            if (indice < 0 || indice >= favoritos.size()) {
                System.out.println("Índice fuera de rango.");
            } else {
                System.out.print("¿Está seguro de eliminar este favorito? (s/n): ");
                if (teclado.nextLine().equalsIgnoreCase("s")) {
                    favoritos.remove(indice);
                    System.out.println("\nFavorito eliminado.");
                } else {
                    System.out.println("\nOperación cancelada.");
                }
            }
        } catch (Exception e) { System.out.println("Entrada inválida."); }
    }
}