package org.example.webservice;

import org.example.dao.BookDao;
import org.example.dao.BookDaoImpl;
import org.example.model.Book;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookDao bookDao = new BookDaoImpl();

    @POST
    public Response addBook(Book book) {
        boolean success = bookDao.addBook(book);
        if (success) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/reader/{id}")
    public Response getBooksByReader(@PathParam("id") int readerId) {
        List<Book> books = bookDao.findByReaderId(readerId);
        return Response.ok(books).build();
    }
}
