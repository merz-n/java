package org.example.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entity.Book;
import org.example.entity.Reader;
import org.example.repo.BookRepository;
import org.example.repo.ReaderRepository;

import java.util.List;

@Path("/readers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReaderController {
    private final ReaderRepository readerRepository = new ReaderRepository();

    @POST
    public Response createReader(Reader reader ) {
        try {
            readerRepository.create(reader);
            return Response.status(Response.Status.CREATED).entity(reader).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getAllReaders() {
        try {
            List<Reader> readers = readerRepository.getAll();
            return Response.ok(readers).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getReaderById(@PathParam("id") Integer id) {
        try {
            Reader reader = readerRepository.getById(id.longValue());
            if (reader != null) {
                return Response.ok(reader).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateReader(@PathParam("id") Integer id, Reader reader) {
        try {
            Reader existing = readerRepository.getById(id.longValue());
            if (existing == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            reader.setId(id);
            readerRepository.update(reader);
            return Response.ok(reader).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReader(@PathParam("id") Integer id) {
        try {
            Reader reader = readerRepository.getById(id.longValue());
            if (reader == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            readerRepository.remove(reader);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
