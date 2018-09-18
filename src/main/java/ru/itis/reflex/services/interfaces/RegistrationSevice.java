package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.exceptions.EmailExistsException;
import ru.itis.reflex.forms.AdminRegistrationForm;

public interface RegistrationSevice {
    void createAdminAccount(AdminRegistrationForm adminRegistrationForm) throws EmailExistsException;
}
