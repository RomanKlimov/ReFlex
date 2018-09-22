package ru.itis.reflex.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import ru.itis.reflex.models.GraphPointData;
import ru.itis.reflex.models.MoodData;
import ru.itis.reflex.models.PostureData;
import ru.itis.reflex.models.TirednessData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class StatsDataFormatConverter {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static JSONObject convertMoodToChartDataFormat(List<MoodData> moodDataList){

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

    public static JSONObject convertTirednessToChartDataFormat(List<TirednessData> tirednessDataList){

        List<GraphPointData> morningPointData = new ArrayList<>();
        List<GraphPointData> eveningPointData = new ArrayList<>();

        for (TirednessData tirednessData: tirednessDataList) {

            morningPointData.add(new GraphPointData(
                    tirednessData.getMorningValue(),
                    sdf.format(tirednessData.getDate())
            ));

            eveningPointData.add(new GraphPointData(
                    tirednessData.getEveningValue(),
                    sdf.format(tirednessData.getDate())
            ));
        }

        JSONObject j = new JSONObject();
        j.put("morningData", morningPointData);
        j.put("eveningData", eveningPointData);

        return j;
    }


    public static JSONObject convertPostureToChartDataFormat(List<PostureData> postureDataList){

        List<GraphPointData> pointData = new ArrayList<>();

        for (PostureData  postureData:  postureDataList) {

            if (postureData.getSmoothNum() != 0) {

                pointData.add(new GraphPointData(
                        Math.round( (float)postureData.getSmoothNum()*100/(postureData.getFlexNum()+postureData.getSmoothNum())),
                        sdf.format(postureData.getDate())
                ));
            }
        }

        JSONObject j = new JSONObject();
        j.put("pointData", pointData);

        return j;
    }

    public static JSONObject convertAvgToChartDataFormat(List<AggregateResult> avgMoodData) {
        List<GraphPointData> morningPointData = new ArrayList<>();
        List<GraphPointData> eveningPointData = new ArrayList<>();

        for (AggregateResult aggregateResult: avgMoodData) {

            morningPointData.add(new GraphPointData(
                    (int) Math.round(aggregateResult.getMorningValue()),
                    sdf.format(aggregateResult.getDate())
            ));

            eveningPointData.add(new GraphPointData(
                    (int) Math.round(aggregateResult.getEveningValue()),
                    sdf.format(aggregateResult.getDate())
            ));
        }

        JSONObject j = new JSONObject();
        j.put("morningData", morningPointData);
        j.put("eveningData", eveningPointData);

        return j;
    }

    public static JSONObject convertPostureAvgToChartDataFormat(List<PostureAggregateResult> avgPostureData) {

        List<GraphPointData> pointData = new ArrayList<>();


        for (PostureAggregateResult aggregateResult: avgPostureData) {

            if (aggregateResult.getSmoothNum() != 0) {

                pointData.add(new GraphPointData(
                        (int) Math.round( (float)aggregateResult.getSmoothNum()*100/(aggregateResult.getFlexNum()+aggregateResult.getSmoothNum())),
                        sdf.format(aggregateResult.getDate())
                ));
            }
        }

        JSONObject j = new JSONObject();
        j.put("pointData", pointData);
        return j;
    }
}
