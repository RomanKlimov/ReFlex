<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <script type="text/javascript" src="js/jquery.js"></script>
    </head>

    <body>
    <!--
Ideally these elements aren't created until it's confirmed that the
client supports video/camera, but for the sake of illustrating the
elements involved, they are created with markup (not JavaScript)
-->
    <button id="initialize">Initialize</button>
    <button id="update">Update</button>
    <#--<button id="delete_user_session">Delete user session</button>-->
    <h1 id="is_inside_text">Is user recognized: </h1>
    <h1 id="is_flexing_text">Is user flexing: </h1>
    <div style="visibility: hidden">
        <canvas id="canvas" width="640" height="480" ></canvas>
        <video id="video" width="640" height="480" autoplay ></video>
    </div>
    <script>
        // Grab elements, create settings, etc.
        var video = document.getElementById('video');

        // Get access to the camera!
        if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
            // Not adding `{ audio: true }` since we only want video now
            navigator.mediaDevices.getUserMedia({ video: true }).then(function(stream) {
                video.src = window.URL.createObjectURL(stream);
                video.play();
            });
        }
        // Elements for taking the snapshot
        var canvas = document.getElementById('canvas');
        var context = canvas.getContext('2d');
        var video = document.getElementById('video');
        var myImage;

        function convertCanvasToImage(canvas) {
            var jpegUrl0 = canvas.toDataURL("image/jpeg");
            var jpegUrl = jpegUrl0.replace(/^data:image\/(png|jpeg);base64,/, "");
            return jpegUrl;
        }


        // Trigger photo take
        document.getElementById("initialize").addEventListener("click", function() {
            var myFormData = new FormData();
            context.drawImage(video, 0, 0, 640, 480);
            jpegURL = convertCanvasToImage(canvas);
            var myBlob = base64ToBlob(jpegURL, 'image/jpeg');

            myFormData.append('myImage', myBlob);

            initialize(myFormData);
        });

//        document.getElementById("delete_user_session").addEventListener("click", function() {
//            end_user_session();
//        });


        document.getElementById("update").addEventListener("click", function() {
            var myFormData = new FormData();
            context.drawImage(video, 0, 0, 640, 480);
            jpegURL = convertCanvasToImage(canvas);
            var myBlob = base64ToBlob(jpegURL, 'image/jpeg');

            myFormData.append('myImage', myBlob);

            update(myFormData);
        });

        // This function is used to convert base64 encoding to mime type (blob)
        function base64ToBlob(base64, mime)
        {
            mime = mime || '';
            var sliceSize = 1024;
            var byteChars = window.atob(base64);
            var byteArrays = [];

            for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
                var slice = byteChars.slice(offset, offset + sliceSize);

                var byteNumbers = new Array(slice.length);
                for (var i = 0; i < slice.length; i++) {
                    byteNumbers[i] = slice.charCodeAt(i);
                }

                var byteArray = new Uint8Array(byteNumbers);

                byteArrays.push(byteArray);
            }

            return new Blob(byteArrays, {type: mime});
        }

        function initialize(myFormData) {
            $.ajax({
                url: "/initialize_fp",
                type: "POST",
                data: myFormData,
                // enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function () {
                    console.log("File uploaded for initialization")
                    // Handle upload success
//                    alert("File succesfully uploaded");
                },
                error: function () {
                    // Handle upload error
                    alert("File not uploaded ");
                }
            });
        } // function uploadFile

        function update(myFormData) {
            $.ajax({
                url: "/update_fp",
                type: "POST",
                data: myFormData,
                // enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    console.log("File for update uploaded. Results:");
                    console.log(data);
                    if (data[0]){
                        document.getElementById("is_inside_text").innerHTML = "Is user recognized: True"
                    } else {
                        document.getElementById("is_inside_text").innerHTML = "Is user recognized: False"
                    }
                    if (data[1]){
                        document.getElementById("is_flexing_text").innerHTML = "Is user flexing: True"
                    } else {
                        document.getElementById("is_flexing_text").innerHTML = "Is user flexing: False"
                    }
                    // Handle upload success
//                    alert("File succesfully uploaded");
                },
                error: function () {
                    // Handle upload error
                    alert("File not uploaded ");
                }
            });
        }

        function end_user_session() {
            $.ajax({
                url: "/update_fp",
                type: "GET",
                // enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function () {
                    // Handle upload success
                    alert("Users cache deleted");
                },
                error: function () {
                    // Handle upload error
                    alert("Error ");
                }
            });
        }

    </script>
    </body>
</html>
