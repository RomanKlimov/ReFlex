package ru.itis.reflex.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import ru.itis.reflex.models.GraphPointData;
import ru.itis.reflex.models.MoodData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class StatsDataFormatConverter {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static JSONObject convertToChartDataFormat(List<MoodData> moodDataList){

        List<GraphPointData> morningPointData = new ArrayList<>();
        List<GraphPointData> eveningPointData = new ArrayList<>();

        for (MoodData moodData: moodDataList) {

            morningPointData.add(new GraphPointData(
                    moodData.getMorningValue(),
                    sdf.format(moodData.getDate())
            ));

            eveningPointData.add(new GraphPointData(
                    moodData.getEveningValue(),
                    sdf.format(moodData.getDate())
            ));
        }

        JSONObject j = new JSONObject();
        j.put("morningData", morningPointData);
        j.put("eveningData", eveningPointData);

        return j;
    }
}
