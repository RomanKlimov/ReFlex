package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.*;

import java.util.List;

public interface StatisticDataService {
    List<MoodData> getUserMoodData(User user);
    List<TirednessData> getUserTirednessData(User user);
    List<PostureData> getUserPostureData(User user);
}
