package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.Key;
import ru.itis.reflex.models.User;

import java.util.List;

public interface KeyService {
    void addKeys(String emails, User user);
    List<Key> getAllKeys();
    List<Key> getAllByHead(User user);
    List<Key> getKeysByEmails(String emails);
    Key getKeyByEmail(String email);
    Key getKeyByValue(String value);
}
