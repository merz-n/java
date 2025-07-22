package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.BookDaoImpl;
import org.example.model.Book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/readers/*")
public class GetBorrowedBooksServlet extends HttpServlet {
    private final BookDaoImpl bookDao = new BookDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || !pathInfo.matches("^/\\d+/books$")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный путь. Пример: /readers/1/books");
            return;
        }


        String[] parts = pathInfo.split("/");
        int readerId;
        try {
            readerId = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный ID читателя");
            return;
        }


        List<Book> borrowedBooks = bookDao.findByReaderId(readerId);


        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html><body>");
            writer.write("<h1>Книги, выданные читателю с ID " + readerId + ":</h1>");

            if (borrowedBooks.isEmpty()) {
                writer.write("<p>Нет выданных книг.</p>");
            } else {
                writer.write("<ul>");
                for (Book book : borrowedBooks) {
                    writer.write("<li>");
                    writer.write("<strong>" + book.getTitle() + "</strong> — " +
                            book.getAuthor() + " (" + book.getPublishedYear() + "), жанр: " + book.getGenre());
                    writer.write("</li>");
                }
                writer.write("</ul>");
            }

            writer.write("</body></html>");
        }
    }
}
