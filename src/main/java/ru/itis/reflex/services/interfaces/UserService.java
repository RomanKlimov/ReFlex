package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.User;

public interface UserService {

    User getUser(Long id);

    User getUser(String email);

    void save(User user);

}
