package ru.itis.reflex.controllers;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.reflex.models.GraphPointData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.MoodDataService;
import ru.itis.reflex.services.interfaces.UserService;
import ru.itis.reflex.util.StatsDataFormatConverter;


@RestController
public class GraphDataController {

    private final MoodDataService moodDataService;
    private final UserService userService;

    @Autowired
    public GraphDataController(MoodDataService moodDataService, UserService userService) {
        this.moodDataService = moodDataService;
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/stats_data_ajax")
    public ResponseEntity<?> getDataViaAjax() {

        User currentUser = userService.getUser(new Long(100)); // for test

        JSONObject jsonData = StatsDataFormatConverter.convertToChartDataFormat(
                moodDataService.getUserMoodData(userService.getUser(new Long(100))));

        return ResponseEntity.ok(jsonData.toString());
    }

}
