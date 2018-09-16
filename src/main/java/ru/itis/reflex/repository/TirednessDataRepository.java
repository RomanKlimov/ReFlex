package ru.itis.reflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.model.TirednessData;

@Repository
public interface TirednessDataRepository extends JpaRepository<TirednessData, Integer> {
}
