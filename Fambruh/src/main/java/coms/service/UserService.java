package coms.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import coms.model.user.PasswordResetToken;
import coms.model.user.User;
import coms.model.user.UserRole;
import coms.repository.RoleRepo;
import coms.repository.UserRepo;

import coms.repository.PasswordResetTokenRepository;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepo;

    // Register a new user
    public User createUser(User user, Set<UserRole> userRoles) {
        // Check if the user already exists
        User existingUser = this.userRepo.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("Username already exists!");
        }

        // Save user roles
        for (UserRole userRole : userRoles) {
            this.roleRepo.save(userRole.getRole());
        }

        // Assign user roles
        user.getUserRoles().addAll(userRoles);

        // Encrypt password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // Save the user
        return this.userRepo.save(user);
    }

    // Retrieve user by username
    public User getByUsername(String username) {
        return this.userRepo.findByUsername(username);
    }

    // Delete user by ID
    public void deleteUserById(Long userId) {
        this.userRepo.deleteById(userId);
    }

    // Generate password reset token and send reset link to user's email
    public void generatePasswordResetToken(String email) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setUser(user);
            resetToken.setToken(token);
            passwordResetTokenRepo.save(resetToken);
            // Send email with password reset link containing token
            // emailService.sendPasswordResetEmail(email, token);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    // Reset user's password using password reset token
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepo.findByToken(token);
        if (resetToken != null && !resetToken.isExpired()) {
            User user = resetToken.getUser();
            // Update user's password
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(user);
            // Invalidate the token
            passwordResetTokenRepo.delete(resetToken);
        } else {
            throw new RuntimeException("Invalid or expired token.");
        }
    }
}
