KEYSAT-API-EXPERIMENTAL is a Spring Boot application with Spring Security for secure web interactions. It includes user authentication and protected endpoints.
Configuration. This is an experimental build for researching and developing basic funcionality for the eventual KEYSAT2024 project.
Security Configuration (Auth.java)

    Endpoints:
        Public: "/", "/home", "/login"
        Secured: All others
    Authentication:
        Type: Form-based
        Page: "/login"
    User Details:
        Stored In-memory
        Default User: "user", Password: "password"

Controllers

    API Controller (API.java):
        Handles requests for "/", "/login"
        Returns view names for Thymeleaf templates

Application Entry Point

    Class: KeysatAlphaApplication.java
    Purpose: Bootstraps the application

Development
Setup

    Add spring-boot-starter-security and spring-boot-starter-thymeleaf to your build configuration.
    Place Thymeleaf templates in src/main/resources/templates.

Security

    Use BCryptPasswordEncoder for production password encoding.
    Include CSRF token in login forms.
    Check session management settings if experiencing authentication issues.

Debugging

    Set logging.level.org.springframework.security=DEBUG for in-depth security logs.
