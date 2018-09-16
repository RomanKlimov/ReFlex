package ru.itis.reflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.model.PostureData;

@Repository
public interface PostureDataRepository extends JpaRepository<PostureData, Integer> {
}
