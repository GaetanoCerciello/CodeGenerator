package com.recruiterproof.codegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CodeResponse DTO for the generated code response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeResponse {

    private Long id;
    private String className;
    private String namespace;
    private String generatedCode;
    private String createdAt;
    private String message;
}
