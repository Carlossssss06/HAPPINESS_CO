public class Favoritos {
    private int idEvento;
    private String emailUsuario;

    public Favoritos(int idEvento, String emailUsuario) {
        this.idEvento = idEvento;
        this.emailUsuario = emailUsuario;
    }

    public int getIdEvento() { return idEvento; }
    public String getEmailUsuario() { return emailUsuario; }
}
