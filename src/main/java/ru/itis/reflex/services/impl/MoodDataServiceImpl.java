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

    @Override
    public MoodData getLastMoodByUser(User user) {
        return moodDataRepository.findFirstByUserOrderByDateDesc(user);
    }

    @Override
    public List<MoodData> getMoodDataBy(User user, java.util.Date date) {
        return null;
    }



    @Override
    public void addMorningMoodData(User user, Integer morningValue) {

        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        MoodData moodData = MoodData.builder()
                .date(date)
                .user(user)
                .morningValue(morningValue)
                .build();
        moodDataRepository.save(moodData);
    }

    @Override
    public void addEveningMoodData(User user, Integer eveningValue) {
        MoodData lastMoodData = moodDataRepository.findFirstByUserOrderByDateDesc(user);
        lastMoodData.setEveningValue(eveningValue);
        moodDataRepository.save(lastMoodData);
    }
}
