package org.example.config;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.example.controller.BookController;
import org.example.controller.ReaderController;
import org.example.controller.BorrowedBookController;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){
        register(BookController.class);
        register(ReaderController.class);
        register(BorrowedBookController.class);
    }
}
