package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.exceptions.EmailExistsException;
import ru.itis.reflex.forms.AdminRegistrationForm;
import ru.itis.reflex.forms.BossRegistrationForm;
import ru.itis.reflex.forms.UserRegistrationForm;
import ru.itis.reflex.models.Key;
import ru.itis.reflex.models.User;
import ru.itis.reflex.repositories.UserRepository;
import ru.itis.reflex.security.Role.Role;
import ru.itis.reflex.security.webConfig.WebSecurityConfig;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.DepartmentService;
import ru.itis.reflex.services.interfaces.KeyService;
import ru.itis.reflex.services.interfaces.RegistrationSevice;


@Service
public class RegistrationServiceImpl implements RegistrationSevice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    private KeyService keyService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public void createAdminAccount(AdminRegistrationForm adminRegistrationForm) throws EmailExistsException {
        if (!userRepository.findOneByEmail(adminRegistrationForm.getEmail()).isPresent()){
            User user = User.builder()
                    .name(adminRegistrationForm.getName())
                    .email(adminRegistrationForm.getEmail())
                    .password(webSecurityConfig.passwordEncoder().encode(adminRegistrationForm.getPassword()))
                    .company(companyService.getCompanyByName(adminRegistrationForm.getCompany()))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(user);
        } else throw new EmailExistsException("Аккаунт с такой почтой уже зарегистрирован: " + adminRegistrationForm.getEmail());
    }

    @Override
    public void createBossAccount(BossRegistrationForm bossRegistrationForm) {
        if (!userRepository.findOneByEmail(bossRegistrationForm.getEmail()).isPresent()) {
            Key key = keyService.getKeyByValue(bossRegistrationForm.getKey());
            if (key.getEmail().equals(bossRegistrationForm.getEmail())){
                User user = User.builder()
                        .name(bossRegistrationForm.getName())
                        .email(bossRegistrationForm.getEmail())
                        .password(webSecurityConfig.passwordEncoder().encode(bossRegistrationForm.getPassword()))
                        .department(departmentService.getDepartmentByName(bossRegistrationForm.getDepartment()))
                        .company(key.getHead().getCompany())
                        .role(Role.BOSS)
                        .build();
                userRepository.save(user);

            }
        }
    }

    @Override
    public void createUserAccount(UserRegistrationForm userRegistrationForm) {
        if (!userRepository.findOneByEmail(userRegistrationForm.getEmail()).isPresent()) {
            Key key = keyService.getKeyByValue(userRegistrationForm.getKey());
            if (key.getEmail().equals(userRegistrationForm.getEmail())){
                User user = User.builder()
                        .name(userRegistrationForm.getName())
                        .email(userRegistrationForm.getEmail())
                        .password(webSecurityConfig.passwordEncoder().encode(userRegistrationForm.getPassword()))
                        .department(key.getHead().getDepartment())
                        .company(key.getHead().getCompany())
                        .role(Role.USER)
                        .build();
                userRepository.save(user);

            }
        }
    }
}