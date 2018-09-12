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
    <video id="video" width="640" height="480" autoplay></video>
    <button id="snap">Snap Photo</button>
    <canvas id="canvas" width="640" height="480"></canvas>
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
            var image = new Image();
            image.src = canvas.toDataURL("image/png");
            return image;
        }
        var myFormData = new FormData();

        // Trigger photo take
        document.getElementById("snap").addEventListener("click", function() {
            context.drawImage(video, 0, 0, 640, 480);
            myImage = convertCanvasToImage(canvas);
            myFormData.append('myImage', myImage);
            uploadFile(myFormData);
        });

        function uploadFile(myFormData) {
            $.ajax({
                url: "/",
                type: "POST",
                data: myFormData,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function () {
                    // Handle upload success
                    alert("File succesfully uploaded");
                },
                error: function () {
                    // Handle upload error
                    alert("File not uploaded ");
                }
            });
        } // function uploadFile

    </script>
    </body>
</html>
