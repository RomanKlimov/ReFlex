package ru.itis.reflex.services.interfaces;

import org.springframework.security.core.Authentication;
import ru.itis.reflex.models.User;


public interface AuthService {
    User getUserByAuthentication(Authentication authentication);
}
