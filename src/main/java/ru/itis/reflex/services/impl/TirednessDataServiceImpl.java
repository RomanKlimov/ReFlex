package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.models.TirednessData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.repositories.TirednessDataRepository;
import ru.itis.reflex.services.interfaces.TirednessDataService;

@Service
public class TirednessDataServiceImpl implements TirednessDataService {

    @Autowired
    private TirednessDataRepository tirednessDataRepository;

    @Override
    public void addMorningTirednessData(User user, Integer morningValue) {
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        TirednessData tirednessData = TirednessData.builder()
                .date(date)
                .morningValue(morningValue)
                .user(user)
                .build();
        tirednessDataRepository.save(tirednessData);
    }

    @Override
    public void addEveningTirednessData(User user, Integer eveningValue) {
        TirednessData lastTiredness = tirednessDataRepository.findFirstByUserOrderByDateDesc(user);
        lastTiredness.setEveningValue(eveningValue);
        tirednessDataRepository.save(lastTiredness);
    }
}
