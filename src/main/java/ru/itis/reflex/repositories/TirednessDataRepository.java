package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.Department;
import ru.itis.reflex.models.TirednessData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.util.AggregateResult;

import java.sql.Date;
import java.util.List;

@Repository
public interface TirednessDataRepository extends JpaRepository<TirednessData, Integer> {
    List<TirednessData> findAllByUserAndDateAfter(User user, Date date);
    List<TirednessData> findAllByUser(User user);

    @Query("SELECT new ru.itis.reflex.util.AggregateResult( " +
            "AVG(d.morningValue), " +
            "AVG(d.eveningValue), " +
            "d.date) " +
            "FROM TirednessData d " +
            "JOIN d.user u " +
            "WHERE d.date >= :date AND u.department = :department " +
            "GROUP BY d.date " +
            "ORDER BY d.date")
    List<AggregateResult> findAvgTirednessByDepartment(@Param("department")Department department, @Param("date")Date date);

    TirednessData findFirstByUserOrderByDateDesc(User user);
}
