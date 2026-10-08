package ru.itis.servlet.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.servlet.model.UserEntity;
import ru.itis.servlet.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryJdbcImpl implements UserRepository {

    private static final String SELECT_BY_USERNAME = """
            select id, username, password
            from user_entity
            where username = ?;
            """;

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<UserEntity> USER_ROW_MAPPER = (rs, rowNum) ->
            UserEntity.builder()
                    .id(rs.getLong("id"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .build();

    @Override
    @SneakyThrows
    public Optional<UserEntity> findByUsername(String username) {
        List<UserEntity> userEntityList = jdbcTemplate.query(
                SELECT_BY_USERNAME,
                USER_ROW_MAPPER,
                username
        );
        if(userEntityList.isEmpty()) {
            return Optional.empty();
        }
        if(userEntityList.size() > 1) {
            throw new IllegalStateException("More than 1 user with username = " + username);
        }
        return Optional.of(userEntityList.getFirst());
    }
}
