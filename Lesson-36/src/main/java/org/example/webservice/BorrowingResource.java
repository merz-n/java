package org.example.webservice;

import org.example.dao.BorrowedBookDao;
import org.example.dao.BorrowedBookDaoImpl;
import org.example.model.BorrowedBook;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/borrowings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BorrowingResource {

    private final BorrowedBookDao borrowedBookDao = new BorrowedBookDaoImpl();

    @POST
    public Response addBorrowing(BorrowedBook borrowing) {
        boolean success = borrowedBookDao.addBorrowedBook(borrowing);
        if (success) {
            return Response.status(Response.Status.CREATED).build(); // HTTP 201
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build(); // HTTP 500
        }
    }
}