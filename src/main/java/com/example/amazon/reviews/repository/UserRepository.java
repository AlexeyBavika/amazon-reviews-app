package com.example.amazon.reviews.repository;

import com.example.amazon.reviews.model.User;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userId = :id")
    User findById(@Param("id") String id);

    User findByProfileName(String profileName);

    @Query("SELECT new User(r.profileName, r.userId) FROM Review r GROUP BY r.userId "
            + "ORDER BY COUNT(r.userId) DESC")
    List<User> findMostActiveUsers(PageRequest p);
}
