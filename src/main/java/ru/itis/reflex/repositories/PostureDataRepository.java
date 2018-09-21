package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.PostureData;
import ru.itis.reflex.models.User;

import java.sql.Date;
import java.util.List;

@Repository
public interface PostureDataRepository extends JpaRepository<PostureData, Integer> {
    List<PostureData> findAllByUserAndDateAfter(User user, Date date);
}
