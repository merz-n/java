package org.example.servlet;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.BookDaoImpl;
import org.example.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/books")
public class AddBookServlet extends HttpServlet{
    private final BookDaoImpl bookDao = new BookDaoImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!"application/json".equalsIgnoreCase(req.getContentType())) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Content-Type должен быть application/json");
            return;
        }

        BufferedReader reader = req.getReader();
        Book book = gson.fromJson(reader, Book.class);


        bookDao.insert(book);

        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h3>✅ Книга успешно добавлена!</h3>");

        }
    }
}
