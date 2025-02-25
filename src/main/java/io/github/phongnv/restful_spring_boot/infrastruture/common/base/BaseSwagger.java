package io.github.phongnv.restful_spring_boot.infrastruture.common.base;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class BaseSwagger {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Create a new resource")
    @ApiResponse(responseCode = "201", description = "Resource created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request: Invalid input")
    @ApiResponse(responseCode = "409", description = "Conflict: Duplicate resource")
    public @interface ApiCreate {
        Class<?> dto() default Object.class;  // Tham số cho DTO
        String message() default "";  // Tham số cho message
    }


    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Update an existing resource")
    @ApiResponse(responseCode = "200", description = "Resource updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request: Invalid input")
    @ApiResponse(responseCode = "404", description = "Not Found: Resource does not exist")
    public @interface ApiUpdate {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Delete a resource")
    @ApiResponse(responseCode = "204", description = "Resource deleted successfully")
    @ApiResponse(responseCode = "404", description = "Not Found: Resource does not exist")
    public @interface ApiDelete {
    }
}
