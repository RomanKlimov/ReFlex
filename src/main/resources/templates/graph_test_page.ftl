<#include "base.ftl">
<#macro title>Re:Flex | Statistics</#macro>

<#macro content>
    <input value="${isSameDay?c}" id="isSameDay" hidden />


<!-- Modal morning -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>Rate your current mood, please</h6>
                <div class="slidecontainer">
                    <input type="range" min="1" max="10" value="5" class="slider" id="mood">
                </div>
                <h6>Rate your tiredness(10 - very tired)</h6>
                <div class="slidecontainer">
                    <input type="range" min="1" max="10" value="5" class="slider" id="tiredness">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save" data-dismiss="modal">Save changes</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal evening -->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>Rate your current mood, please</h6>
                <div class="slidecontainer">
                    <input type="range" min="1" max="10" value="5" class="slider" id="moodEv">
                </div>
                <h6>Rate your tiredness(10 - very tired)</h6>
                <div class="slidecontainer">
                    <input type="range" min="1" max="10" value="5" class="slider" id="tirednessEv">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="logout" data-dismiss="modal">Save changes</button>
            </div>
        </div>
    </div>
</div>



<div class="container">

<!--<div style="width:75%;">-->

    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-4 border-bottom">
        <h1 class="h2" id="headText">Mood statistics</h1>
        <div class="btn-toolbar mb-2 mb-md-0">


            <div class="btn-group btn-group-toggle" data-toggle="buttons">
                <label class="btn btn-outline-secondary active">
                    <input type="radio" name="chartData" value="Mood" autocomplete="on" checked> Mood
                </label>
                <label class="btn btn-outline-secondary">
                    <input type="radio" name="chartData" value="Tiredness" autocomplete="on"> Tiredness
                </label>
                <label class="btn btn-outline-secondary">
                    <input type="radio" name="chartData" value="Posture" autocomplete="on"> Posture
                </label>
            </div>

            <div class="btn-group btn-group-toggle" data-toggle="buttons" style="margin-left:10px;">
                <label class="btn btn-outline-secondary active">
                    <span data-feather="calendar"></span>
                    <input type="radio" name="chartTime" value="Week" autocomplete="on" checked> Week
                </label>
                <label class="btn btn-outline-secondary">
                    <input type="radio" name="chartTime" value="Month" autocomplete="on"> Month
                </label>


            <#if currentUser.role == "ADMIN">
            <#if company??>
                <select class="form-control" id="departmentsSelect" style="margin-left:10px;">
                    <#list company.departments as department>
                        <option value="${department.id}">${department.name} department</option>
                    </#list>
                </select>
            </#if>
            </#if>
                <!--
                <label class="btn btn-outline-secondary">
                    <input type="radio" name="chartTime" value="Year" autocomplete="on"> Year
                </label>
                -->
            </div>
            <!--

            <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
                <span data-feather="calendar"></span>
                This week
            </button>
                    -->
        </div>
    </div>


    <canvas id="statsChart"></canvas>

</div>
<!--</div>-->
</#macro>


<#macro scripts>
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
                display: false
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
        backgroundColor: window.chartColors.green_t,
        borderColor: window.chartColors.green,
        fill: true
    }, {
        label: 'Evening data',
        backgroundColor: window.chartColors.purple_t,
        borderColor: window.chartColors.purple,
        fill: true
    }];

    var dataset2 = [{
        label: 'Posture data',
        backgroundColor: window.chartColors.green_t,
        borderColor: window.chartColors.green,
    }];

    var ctx = document.getElementById('statsChart').getContext('2d');
    var statsChart = new Chart(ctx, config);
    var url = $(location).attr('pathname') + "_ajax";
    console.log(url);
    Chart.defaults.global.defaultFontSize = 14;

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
                $("#headText").text(chart_request["dataType"] + " statistics");


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
    };

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


        $('#posture_track_switch').change(function() {
            console.log('posture_track_switch ' + $(this).prop('checked'));
        });

        $('#eyes_rest_switch').change(function() {
            console.log('posture_track_switch ' + $(this).prop('checked'));
        });

        $('#init_button').on('click', function(event) {
            console.log('Init button');
        });
    });
</script>
<script>
    var isSameDay = document.getElementById("isSameDay").value;
    console.log(isSameDay);
    if (isSameDay === "true") {
        $('#exampleModal').modal('show');
    }

    var mood = document.getElementById("mood");
    var tiredness = document.getElementById("tiredness");
    document.getElementById("save").addEventListener("click", function () {
        saveValues(mood.value, tiredness.value);
    });
    var moodEv = document.getElementById("moodEv");
    var tirednessEv = document.getElementById("tirednessEv");
    document.getElementById("logout").addEventListener("click", function () {
        saveValuesEv(moodEv.value, tirednessEv.value);
    });


    function saveValues(mood, tiredness) {
        $.ajax({
            url: "/sliderValue",
            type: "POST",
            data: {mood:mood, tiredness:tiredness}
        });
    }

    function saveValuesEv(moodEv, tirednessEv) {
        $.ajax({
            url: "/sliderValueEv",
            type: "POST",
            data: {moodEv:moodEv, tirednessEv:tirednessEv},
            success:
                    function (response) {
                        window.location.href = '/login';
                    }

        });
    }

</script>
</#macro>