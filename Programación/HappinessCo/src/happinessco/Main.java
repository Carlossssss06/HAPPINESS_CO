import java.util.*;

public class Main {
    static Scanner teclado = new Scanner(System.in);
    static int contEventos = 0;
    static int contGalerias = 0;
    public static void main(String[] args) {
        HashMap<String, Usuarios> usuarios = new HashMap<>();
        HashMap<Integer, Eventos> eventos = new HashMap<>();
        ArrayList<Favoritos> favoritos = new ArrayList<>();
        int opcion;

        do {
            imprimirCabecera("MENÚ PRINCIPAL HAPPINESS&Co");
            System.out.println(" [1] Añadir Usuario      [5] Añadir Galería");
            System.out.println(" [2] Eliminar Usuario    [6] Eliminar Galería");
            System.out.println(" [3] Añadir Evento       [7] Añadir Favorito");
            System.out.println(" [4] Eliminar Evento     [8] Eliminar Favorito");
            System.out.println(" [9] SALIR DEL SISTEMA");
            System.out.print("\n> Seleccione una opción: ");
            
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1: añadirUsuario(usuarios); break;
                case 2: eliminarUsuario(usuarios); break;
                case 3: añadirEvento(eventos); break;
                case 4: eliminarEvento(eventos); break;
                case 5: añadirGaleria(eventos); break;
                case 6: eliminarGaleria(eventos); break;
                case 7: /* añadirFavorito(eventos, usuarios, favoritos); */ break;
                case 8: /* eliminarFavorito(favoritos); */ break;
                case 9: 
                    System.out.println("\nCerrando sesión..."); 
                    break;
                default: 
                    System.out.println("\nERROR: Opción no reconocida.");
            }

            if(opcion != 9) pausar();
            
        } while (opcion != 9);
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

    public static void añadirUsuario(HashMap<String, Usuarios> lista) {
        imprimirCabecera("REGISTRO DE USUARIO");
        System.out.print("E-mail corporativo: "); String email = teclado.nextLine();
        
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
        System.out.print("E-mail del usuario a eliminar: ");
        String email = teclado.nextLine();
        if (lista.containsKey(email)) {
            System.out.print("¿Está seguro de eliminar el usuario '" + email + "'? (s/n): ");
            if (teclado.nextLine().equalsIgnoreCase("s")) {
                lista.remove(email);
                System.out.println("\nUsuario eliminado.");
            } else {
                System.out.println("\nOperación cancelada.");
            }
        } else {
            System.out.println("\nEl usuario no existe.");
        }
    }

    public static void añadirEvento(HashMap<Integer, Eventos> lista) {
        imprimirCabecera("CREAR NUEVO EVENTO");
        System.out.print("Nombre del evento: "); String tit = teclado.nextLine();
        System.out.print("Fecha prevista: "); String fec = teclado.nextLine();
        System.out.print("Lugar/Ubicación: "); String ubi = teclado.nextLine();
        System.out.print("Breve descripción: "); String des = teclado.nextLine();

        lista.put(contEventos, new Eventos(contEventos, fec, tit, ubi, des));
        System.out.println("\nÉXITO: Evento registrado con el ID #" + contEventos);
        contEventos++;
    }
    
    public static void eliminarEvento(HashMap<Integer, Eventos> lista) {
        imprimirCabecera("CANCELAR EVENTO");
        System.out.print("Introduzca el ID del evento a borrar: ");
        int id = teclado.nextInt(); teclado.nextLine();

        if (lista.containsKey(id)) {
            System.out.print("¿Está seguro de eliminar '" + lista.get(id).getTitulo() + "'? (s/n): ");
            if (teclado.nextLine().equalsIgnoreCase("s")) {
                lista.remove(id);
                System.out.println("\nEvento eliminado.");
            }
        } else {
            System.out.println("\nEl evento no existe.");
        }
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

        System.out.print("\nID del evento destino: ");
        int id = teclado.nextInt(); teclado.nextLine();

        if (!listaEv.containsKey(id)) {
            System.out.println("\nERROR: El ID #" + id + " no existe.");
        } else {
            System.out.print("Nombre de la galería: "); String t = teclado.nextLine();
            System.out.print("Link de la imagen: "); String u = teclado.nextLine();
            listaEv.get(id).getListaGalerias().add(new Galerias(contGalerias++, t, u));
            System.out.println("\nÉXITO: Galería vinculada al evento.");
        }
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

        System.out.print("\nID del evento: ");
        int idEvento = teclado.nextInt(); teclado.nextLine();

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
        int idGaleria = teclado.nextInt(); teclado.nextLine();

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
    }
}