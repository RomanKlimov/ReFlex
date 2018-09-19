package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.models.Key;
import ru.itis.reflex.models.User;
import ru.itis.reflex.repositories.KeyRepository;
import ru.itis.reflex.security.webConfig.WebSecurityConfig;
import ru.itis.reflex.services.interfaces.KeyService;

import java.util.List;

@Service
public class KeyServiceImpl implements KeyService {

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    private KeyRepository keyRepository;

    @Override
    public void addKeys(String emails, User user) {
        String[] emailsArr = emails.trim().split(" ");
        for (String mail : emailsArr) {
            String value = webSecurityConfig.passwordEncoder().encode(mail);
            Key key = Key.builder()
                    .email(mail)
                    .value(value)
                    .head(user)
                    .build();
            keyRepository.save(key);

        }
    }

    @Override
    public List<Key> getAllKeys() {
        return keyRepository.findKeys();
    }

    @Override
    public List<Key> getAllByHead(User user) {
        return keyRepository.getAllByHead(user);
    }
}
