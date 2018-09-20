package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.exceptions.EmailExistsException;
import ru.itis.reflex.forms.AdminRegistrationForm;
import ru.itis.reflex.forms.BossRegistrationForm;

public interface RegistrationSevice {
    void createAdminAccount(AdminRegistrationForm adminRegistrationForm) throws EmailExistsException;
    void createBossAccount(BossRegistrationForm bossRegistrationForm);
}
