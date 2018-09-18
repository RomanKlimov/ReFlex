package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.reflex.exceptions.EmailExistsException;
import ru.itis.reflex.forms.AdminRegistrationForm;
import ru.itis.reflex.models.User;
import ru.itis.reflex.repositories.CompanyRepository;
import ru.itis.reflex.repositories.UserRepository;
import ru.itis.reflex.security.Role.Role;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.RegistrationSevice;


@Service
public class RegistrationServiceImpl implements RegistrationSevice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyService companyService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void createAdminAccount(AdminRegistrationForm adminRegistrationForm) throws EmailExistsException {
        if (!userRepository.findOneByEmail(adminRegistrationForm.getEmail()).isPresent()){
            System.out.println(companyService.getCompanyByName(adminRegistrationForm.getCompany()));
            User user = User.builder()
                    .name(adminRegistrationForm.getName())
                    .email(adminRegistrationForm.getEmail())
                    .password(passwordEncoder.encode(adminRegistrationForm.getPassword()))
                    .company(companyService.getCompanyByName(adminRegistrationForm.getCompany()))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(user);
        } else throw new EmailExistsException("Аккаунт с такой почтой уже зарегистрирован: " + adminRegistrationForm.getEmail());
    }
    }


