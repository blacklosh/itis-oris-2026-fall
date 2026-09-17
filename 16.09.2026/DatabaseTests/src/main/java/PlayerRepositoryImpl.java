import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PlayerRepositoryImpl implements CrudRepository<Long, PlayerEntity> {

    private final PlayerMapper playerMapper;
    private final Connection connection;

    private static final String SQL_UPDATE = "update player set name = ?, " +
            "position = ?, height = ?, weight = ?, salary = ? where id = ?;";

    @Override
    public PlayerEntity save(PlayerEntity entity) {
        return null;
    }

    @Override
    public List<PlayerEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<PlayerEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    @SneakyThrows
    public boolean update(PlayerEntity playerEntity) {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setString(1, playerEntity.getName());
        ps.setString(2, playerEntity.getPosition());
        ps.setInt(3, playerEntity.getHeight());
        ps.setInt(4, playerEntity.getWeight());
        ps.setInt(5, playerEntity.getSalary());
        ps.setLong(6, playerEntity.getId());

        return ps.executeUpdate() > 0;
    }
}
