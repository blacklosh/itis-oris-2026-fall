import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayerMapper {

    @SneakyThrows
    public List<PlayerEntity> toEntity(ResultSet resultSet) {
        List<PlayerEntity> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(PlayerEntity.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .position(resultSet.getString("position"))
                    .height(resultSet.getInt("height"))
                    .weight(resultSet.getInt("weight"))
                    .salary(resultSet.getInt("salary"))
                    .build());
        }

        return result;
    }

}
