package ru.itis.reflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.model.MoodData;

@Repository
public interface MoodDataRepository extends JpaRepository<MoodData, Integer> {
}
