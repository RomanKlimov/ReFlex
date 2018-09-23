package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.User;

public interface TirednessDataService {
    void addMorningTirednessData(User user, Integer morningValue);
    void addEveningTirednessData(User user, Integer eveningValue);

}
