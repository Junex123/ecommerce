package coms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.user.User;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    
    // Find a user by username
    public User findByUsername(String username);

    // Find a user by email
    public User findByEmail(String email);

    // Find users by enabled status
    public List<User> findByEnabled(boolean enabled);

    // Find users by role
    public List<User> findByUserRolesRole(String role);

    // Find users by username containing a keyword
    public List<User> findByUsernameContaining(String keyword);
}

