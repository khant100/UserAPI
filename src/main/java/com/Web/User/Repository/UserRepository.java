package com.Web.User.Repository;

import com.Web.User.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<List<User>> findByEmail(String email);

    Optional<User> findByEmailAndId(String email,Long id);
}
