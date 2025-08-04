package org.example.library.dao;

import org.example.library.mapper.AuthorRowMapper;
import org.example.library.mapper.BookRowMapper;
import org.example.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcBookDao implements BookDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Book book) {
        String sql = "INSERT INTO books (title, published_year, author_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getPublishedYear(), book.getAuthorId());

    }

    @Override
    public Book findById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BookRowMapper(), id);
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, published_year = ?, author_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, book.getTitle(), book.getPublishedYear(), book.getAuthorId(), book.getId());

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
