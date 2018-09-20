package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.MoodData;
import ru.itis.reflex.models.User;

import java.util.List;

public interface MoodDataService {

    List<MoodData> getUserMoodData(User user);
    List<MoodData> getMoodData();

}
