package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entity.BorrowedBook;
import org.example.entity.Reader;
import org.example.repo.BorrowedBookRepository;
import org.example.repo.ReaderRepository;

import java.util.List;

@Path("/borrowedbooks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BorrowedBookController {

    private final BorrowedBookRepository borrowedBookRepository = new BorrowedBookRepository();

    @POST
    public Response createBorrowedBooks(BorrowedBook borrowedBook) {
        try {
            borrowedBookRepository.create(borrowedBook);
            return Response.status(Response.Status.CREATED).entity(borrowedBook).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getAllBorrowedBooks() {
        try {
            List<BorrowedBook> borrowedBooks = borrowedBookRepository.getAll();
            return Response.ok(borrowedBooks).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getBorrowedBookById(@PathParam("id") Integer id) {
        try {
            BorrowedBook borrowedBook = borrowedBookRepository.getById(id.longValue());
            if (borrowedBook != null) {
                return Response.ok(borrowedBook).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBorrowedBook(@PathParam("id") Integer id, BorrowedBook borrowedBook) {
        try {
            BorrowedBook existing = borrowedBookRepository.getById(id.longValue());
            if (existing == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            borrowedBook.setId(id);
            borrowedBookRepository.update(borrowedBook);
            return Response.ok(borrowedBook).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBorrowedBook(@PathParam("id") Integer id) {
        try {
            BorrowedBook borrowedBook = borrowedBookRepository.getById(id.longValue());
            if (borrowedBook == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            borrowedBookRepository.remove(borrowedBook);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}

