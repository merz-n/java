package org.example.library.dao;

import org.example.library.mapper.AuthorRowMapper;
import org.example.library.model.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class JdbcAuthorDao implements AuthorDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAuthorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Author author) {
        String sql = "INSERT INTO authors (name, country) VALUES (?, ?)";
        jdbcTemplate.update(sql, author.getName(), author.getCountry());
    }

    @Override
    public Author findById(Long id) {
        String sql = "SELECT * FROM authors WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AuthorRowMapper(), id);
    }

    @Override
    public List<Author> findAll() {
        String sql = "SELECT * FROM authors";
        return jdbcTemplate.query(sql, new AuthorRowMapper());
    }

    @Override
    public void update(Author author) {
        String sql = "UPDATE authors SET name = ?, country = ? WHERE id = ?";
        jdbcTemplate.update(sql,author.getName(), author.getCountry(), author.getId());

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM authors WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
