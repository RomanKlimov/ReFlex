package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
