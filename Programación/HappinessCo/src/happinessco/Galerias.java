public class Galerias {
    private int id;
    private String titulo;
    private String url;

    public Galerias(int id, String titulo, String url) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    @Override
    public String toString() {
        return "ID: " + id + " | Título: " + titulo + " | URL: " + url;
    }
}