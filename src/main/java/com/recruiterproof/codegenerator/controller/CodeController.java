package com.recruiterproof.codegenerator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruiterproof.codegenerator.model.CodeRequest;
import com.recruiterproof.codegenerator.model.CodeResponse;
import com.recruiterproof.codegenerator.service.CodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * CodeController handles REST endpoints for code generation.
 */
@RestController
@RequestMapping("/api/generate")
@RequiredArgsConstructor
@Tag(name = "Code Generation", description = "REST API for generating C# code")
public class CodeController {

    private final CodeService codeService;

    /**
     * Generate C# class code from a CodeRequest.
     * Example request:
     * {
     *   "className": "User",
     *   "properties": {
     *     "id": "int",
     *     "name": "string",
     *     "email": "string"
     *   },
     *   "namespace": "MyApp.Models",
     *   "zip": false
     * }
     */
    @PostMapping
    @Operation(summary = "Generate C# class code", description = "Generates a C# class from the provided class name and properties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "C# code generated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CodeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CodeResponse> generateCode(@Valid @RequestBody CodeRequest request) {
        try {
            CodeResponse response = codeService.generateCode(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CodeResponse.builder()
                            .message("Error generating code: " + e.getMessage())
                            .build());
        }
    }

    /**
     * Retrieve previously generated code by ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve generated code", description = "Get a previously generated C# code by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Code found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CodeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Code not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CodeResponse> getGeneratedCode(@PathVariable Long id) {
        try {
            CodeResponse response = codeService.getGeneratedCode(id);
            if (response == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CodeResponse.builder()
                            .message("Error retrieving code: " + e.getMessage())
                            .build());
        }
    }

    /**
     * Health check endpoint.
     */
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Check if the API is running")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("{\"status\":\"UP\"}");
    }
}
