package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.models.Company;
import ru.itis.reflex.models.Department;
import ru.itis.reflex.models.User;
import ru.itis.reflex.repositories.DepartmentRepository;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.DepartmentService;
import ru.itis.reflex.services.interfaces.KeyService;
import ru.itis.reflex.services.interfaces.UserService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private KeyService keyService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void addDepartment(String name, String key) {
        String email = keyService.getKeyByValue(key).getEmail();
        User user = userService.getUser(email);
        Company company = companyService.getCompanyByHead(user);
        Department department = Department.builder()
                .name(name)
                .company(company)
                .build();
        departmentRepository.save(department);

    }

    @Override
    public void addDepartmentHead(String department, String email) {
        Department depatmentByName = getDepartmentByName(department);
        User user = userService.getUser(email);
        depatmentByName.setHead(user);
        departmentRepository.save(depatmentByName);
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.getByName(name);
    }

    @Override
    public Department getDepartment(Integer id) {
        return departmentRepository.getById(new Long(id));
    }
}
