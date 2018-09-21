package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.PostureData;
import ru.itis.reflex.models.User;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PostureDataRepository extends JpaRepository<PostureData, Integer> {
    Optional<PostureData> findOneByUserAndDate(User user, Date date);
}
