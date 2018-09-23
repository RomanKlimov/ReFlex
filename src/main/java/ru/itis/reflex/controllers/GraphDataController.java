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
import ru.itis.reflex.models.CompanyStatsChartType;
import ru.itis.reflex.models.Department;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.*;
import ru.itis.reflex.util.StatsDataFormatConverter;


@RestController
public class GraphDataController {

    private final StatisticDataService statisticDataService;
    private final UserService userService;
    private final AuthService authService;
    private final DepartmentService departmentService;

    @Autowired
    public GraphDataController(StatisticDataService statisticDataService,
                               UserService userService,
                               AuthService authService,
                               DepartmentService departmentService) {
        this.statisticDataService = statisticDataService;
        this.userService = userService;
        this.authService = authService;
        this.departmentService = departmentService;
    }

    @ResponseBody
    @PostMapping("/profile_ajax")
    public ResponseEntity<?> getUserDataViaAjax(@RequestBody ChartType chartType, Authentication authentication) {

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


    @ResponseBody
    @PostMapping("/company_stats_ajax")
    public ResponseEntity<?> getCompanyDataViaAjax(@RequestBody CompanyStatsChartType chartType, Authentication authentication) {

        //User currentUser = userService.getUser(new Long(4)); // for test
        //User currentUser = authService.getUserByAuthentication(authentication);
        Department department = departmentService.getDepartment(chartType.getDepartmentId()); //for test

        JSONObject jsonData;

        if (chartType.getDataType().equals("Tiredness")) {
            jsonData = StatsDataFormatConverter.convertAvgToChartDataFormat(
                    statisticDataService.getAvgTirednessData(department, chartType.getTimeType()));

        } else if (chartType.getDataType().equals("Posture")) {
            jsonData = StatsDataFormatConverter.convertPostureAvgToChartDataFormat(
               statisticDataService.getAvgPostureData(department, chartType.getTimeType()));
        }
        else {
            jsonData = StatsDataFormatConverter.convertAvgToChartDataFormat(
                    statisticDataService.getAvgMoodData(department, chartType.getTimeType()));
        }


        return ResponseEntity.ok(jsonData.toString());
    }

}
