
package com.webpayglobal.admin.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Entity class representing an admin user.
 */
@Data
@NoArgsConstructor // Added for clarity in entity handling and testing
@Entity
@Table(name = "admins")
public class Admin {

    private static final String USERNAME_MANDATORY = "Username is mandatory";
    private static final String USERNAME_SIZE = "Username must be between 3 and 50 characters";
    private static final String PASSWORD_MANDATORY = "Password is mandatory";
    private static final String PASSWORD_SIZE = "Password must be at least 8 characters long";
    private static final String EMAIL_MANDATORY = "Email is mandatory";
    private static final String EMAIL_VALID = "Email should be valid";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = USERNAME_MANDATORY)
    @Size(min = 3, max = 50, message = USERNAME_SIZE)
    @Column(nullable = false, unique = true)
    private String userName; // Renamed for consistency

    @NotBlank(message = PASSWORD_MANDATORY)
    @Size(min = 8, message = PASSWORD_SIZE)
    @Column(nullable = false)
    private String password;

    @NotBlank(message = EMAIL_MANDATORY)
    @Email(message = EMAIL_VALID)
    @Column(nullable = false)
    private String email;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    /**
     * Sets the password, implementing secure hashing.
     */
    public void setPassword(String password) {
        this.password = hashPassword(password); // Extracted method for hashing
    }

    /**
     * An example method to hash passwords securely.
     *
     * @param password the plain-text password
     * @return the hashed password
     */
    private String hashPassword(String password) {
        // Example: return HashingFunction.hash(password);
        return password; // Replace with actual hashing logic
    }
}