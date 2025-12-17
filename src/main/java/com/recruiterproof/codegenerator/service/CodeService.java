package com.recruiterproof.codegenerator.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.recruiterproof.codegenerator.entity.GeneratedCode;
import com.recruiterproof.codegenerator.model.CodeRequest;
import com.recruiterproof.codegenerator.model.CodeResponse;
import com.recruiterproof.codegenerator.repository.GeneratedCodeRepository;
import com.recruiterproof.codegenerator.util.CodeTemplateGenerator;

import lombok.RequiredArgsConstructor;

/**
 * CodeService handles the business logic for code generation and storage.
 */
@Service
@RequiredArgsConstructor
public class CodeService {

    private final GeneratedCodeRepository generatedCodeRepository;

    /**
     * Generate C# code from a CodeRequest and optionally store it.
     */
    public CodeResponse generateCode(CodeRequest request) {
        // Generate C# code
        String csharpCode = CodeTemplateGenerator.generateCSharpClass(request);

        // Store in database
        GeneratedCode entity = GeneratedCode.builder()
                .className(request.getClassName())
                .namespaceName(request.getNamespace())
                .content(csharpCode)
                .createdAt(OffsetDateTime.now())
                .build();

        GeneratedCode saved = generatedCodeRepository.save(entity);

        // Build response
        return CodeResponse.builder()
                .id(saved.getId())
                .className(saved.getClassName())
                .namespace(saved.getNamespaceName())
                .generatedCode(saved.getContent())
                .createdAt(saved.getCreatedAt().toString())
                .message("C# class generated successfully")
                .build();
    }

    /**
     * Get previously generated code by ID.
     */
    public CodeResponse getGeneratedCode(Long id) {
        return generatedCodeRepository.findById(id)
                .map(entity -> CodeResponse.builder()
                        .id(entity.getId())
                        .className(entity.getClassName())
                        .namespace(entity.getNamespaceName())
                        .generatedCode(entity.getContent())
                        .createdAt(entity.getCreatedAt().toString())
                        .build())
                .orElse(null);
    }
}
