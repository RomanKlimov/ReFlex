<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <title>User</title>
    <style>
        .slider {
            -webkit-appearance: none;
            width: 100%;
            height: 15px;
            border-radius: 5px;
            background: #d3d3d3;
            outline: none;
            opacity: 0.7;
            -webkit-transition: .2s;
            transition: opacity .2s;
        }

        .slider::-webkit-slider-thumb {
            -webkit-appearance: none;
            appearance: none;
            width: 25px;
            height: 25px;
            border-radius: 50%;
            background: #4CAF50;
            cursor: pointer;
        }

        .slider::-moz-range-thumb {
            width: 25px;
            height: 25px;
            border-radius: 50%;
            background: #4CAF50;
            cursor: pointer;
        }
    </style>
</head>
<body>
<input value="${isSameDay?c}" id="isSameDay" hidden />

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal2">
    Logout
</button>

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


<script src="https://code.jquery.com/jquery-3.1.1.min.js">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
    var isSameDay = document.getElementById("isSameDay").value;
    console.log(isSameDay);
    if (isSameDay === "false") {
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
                        window.location.href = '/';
                    }

        });
    }

</script>
</body>
</html>