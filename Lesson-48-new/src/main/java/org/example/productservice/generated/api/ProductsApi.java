package org.example.productservice.generated.api;

import org.example.productservice.generated.model.Product;
import org.example.productservice.generated.model.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Tag(name = "products", description = "the products API")
@RequestMapping("${openapi.productManagementAPI.base-path:/api}")
public interface ProductsApi {

    @Operation(
            operationId = "createProduct",
            summary = "Create a product",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product successfully created",
                            content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request (validation error)",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping(
            value = "/products",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    ResponseEntity<Product> createProduct(
            @Parameter(description = "Product object that needs to be created", required = true)
            @Valid @RequestBody Product product
    );

    @Operation(
            operationId = "getAllProducts",
            summary = "Find all products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of products",
                            content = @Content(schema = @Schema(implementation = Product[].class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping(
            value = "/products",
            produces = { "application/json" }
    )
    ResponseEntity<List<Product>> getAllProducts(
            @Parameter(description = "Maximum number of products to return")
            @RequestParam(value = "limit", required = false, defaultValue = "25") Integer limit,

            @Parameter(description = "Number of products to skip")
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    );

    @Operation(
            operationId = "getProductById",
            summary = "Find a product by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product found",
                            content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping(
            value = "/products/{id}",
            produces = { "application/json" }
    )
    ResponseEntity<Product> getProductById(
            @Parameter(description = "ID of product to return", required = true)
            @PathVariable("id") Long id
    );

    @Operation(
            operationId = "deactivateProduct",
            summary = "Deactivate a product",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product successfully deactivated",
                            content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PutMapping(
            value = "/products/{id}/deactivate",
            produces = { "application/json" }
    )
    ResponseEntity<Product> deactivateProduct(
            @Parameter(description = "ID of product to deactivate", required = true)
            @PathVariable("id") Long id
    );
}
