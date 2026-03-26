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
                case 1: /* añadirUsuario(usuarios); */ break;
                case 2: /* eliminarUsuario(usuarios); */ break;
                case 3: /* añadirEvento(eventos); */ break;
                case 4: /* eliminarEvento(eventos); */ break;
                case 5: /* añadirGaleria(eventos); */ break;
                case 6: /* eliminarGaleria(eventos); */ break;
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
    }