package org.example.webservice;

import org.example.dao.ReaderDao;
import org.example.dao.ReaderDaoImpl;
import org.example.model.Reader;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/readers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReaderResource {

    private final ReaderDao readerDao = new ReaderDaoImpl();

    @POST
    public Response addReader(Reader reader) {
        boolean success = readerDao.addReader(reader);
        if (success) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
