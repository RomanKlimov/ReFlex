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
            var jpegUrl0 = canvas.toDataURL("image/jpeg");
            var jpegUrl = jpegUrl0.replace(/^data:image\/(png|jpeg);base64,/, "");
            return jpegUrl;
        }
        var myFormData = new FormData();

        // Trigger photo take
        document.getElementById("snap").addEventListener("click", function() {
            context.drawImage(video, 0, 0, 640, 480);
            jpegURL = convertCanvasToImage(canvas);
            var myBlob = base64ToBlob(jpegURL, 'image/jpeg');

            // var byteCharacters = atob(jpegURL);
            // var byteNumbers = new Array(byteCharacters.length);
            // for (var i = 0; i < byteCharacters.length; i++) {
            //     byteNumbers[i] = byteCharacters.charCodeAt(i);
            // }
            // var byteArray = new Uint8Array(byteNumbers);
            // var blob = new Blob([byteArray], {type: contentType});



            myFormData.append('myImage', myBlob);
            uploadFile(myFormData);
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

        function uploadFile(myFormData) {
            $.ajax({
                url: "/",
                type: "POST",
                data: myFormData,
                // enctype: 'multipart/form-data',
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
