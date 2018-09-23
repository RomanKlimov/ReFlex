package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.Department;
import ru.itis.reflex.models.PostureData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.models.User;
import ru.itis.reflex.util.AggregateResult;
import ru.itis.reflex.util.PostureAggregateResult;

import java.util.Optional;

import java.sql.Date;
import java.util.List;

@Repository
public interface PostureDataRepository extends JpaRepository<PostureData, Integer> {
    Optional<PostureData> findOneByUserAndDate(User user, java.util.Date date);
    List<PostureData> findAllByUserAndDateAfter(User user, Date date);
    List<PostureData> findAllByUser(User user);

    @Query("SELECT new ru.itis.reflex.util.PostureAggregateResult( " +
            "AVG(p.smoothNum), " +
            "AVG(p.flexNum), " +
            "p.date) " +
            "FROM PostureData p " +
            "JOIN p.user u " +
            "WHERE p.date >= :date AND u.department = :department " +
            "GROUP BY p.date " +
            "ORDER BY p.date")
    List<PostureAggregateResult> findAvgPostureByDepartment(@Param("department")Department department, @Param("date")Date date);

}
