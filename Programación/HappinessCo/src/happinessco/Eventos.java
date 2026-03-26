import java.util.ArrayList;
public class Eventos {
    private int id;
    private String fecha, titulo, ubicacion, descripcion;
    private ArrayList<Galerias> listaGalerias;

    public Eventos(int id, String fecha, String titulo, String ubicacion, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.listaGalerias = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public ArrayList<Galerias> getListaGalerias() { return listaGalerias; }
    @Override
    public String toString() {
        return "ID: " + id + " | " + titulo + " (" + fecha + ") en " + ubicacion;
    }

}