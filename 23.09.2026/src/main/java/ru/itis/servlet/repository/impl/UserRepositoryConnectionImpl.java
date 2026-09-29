package ru.itis.servlet.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itis.servlet.model.UserEntity;
import ru.itis.servlet.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.IllegalFormatException;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryConnectionImpl implements UserRepository {

    private final Connection connection;

    private final String SELECT_BY_USERNAME =
            "select * from user_entity where username = ?;";

    @Override
    @SneakyThrows
    public Optional<UserEntity> findByUsername(String username) {
        PreparedStatement stmt = connection.prepareStatement(SELECT_BY_USERNAME);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return Optional.empty();
        }
        Optional<UserEntity> result = Optional.of(UserEntity.builder()
                        .id(rs.getLong("id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                .build());
        if (!rs.next()) {
            return result;
        }
        throw new  IllegalStateException("More that 1 user with username = " + username);
    }
}
