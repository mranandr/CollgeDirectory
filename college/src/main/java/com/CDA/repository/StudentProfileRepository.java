package com.CDA.repository;

import com.CDA.model.StudentProfile;
import com.CDA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    Optional<StudentProfile> findByUserId(Long userId);

    @Query("SELECT sp FROM StudentProfile sp WHERE "
            + "(sp.user.username LIKE %:name% OR :name IS NULL) "
            + "AND (sp.department.name = :department OR :department IS NULL) "
            + "AND (sp.year = :year OR :year IS NULL)")
    List<StudentProfile> searchStudents(
            @Param("name") String name,
            @Param("department") String department,
            @Param("year") String year);

    @Query("SELECT sp FROM StudentProfile sp JOIN sp.department d WHERE d.id = :facultyId")
    List<StudentProfile> findStudentsByFaculty(@Param("facultyId") Long facultyId);

    Object findByUser_Username(String username);
}
