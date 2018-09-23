<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script type="text/javascript" src="js/Chart.bundle.js"></script>
    <script type="text/javascript" src="js/chart_util.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>

    <link href="/css/custom.css" rel="stylesheet">
    <meta charset="UTF-8">

    <title><@title/></title>
</head>
<body>



<div class="navbar navbar-light d-flex flex-column flex-md-row p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">Re:Flex</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <#if currentUser??>
            <#if currentUser.role == "USER">
        <button id="init_button" type="button" class="btn btn-outline-dark btn-sm" style="margin-right: 16px;">Set position</button>

        <input id="posture_track_switch" type="checkbox" checked data-toggle="toggle" data-onstyle="success" data-offstyle="danger" data-size="small" data-on="Posture tracking on" data-off="Posture tracking off" style="margin-left: 6px;">
        <input id="eyes_rest_switch" type="checkbox" checked data-toggle="toggle" data-onstyle="success" data-offstyle="danger" data-size="small" data-on="Eyes rest notice on" data-off="Eyes rest notice off" >
            </#if>
        <a class="p-2 text-dark" href="/logout"  style="margin-left: 16px;">Logout</a>
        <#else>
        <a class="p-2 text-dark" href="/signUp"  style="margin-left: 16px;">SignUp</a>
        <a class="p-2 text-dark" href="/login">Login</a>
        </#if>

    </nav>
</div>

<@content/>


<footer class="footer pt-4 my-md-5 pt-md-5 border-top"">
    <div class="container">
        <span class="text-muted">Digital SuperHero Hackdays © 2018 |
        <a href="#"> Re:Mind </a>
        </span>
    </div>
</footer>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<script>
    feather.replace()
</script>

<#if scripts??>
    <@scripts/>
</#if>

</body>
</html>