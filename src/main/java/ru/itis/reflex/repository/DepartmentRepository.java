package ru.itis.reflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.reflex.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
