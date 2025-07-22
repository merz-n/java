package org.example.webservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;


@WebService
public interface LibraryWebService {

    @WebMethod
    String addBook(
            @WebParam(name = "title") String title,
            @WebParam(name = "author") String author,
            @WebParam(name = "year") int publishedYear,
            @WebParam(name = "genre") String genre
    );

    @WebMethod
    String addReader(
            @WebParam(name = "name") String name,
            @WebParam(name = "email") String email,
            @WebParam(name = "phone") String phone
    );

    @WebMethod
    String reserveBook(
            @WebParam(name = "bookId") int bookId,
            @WebParam(name = "readerId") int readerId,
            @WebParam(name = "status") String status
    );

    @WebMethod
    String getBorrowedBooksByReaderId(
            @WebParam(name = "readerId") int readerId
    );
}
