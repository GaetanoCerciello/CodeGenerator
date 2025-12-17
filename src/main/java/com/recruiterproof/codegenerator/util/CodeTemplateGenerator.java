package com.recruiterproof.codegenerator.util;

import java.util.Map;

import com.recruiterproof.codegenerator.model.CodeRequest;

/**
 * CodeTemplateGenerator generates C# code templates from CodeRequest.
 * This utility can be extended to support multiple languages and templates.
 */
public class CodeTemplateGenerator {

    /**
     * Generate C# class code from a CodeRequest.
     */
    public static String generateCSharpClass(CodeRequest request) {
        StringBuilder sb = new StringBuilder();

        // Namespace
        sb.append(String.format("namespace %s%n", request.getNamespace()));
        sb.append("{%n");

        // Class declaration
        sb.append(String.format("    public class %s%n", request.getClassName()));
        sb.append("    {%n");

        // Properties
        if (request.getProperties() != null && !request.getProperties().isEmpty()) {
            for (Map.Entry<String, String> entry : request.getProperties().entrySet()) {
                String propName = entry.getKey();
                String propType = entry.getValue();
                String propertyName = capitalizeFirstLetter(propName);

                sb.append(String.format("        public %s %s { get; set; }%n", propType, propertyName));
            }
        }

        // Constructor
        sb.append(String.format("%n        public %s()%n", request.getClassName()));
        sb.append("        {%n");
        sb.append("        }%n");

        // ToString override
        sb.append("%n        public override string ToString()%n");
        sb.append("        {%n");
        sb.append(String.format("            return \"%s { \" +%n", request.getClassName()));

        if (request.getProperties() != null && !request.getProperties().isEmpty()) {
            int count = 0;
            for (String key : request.getProperties().keySet()) {
                String propertyName = capitalizeFirstLetter(key);
                if (count > 0) {
                    sb.append("                   \", \" +%n");
                }
                sb.append(String.format("                   \"%s: \" + %s +%n", propertyName, propertyName));
                count++;
            }
        }

        sb.append("                   \" }\";%n");
        sb.append("        }%n");

        // Close class
        sb.append("    }%n");

        // Close namespace
        sb.append("}%n");

        return sb.toString();
    }

    /**
     * Capitalize the first letter of a string.
     */
    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Generate a C# project file (.csproj) template.
     */
    public static String generateCSharpProjectFile(String projectName, String framework) {
        return String.format(
                "<Project Sdk=\"Microsoft.NET.Sdk\">%n" +
                "%n" +
                "  <PropertyGroup>%n" +
                "    <TargetFramework>%s</TargetFramework>%n" +
                "    <LangVersion>latest</LangVersion>%n" +
                "    <Nullable>enable</Nullable>%n" +
                "  </PropertyGroup>%n" +
                "%n" +
                "</Project>%n",
                framework);
    }
}
