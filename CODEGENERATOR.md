# CodeGenerator REST API

A Spring Boot 3 REST API that **automatically generates C# code** for Visual Studio, with built-in Swagger documentation and a minimal web UI.

## 🎯 Features

- ✅ **POST /api/generate** – Generate C# class code dynamically
- ✅ **GET /api/generate/{id}** – Retrieve previously generated code
- ✅ **GET /api/generate/health** – Health check endpoint
- ✅ **Swagger/OpenAPI UI** – Interactive API documentation at `/swagger-ui.html`
- ✅ **H2 In-Memory Database** – Store and retrieve generated snippets
- ✅ **Minimal Web Frontend** – Simple UI to generate code at `/`
- ✅ **Lombok Annotations** – Reduced boilerplate code
- ✅ **Input Validation** – Java validation with @Valid annotations
- ✅ **Modular Design** – Easy to extend for additional languages/templates

## 📋 Project Structure

```
src/main/java/com/recruiterproof/codegenerator/
├── CodeGeneratorApplication.java
├── controller/
│   ├── CodeController.java        # REST endpoints
│   └── HomeController.java         # Frontend routing
├── model/
│   ├── CodeRequest.java            # Request DTO
│   └── CodeResponse.java           # Response DTO
├── entity/
│   └── GeneratedCode.java          # JPA entity
├── repository/
│   └── GeneratedCodeRepository.java # Data access
├── service/
│   └── CodeService.java            # Business logic
├── util/
│   └── CodeTemplateGenerator.java   # Code template generation
└── config/
    └── OpenApiConfig.java          # Swagger configuration

src/main/resources/
├── application.properties           # Spring Boot config
└── templates/
    └── index.html                  # Web UI
```

## 🚀 Quick Start

### Prerequisites
- **Java 21** or later
- **Maven 3.6+**

### Clone & Build

```bash
cd /Users/producer/Downloads/codegenerator
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on **http://localhost:8080**

### Access the Endpoints

- **Web UI:** http://localhost:8080/
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **API Docs:** http://localhost:8080/v3/api-docs
- **H2 Console:** http://localhost:8080/h2-console (username: `sa`, password: empty)

## 📝 API Usage

### Generate C# Code

**POST** `/api/generate`

**Request Body (JSON):**

```json
{
  "className": "User",
  "properties": {
    "id": "int",
    "name": "string",
    "email": "string",
    "isActive": "bool"
  },
  "namespace": "MyApp.Models",
  "zip": false
}
```

**Response (200 OK):**

```json
{
  "id": 1,
  "className": "User",
  "namespace": "MyApp.Models",
  "generatedCode": "namespace MyApp.Models\n{\n    public class User\n    {\n        public int Id { get; set; }\n        ...\n    }\n}\n",
  "createdAt": "2025-12-17T10:30:00+01:00",
  "message": "C# class generated successfully"
}
```

### Retrieve Generated Code

**GET** `/api/generate/{id}`

**Response (200 OK):**

```json
{
  "id": 1,
  "className": "User",
  "namespace": "MyApp.Models",
  "generatedCode": "..."
}
```

### Health Check

**GET** `/api/generate/health`

**Response (200 OK):**

```json
{
  "status": "UP"
}
```

## 🎨 Generated C# Example

For the request above, the generated C# code will be:

```csharp
namespace MyApp.Models
{
    public class User
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public bool IsActive { get; set; }

        public User()
        {
        }

        public override string ToString()
        {
            return "User { " +
                   "Id: " + Id +
                   ", " +
                   "Name: " + Name +
                   ", " +
                   "Email: " + Email +
                   ", " +
                   "IsActive: " + IsActive +
                   " }";
        }
    }
}
```

## 🔧 Configuration

Edit `src/main/resources/application.properties` to customize:

```properties
# Application name
spring.application.name=codegenerator

# H2 Database (in-memory)
spring.datasource.url=jdbc:h2:mem:codegen
spring.h2.console.enabled=true

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update

# Swagger UI
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

## 📦 Dependencies

- **Spring Boot 3.5.8** (Java 21)
- **Spring Web** – REST controller support
- **Spring Data JPA** – Database abstraction
- **H2 Database** – In-memory SQL database
- **Lombok** – Boilerplate reduction
- **Springdoc OpenAPI** – Swagger UI & API documentation
- **Validation** – Bean validation

## 🧪 Testing with cURL

### Generate C# Code

```bash
curl -X POST http://localhost:8080/api/generate \
  -H "Content-Type: application/json" \
  -d '{
    "className": "Product",
    "properties": {
      "id": "int",
      "name": "string",
      "price": "decimal"
    },
    "namespace": "Shop.Models"
  }'
```

### Get Generated Code

```bash
curl http://localhost:8080/api/generate/1
```

## 🛠️ Extension Ideas

The modular structure makes it easy to add:

1. **Multiple Language Support** – Extend `CodeTemplateGenerator` with methods for Java, Python, Go, etc.
2. **JWT Authentication** – Add Spring Security with JWT tokens
3. **ZIP Export** – Combine generated code with project templates into downloadable ZIPs
4. **Database Persistence** – Switch from H2 to PostgreSQL for production
5. **API Key Authentication** – Implement basic API key validation
6. **Code Templates** – Add configurable templates for different architectures (SOLID, MVVM, etc.)

## 📄 License

This project is provided as-is for educational and commercial use.

## 🤝 Support

For issues, questions, or feature requests, please contact RecruiterProof.

---

**Created with ❤️ using Spring Boot 3, Lombok, and Springdoc OpenAPI**
