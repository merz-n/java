package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entity.Book;
import org.example.entity.BorrowedBook;
import org.example.repo.BookRepository;
import org.example.repo.BorrowedBookRepository;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {

    private final BookRepository bookRepository = new BookRepository();

    @POST
    public Response createBook(Book book) {
        try {
            bookRepository.create(book);
            return Response.status(Response.Status.CREATED).entity(book).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getAllBooks() {
        try {
            List<Book> books = bookRepository.getAll();
            return Response.ok(books).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Integer id) {
        try {
            Book book = bookRepository.getById(id.longValue());
            if (book != null) {
                return Response.ok(book).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") Integer id, Book book) {
        try {
            Book existing = bookRepository.getById(id.longValue());
            if (existing == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            book.setId(id);
            bookRepository.update(book);
            return Response.ok(book).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Integer id) {
        try {
            Book book = bookRepository.getById(id.longValue());
            if (book == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            bookRepository.remove(book);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/by-reader")
    public Response getBooksByReaderId(@QueryParam("readerId") Integer readerId) {
        try {
            BorrowedBookRepository borrowedBookRepository = new BorrowedBookRepository();
            List<BorrowedBook> allBorrowed = borrowedBookRepository.getAll();

            List<Book> result = new java.util.ArrayList<>();
            for (BorrowedBook bb : allBorrowed) {
                if (bb.getReader().getId().equals(readerId) &&
                        "borrowed".equalsIgnoreCase(bb.getStatus())) {
                    result.add(bb.getBook());
                }
            }

            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}