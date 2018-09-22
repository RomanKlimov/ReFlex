package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.models.MoodData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.repositories.MoodDataRepository;

import ru.itis.reflex.services.interfaces.MoodDataService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class MoodDataServiceImpl implements MoodDataService{

    private final MoodDataRepository moodDataRepository;

    @Autowired
    public MoodDataServiceImpl(MoodDataRepository moodDataRepository) {
        this.moodDataRepository = moodDataRepository;
    }

    @Override
    public List<MoodData> getUserMoodData(User user) {
        Date beginDate = Date.valueOf(LocalDate.now().minusDays(30));
        return moodDataRepository.findAllByUserAndDateAfter(user, beginDate);
    }

    @Override
    public List<MoodData> getMoodData() {
        return null;
    }
}
