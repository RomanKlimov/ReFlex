package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.*;

import java.util.List;

public interface StatisticDataService {
    List<MoodData> getUserMoodData(User user, String timePeriod);
    List<TirednessData> getUserTirednessData(User user, String timePeriod);
    List<PostureData> getUserPostureData(User user, String timePeriod);
}
