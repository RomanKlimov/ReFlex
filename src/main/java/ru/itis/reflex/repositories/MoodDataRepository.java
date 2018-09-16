package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.MoodData;

@Repository
public interface MoodDataRepository extends JpaRepository<MoodData, Integer> {
}
