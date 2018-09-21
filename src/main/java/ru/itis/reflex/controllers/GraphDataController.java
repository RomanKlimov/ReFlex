package ru.itis.reflex.controllers;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.reflex.models.ChartType;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.StatisticDataService;
import ru.itis.reflex.services.interfaces.UserService;
import ru.itis.reflex.util.StatsDataFormatConverter;


@RestController
public class GraphDataController {

    private final StatisticDataService statisticDataService;
    private final UserService userService;

    @Autowired
    public GraphDataController(StatisticDataService statisticDataService, UserService userService) {
        this.statisticDataService = statisticDataService;
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/stats_data_ajax")
    public ResponseEntity<?> getDataViaAjax(@RequestBody ChartType chartType) {

        User currentUser = userService.getUser(new Long(100)); // for test

        JSONObject jsonData;

        if (chartType.getDataType().equals("Tiredness")) {
            jsonData = StatsDataFormatConverter.convertTirednessToChartDataFormat(
                    statisticDataService.getUserTirednessData(currentUser));
        }
        else if (chartType.getDataType().equals("Posture")){
            jsonData = StatsDataFormatConverter.convertPostureToChartDataFormat(
                    statisticDataService.getUserPostureData(currentUser));
        }
        else {
            jsonData = StatsDataFormatConverter.convertMoodToChartDataFormat(
                    statisticDataService.getUserMoodData(currentUser));
        }

        return ResponseEntity.ok(jsonData.toString());
    }

}
