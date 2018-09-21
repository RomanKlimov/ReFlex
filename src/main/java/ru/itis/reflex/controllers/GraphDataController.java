package ru.itis.reflex.controllers;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.reflex.models.ChartType;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.AuthService;
import ru.itis.reflex.services.interfaces.StatisticDataService;
import ru.itis.reflex.services.interfaces.UserService;
import ru.itis.reflex.util.StatsDataFormatConverter;


@RestController
public class GraphDataController {

    private final StatisticDataService statisticDataService;
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public GraphDataController(StatisticDataService statisticDataService, UserService userService, AuthService authService) {
        this.statisticDataService = statisticDataService;
        this.userService = userService;
        this.authService = authService;
    }

    @ResponseBody
    @PostMapping("/stats_data_ajax")
    public ResponseEntity<?> getDataViaAjax(@RequestBody ChartType chartType, Authentication authentication) {

        //User currentUser = userService.getUser(new Long(4)); // for test
        User currentUser = authService.getUserByAuthentication(authentication);

        JSONObject jsonData;

        if (chartType.getDataType().equals("Tiredness")) {
            jsonData = StatsDataFormatConverter.convertTirednessToChartDataFormat(
                    statisticDataService.getUserTirednessData(currentUser, chartType.getTimeType()));
        }
        else if (chartType.getDataType().equals("Posture")){
            jsonData = StatsDataFormatConverter.convertPostureToChartDataFormat(
                    statisticDataService.getUserPostureData(currentUser, chartType.getTimeType()));
        }
        else {
            jsonData = StatsDataFormatConverter.convertMoodToChartDataFormat(
                    statisticDataService.getUserMoodData(currentUser, chartType.getTimeType()));
        }

        return ResponseEntity.ok(jsonData.toString());
    }

}
