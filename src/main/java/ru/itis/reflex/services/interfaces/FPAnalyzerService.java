package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.User;

public interface FPAnalyzerService {

    void update(User user, byte[] userPhotoBytes);

    //TODO БУЛЕАН
    void initialize(User user, byte[] userPhotoBytes);

    boolean isFlexing(User user);

    boolean isInitialized(User user);

    boolean isTired(User user);

}
