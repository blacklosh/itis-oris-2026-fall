import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main3 {

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

        Scanner sc = new Scanner(System.in);
        String searchName = sc.nextLine();

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Connection connection2 = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "select * from player where name = ?;");
            statement.setString(1, searchName);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(PLAYER_MAPPER.toEntity(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
