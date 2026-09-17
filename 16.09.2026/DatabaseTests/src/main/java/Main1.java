import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main1 {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "qwerty";

    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "select * from player where id = 60;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");

                //String nameCorrected = new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

                int salary = resultSet.getInt("salary");

                System.out.println("Found player with name " + name + " and salary " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
