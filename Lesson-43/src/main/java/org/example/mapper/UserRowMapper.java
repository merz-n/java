package org.example.mapper;

import org.example.model.Authority;
import org.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<User> {
    private final JdbcTemplate template;


    public UserRowMapper(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("u_id"));
        user.setName(rs.getString("u_name"));
        user.setEmail(rs.getString("u_email"));
        user.setPassword(rs.getString("u_password"));
        user.setEnabled(rs.getBoolean("u_enabled"));
        user.setAddresses(new ArrayList<>());

        List<Authority> authorities = template.query(
                "SELECT a.id, a.authority FROM authorities a WHERE a.user_id = ?",
                (rsAuth, rowNumAuth) -> {
                    Authority authority = new Authority();
                    authority.setId(rsAuth.getInt("id"));
                    authority.setAuthority(rsAuth.getString("authority"));
                    return authority;
                },
                user.getId()
        );

        user.setAuthorities(authorities);

        return user;
    }
}