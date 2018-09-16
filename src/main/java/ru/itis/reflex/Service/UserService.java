package ru.itis.reflex.Service;

import ru.itis.reflex.model.User;

public interface UserService {

    User getUser(String email);

    void save(User user);

}
