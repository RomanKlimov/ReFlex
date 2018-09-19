package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.exceptions.CompanyExistsException;
import ru.itis.reflex.exceptions.EmailExistsException;
import ru.itis.reflex.models.Company;
import ru.itis.reflex.models.User;
import ru.itis.reflex.repositories.CompanyRepository;
import ru.itis.reflex.security.Role.Role;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.UserService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;

    @Override
    public void createCompany(String companyName) throws EmailExistsException, CompanyExistsException {
        if (!companyRepository.findOneByName(companyName).isPresent()){
            Company company = Company.builder()
                    .name(companyName)
                    .build();
            companyRepository.save(company);
        } else throw new CompanyExistsException("Компания с данным именем уже зарегистрирована: " + companyName);
    }

    @Override
    public Company getCompanyByName(String companyName) {
        if(companyRepository.findOneByName(companyName).isPresent()){
            return companyRepository.findOneByName(companyName).get();
        } else return null;
    }

    @Override
    public void addCompanyHead(String companyName, String headEmail) {
        Company companyByName = getCompanyByName(companyName);
        User user = userService.getUser(headEmail);
        companyByName.setHead(user);
        companyRepository.save(companyByName);
    }

    @Override
    public Company getCompanyByHead(User head) {
        return companyRepository.findByHead(head);
    }
}
