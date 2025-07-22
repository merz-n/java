package org.example.servlet;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.BookDaoImpl;
import org.example.dao.BorrowedBookDaoImpl;
import org.example.dao.ReaderDaoImpl;
import org.example.model.Book;
import org.example.model.BorrowedBook;
import org.example.model.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


@WebServlet("/borrow")
public class BorrowBookServlet extends HttpServlet {

    private final BorrowedBookDaoImpl borrowedBookDao = new BorrowedBookDaoImpl();
    private final ReaderDaoImpl readerDao = new ReaderDaoImpl();
    private final BookDaoImpl bookDao = new BookDaoImpl();
    private final Gson gson = new Gson();


    static class BorrowRequestDTO {
        public int bookId;
        public int readerId;
        public String borrowDate;
        public String status;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!"application/json".equalsIgnoreCase(req.getContentType())) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Content-Type должен быть application/json");
            return;
        }


        BufferedReader reader = req.getReader();
        BorrowRequestDTO dto = gson.fromJson(reader, BorrowRequestDTO.class);


        Book book = null;
        for (Book b : bookDao.findAll()) {
            if (b.getId() == dto.bookId) {
                book = b;
                break;
            }
        }


        Reader readerObj = null;
        for (Reader r : readerDao.findAll()) {
            if (r.getId() == dto.readerId) {
                readerObj = r;
                break;
            }
        }


        if (book == null || readerObj == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Книга или читатель не найдены");
            return;
        }


        BorrowedBook borrowedBook = new BorrowedBook(
                book,
                readerObj,
                LocalDate.parse(dto.borrowDate),
                null,
                dto.status
        );

        borrowedBookDao.insert(borrowedBook);

        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h3>✅ Книга успешно выдана читателю</h3>");
        }
    }
}
