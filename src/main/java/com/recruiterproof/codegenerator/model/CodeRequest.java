package com.recruiterproof.codegenerator.model;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeRequest {

    @NotBlank
    private String className;

    @NotEmpty
    private Map<String, String> properties;

    /**
     * When true, the response can be returned as a ZIP containing a minimal Visual Studio project.
     */
    @Builder.Default
    private boolean zip = false;

    @Builder.Default
    private String namespace = "Generated";
}
