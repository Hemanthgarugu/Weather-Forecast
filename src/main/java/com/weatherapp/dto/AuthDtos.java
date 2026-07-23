package com.weatherapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDtos {

    public static class LoginRequest {
        @NotBlank(message = "Username cannot be blank")
        private String username;

        @NotBlank(message = "Password cannot be blank")
        private String password;

        public LoginRequest() {}

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RegisterRequest {
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
        private String username;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
        private String password;

        public RegisterRequest() {}

        public RegisterRequest(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class AuthResponse {
        private String token;
        private String type = "Bearer";
        private Long id;
        private String username;
        private String email;
        private String role;

        public AuthResponse() {}

        public AuthResponse(String token, String type, Long id, String username, String email, String role) {
            this.token = token;
            this.type = type != null ? type : "Bearer";
            this.id = id;
            this.username = username;
            this.email = email;
            this.role = role;
        }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private String token;
            private String type = "Bearer";
            private Long id;
            private String username;
            private String email;
            private String role;

            public Builder token(String token) { this.token = token; return this; }
            public Builder type(String type) { this.type = type; return this; }
            public Builder id(Long id) { this.id = id; return this; }
            public Builder username(String username) { this.username = username; return this; }
            public Builder email(String email) { this.email = email; return this; }
            public Builder role(String role) { this.role = role; return this; }

            public AuthResponse build() {
                return new AuthResponse(token, type, id, username, email, role);
            }
        }
    }

    public static class UserSummary {
        private Long id;
        private String username;
        private String email;
        private String role;

        public UserSummary() {}

        public UserSummary(Long id, String username, String email, String role) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.role = role;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private Long id;
            private String username;
            private String email;
            private String role;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder username(String username) { this.username = username; return this; }
            public Builder email(String email) { this.email = email; return this; }
            public Builder role(String role) { this.role = role; return this; }

            public UserSummary build() {
                return new UserSummary(id, username, email, role);
            }
        }
    }
}
