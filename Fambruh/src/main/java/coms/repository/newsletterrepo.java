package coms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import coms.model.extra.newsletter;

public interface newsletterrepo extends JpaRepository<newsletter, Long> {
    
	 Optional<newsletter> findByEmail(String email);
	    void deleteByEmail(String email);
}
