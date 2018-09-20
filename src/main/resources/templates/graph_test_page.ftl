<!DOCTYPE html>
<html lang="en">
<head>
    <script type="text/javascript" src="js/Chart.bundle.js"></script>
    <script type="text/javascript" src="js/chart_util.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <meta charset="UTF-8">
    <title>Статистика</title>
</head>
<body>

<div style="width:75%;">
<canvas id="statsChart"></canvas>

</div>


<script>

    // var MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

    // Chart configuration
    var config = {
        type: 'line',
        data: {
            //labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
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
                display: true,
                text: 'Mood statistic'
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
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    }
                }]
            }
        }
    };

   // var ctx = document.getElementById('myChart').getContext('2d');
  //  var myChart = new Chart(ctx, config);

    function ajax_submit() {

        $.ajax({
            async: false,
            type: "POST",
            url: "/stats_data_ajax",
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);

                //var labels = [];

                //for (var i = 0; i < data.points.length; i++) {
                //    labels.push(data.points[i].x)
                //}

               // statsChart.data.labels = labels;
                config.data.datasets[0].data = data.morningData;
                config.data.datasets[1].data = data.eveningData;
               // statsChart.update();

                var ctx = document.getElementById('statsChart').getContext('2d');
                var statsChart = new Chart(ctx, config);

            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        })
    }

    ajax_submit();

</script>


</body>
</html>