package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.MoodData;
import ru.itis.reflex.models.User;


import java.util.Date;
import java.util.List;

@Repository
public interface MoodDataRepository extends JpaRepository<MoodData, Integer> {
    List<MoodData> findAllByUserAndDateAfter(User user, Date date);

    List<MoodData> findByUser(User user);

    MoodData findFirstByUserOrderByDateDesc(User user);

}
