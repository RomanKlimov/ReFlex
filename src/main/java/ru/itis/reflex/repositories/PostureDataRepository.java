package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.PostureData;

@Repository
public interface PostureDataRepository extends JpaRepository<PostureData, Integer> {
}
