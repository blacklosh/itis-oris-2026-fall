import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main4 {

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

            PlayerEntity entity = PlayerEntity.builder()
                    .id(60L)
                    .name("ahahhahahah")
                    .position("ggggggg")
                    .salary(100500)
                    .weight(500)
                    .height(800)
                    .build();

            PlayerRepositoryImpl repository = new PlayerRepositoryImpl(new PlayerMapper(), connection);
            repository.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
