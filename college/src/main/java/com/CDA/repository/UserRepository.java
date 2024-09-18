package com.CDA.repository;

import com.CDA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);
//
//    boolean existsByUsername(String username);
//
//    @Query("SELECT u FROM User u JOIN u.facultyProfile fp WHERE fp.department = :department")
//    List<User> findAdvisorsForStudent(@Param("department") String department);

}