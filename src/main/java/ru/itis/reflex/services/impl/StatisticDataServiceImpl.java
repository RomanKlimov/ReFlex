package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.models.MoodData;
import ru.itis.reflex.models.PostureData;
import ru.itis.reflex.models.TirednessData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.repositories.MoodDataRepository;
import ru.itis.reflex.repositories.PostureDataRepository;
import ru.itis.reflex.repositories.TirednessDataRepository;
import ru.itis.reflex.services.interfaces.StatisticDataService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticDataServiceImpl implements StatisticDataService{

    private final MoodDataRepository moodDataRepository;
    private final TirednessDataRepository tirednessDataRepository;
    private final PostureDataRepository postureDataRepository;


    @Autowired
    public StatisticDataServiceImpl(
            MoodDataRepository moodDataRepository,
            TirednessDataRepository tirednessDataRepository,
            PostureDataRepository postureDataRepository) {
        this.moodDataRepository = moodDataRepository;
        this.tirednessDataRepository = tirednessDataRepository;
        this.postureDataRepository = postureDataRepository;
    }

    @Override
    public List<MoodData> getUserMoodData(User user, String timePeriod) {
        return moodDataRepository.findAllByUserAndDateAfter(user, getBeginDate(timePeriod));
    }

    @Override
    public List<TirednessData> getUserTirednessData(User user, String timePeriod) {
        return tirednessDataRepository.findAllByUserAndDateAfter(user, getBeginDate(timePeriod));
    }

    @Override
    public List<PostureData> getUserPostureData(User user, String timePeriod) {
        return postureDataRepository.findAllByUserAndDateAfter(user, getBeginDate(timePeriod));
    }

    private Date getBeginDate(String timePeriod){
        if (timePeriod.equals("Year")){
            return Date.valueOf(LocalDate.now().minusYears(1));
        }
        else if (timePeriod.equals("Quarter")){
            return Date.valueOf(LocalDate.now().minusMonths(3));
        }
        else {
            return Date.valueOf(LocalDate.now().minusMonths(1));
        }
    }

}
