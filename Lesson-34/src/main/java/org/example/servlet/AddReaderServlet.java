package org.example.servlet;

import jakarta.servlet.http.HttpServlet;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.ReaderDaoImpl;
import org.example.model.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reader")
public class AddReaderServlet extends HttpServlet {
    private final ReaderDaoImpl readerDao = new ReaderDaoImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!"application/json".equalsIgnoreCase(req.getContentType())) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Content-Type должен быть application/json");
            return;
        }

        BufferedReader reader = req.getReader();
        Reader newReader = gson.fromJson(reader, Reader.class);
        readerDao.insert(newReader);

        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h3>✅ Читатель успешно добавлен!</h3>");
        }
    }

}
