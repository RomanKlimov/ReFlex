package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.Department;
import ru.itis.reflex.models.MoodData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.util.AggregateResult;


import java.util.Date;
import java.util.List;

@Repository
public interface MoodDataRepository extends JpaRepository<MoodData, Integer> {

    List<MoodData> findAllByUserAndDateAfter(User user, Date date);

    List<MoodData> findByUser(User user);

    MoodData findFirstByUserOrderByDateDesc(User user);

    List<MoodData> findAllByUser(User user);


    @Query("SELECT new ru.itis.reflex.util.AggregateResult( " +
            "AVG(m.morningValue), " +
            "AVG(m.eveningValue), " +
            "m.date) " +
            "FROM MoodData m " +
            "JOIN m.user u " +
            "WHERE m.date >= :date AND u.department = :department " +
            "GROUP BY m.date " +
            "ORDER BY m.date")
    List<AggregateResult> findAvgMoodByDepartment(@Param("department")Department department, @Param("date")Date date);

}
