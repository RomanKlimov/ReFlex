package ru.itis.reflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
