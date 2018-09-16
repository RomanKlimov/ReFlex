package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.TirednessData;

@Repository
public interface TirednessDataRepository extends JpaRepository<TirednessData, Integer> {
}
