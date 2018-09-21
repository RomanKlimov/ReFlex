package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.TirednessData;
import ru.itis.reflex.models.User;

import java.sql.Date;
import java.util.List;

@Repository
public interface TirednessDataRepository extends JpaRepository<TirednessData, Integer> {
    List<TirednessData> findAllByUserAndDateAfter(User user, Date date);
}
