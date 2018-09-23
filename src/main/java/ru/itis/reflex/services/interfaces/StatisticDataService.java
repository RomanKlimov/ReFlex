package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.*;
import ru.itis.reflex.util.AggregateResult;
import ru.itis.reflex.util.PostureAggregateResult;

import java.util.List;

public interface StatisticDataService {
    List<MoodData> getUserMoodData(User user, String timePeriod);
    List<TirednessData> getUserTirednessData(User user, String timePeriod);
    List<PostureData> getUserPostureData(User user, String timePeriod);

    List<AggregateResult> getAvgMoodData(Department department, String timePeriod);
    List<AggregateResult> getAvgTirednessData(Department department, String timePeriod);
    List<PostureAggregateResult> getAvgPostureData(Department department, String timePeriod);
}
