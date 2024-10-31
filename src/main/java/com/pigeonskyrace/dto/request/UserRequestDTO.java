package com.pigeonskyrace.dto.request;

import com.pigeonskyrace.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private Role role;

    public @NotBlank(message = "Name is required") @Size(max = 50) String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(max = 50) String name) {
        this.name = name;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password should be at least 8 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password should be at least 8 characters") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRequestDTO(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
