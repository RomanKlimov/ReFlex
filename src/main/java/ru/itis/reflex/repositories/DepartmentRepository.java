package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department getByName(String name);
    Department getById(Long id);
}
