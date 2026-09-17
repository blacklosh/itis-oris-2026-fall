import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main2 {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "qwerty";
    private static final PlayerMapper PLAYER_MAPPER = new PlayerMapper();

    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "select * from player;";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(PLAYER_MAPPER.toEntity(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
