package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.MoodData;
import ru.itis.reflex.models.User;

import java.util.Date;
import java.util.List;

public interface MoodDataService {

    List<MoodData> getUserMoodData(User user);
    List<MoodData> getMoodData();
    MoodData getLastMoodByUser(User user);
    List<MoodData> getMoodDataBy(User user, Date date);
    void addMorningMoodData(User user, Integer morningValue);
    void addEveningMoodData(User user, Integer eveningValue);
}
