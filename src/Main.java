import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        MyPersoane mpe=new MyPersoane();
        mpe.setVisible(true);
        mpe.setLocationRelativeTo(null);
        try (Connection connection = ConexiuneBazadeDate.obtineConexiune()) {
            System.out.println("Conexiune reușită la baza de date.");
        } catch (Exception e) {
            System.err.println("Eroare la conectarea la baza de date: " + e.getMessage());
        }
        schimbare();
    }
    public static void schimbare(){
        System.out.println("O mica schimbare ");
    }
}