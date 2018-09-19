package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.Company;
import ru.itis.reflex.models.User;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findOneByName(String companyName);
    Company findByName(String name);
    Company findByHead(User user);
}
