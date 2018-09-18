package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.exceptions.CompanyExistsException;
import ru.itis.reflex.exceptions.EmailExistsException;
import ru.itis.reflex.models.Company;
import ru.itis.reflex.repositories.CompanyRepository;
import ru.itis.reflex.security.Role.Role;
import ru.itis.reflex.services.interfaces.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

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
        if(!companyRepository.findOneByName(companyName).isPresent()){
            System.out.println(companyRepository.findOneByName(companyName).get());
            return companyRepository.findOneByName(companyName).get();
        } else return null;

    }
}
