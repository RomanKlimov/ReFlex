package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.Department;
import ru.itis.reflex.models.User;

public interface DepartmentService {
    void addDepartment(String name, String key);
    void addDepartmentHead(String department,String email);
    Department getDepartmentByName(String name);
    Department getDepartment(Integer id);
}
