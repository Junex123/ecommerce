package coms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.user.PasswordResetToken;

//PasswordResetTokenRepository.java

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

 PasswordResetToken findByToken(String token);
}

