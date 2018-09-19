package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.exceptions.CompanyExistsException;
import ru.itis.reflex.exceptions.EmailExistsException;
import ru.itis.reflex.forms.AdminRegistrationForm;
import ru.itis.reflex.models.Company;
import ru.itis.reflex.models.User;

public interface CompanyService {
    void createCompany(String companyName) throws EmailExistsException, CompanyExistsException;
    Company getCompanyByName(String companyName);
    void addCompanyHead(String companyName, String headEmail);
    Company getCompanyByHead(User head);

}
