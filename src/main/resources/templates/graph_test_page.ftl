<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script type="text/javascript" src="js/Chart.bundle.js"></script>
    <script type="text/javascript" src="js/chart_util.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <meta charset="UTF-8">
    <title>Re:Flex</title>
</head>
<body>


<div style="width:75%;">

    <div class="btn-group btn-group-toggle" data-toggle="buttons">
        <label class="btn btn-light active">
            <input type="radio" name="chartData" value="Mood" autocomplete="on" checked> Mood
        </label>
        <label class="btn btn-light">
            <input type="radio" name="chartData" value="Tiredness" autocomplete="on"> Tiredness
        </label>
        <label class="btn btn-light">
            <input type="radio" name="chartData" value="Posture" autocomplete="on"> Posture
        </label>
    </div>
    <div class="btn-group btn-group-toggle" data-toggle="buttons">
        <label class="btn btn-light active">
            <input type="radio" name="chartTime" value="Week" autocomplete="on" checked> Week
        </label>
        <label class="btn btn-light">
            <input type="radio" name="chartTime" value="Month" autocomplete="on"> Month
        </label>
        <label class="btn btn-light">
            <input type="radio" name="chartTime" value="Year" autocomplete="on"> Year
        </label>
    </div>

    <#if company??>

    <select class="form-control" id="departmentsSelect">
        <#list company.departments as department>
            <option value="${department.id}">${department.name}</option>
        </#list>
    </select>
    </#if>
    <canvas id="statsChart"></canvas>

</div>


<script>

    // var MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

    // Chart configuration
    var config = {
        type: 'line',
        data: {
            datasets: [{
                label: 'Morning data',
                backgroundColor: window.chartColors.red,
                borderColor: window.chartColors.red,
                fill: false
            }, {
                label: 'Evening data',
                backgroundColor: window.chartColors.blue,
                borderColor: window.chartColors.blue,
                fill: false
            }]
        },
        options: {
            responsive: true,
            title: {
                text: 'Mood statistics',
                display: true
            },
            tooltips: {
                mode: 'index',
                intersect: false
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Day'
                    },
                    type: 'time',
                    time: {
                        unit: 'day'
                    },
                    ticks:{
                        source: 'auto'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    },
                    ticks: {
                        max: 10,
                        min: 0
                    }
                }]
            }
        }
    };

    var dataset1 = [{
        label: 'Morning data',
        backgroundColor: window.chartColors.red,
        borderColor: window.chartColors.red,
        fill: false
    }, {
        label: 'Evening data',
        backgroundColor: window.chartColors.blue,
        borderColor: window.chartColors.blue,
        fill: false
    }];

    var dataset2 = [{
        label: 'Posture data',
        backgroundColor: window.chartColors.red,
        borderColor: window.chartColors.red,
        fill: false
    }];

    var ctx = document.getElementById('statsChart').getContext('2d');
    var statsChart = new Chart(ctx, config);
    var url = $(location).attr('pathname') + "_ajax";
    console.log(url);

    function ajax_submit() {

        var chart_request = {
            "dataType": $('input[name=chartData]:checked').val(),
            "timeType": $('input[name=chartTime]:checked').val()
        };

        <#if company??>
            chart_request["departmentId"] =  $('#departmentsSelect').val();
        </#if>

        console.log(chart_request);

        $.ajax({
            async: false,
            type: "POST",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            data: JSON.stringify(chart_request),
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);

                //var labels = [];

                //for (var i = 0; i < data.points.length; i++) {
                //    labels.push(data.points[i].x)
                //}

               // statsChart.data.labels = labels;

                config.options.title.text = chart_request["dataType"] + " statistics";
                if (chart_request["timeType"] === "Year") {
                    config.options.scales.xAxes[0].time.unit = 'month';
                    config.options.scales.xAxes[0].scaleLabel.labelString = 'Month';
                }
                else {
                    config.options.scales.xAxes[0].time.unit = 'day';
                    config.options.scales.xAxes[0].scaleLabel.labelString = 'Day';
                }

                if (chart_request["dataType"] === 'Posture'){
                    config.data.datasets = dataset2;
                    config.data.datasets[0].data = data.pointData;
                    config.options.scales.yAxes[0].ticks.max = 100;
                    statsChart.update();
                }
                else {
                    config.data.datasets = dataset1;
                    config.data.datasets[0].data = data.morningData;
                    config.data.datasets[1].data = data.eveningData;
                    config.options.scales.yAxes[0].ticks.max = 10;
                }

                statsChart.update();

            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }

    $(document).ready(function() {

        ajax_submit();

        $('input[name=chartData]').change(function () {
            ajax_submit();
        });

        $('input[name=chartTime]').change(function () {
            ajax_submit();
        });
        $('#departmentsSelect').change(function() {
            console.log($(this).val());
            ajax_submit();
        });
    });
</script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>