<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script type="text/javascript" src="js/Chart.bundle.js"></script>
    <script type="text/javascript" src="js/chart_util.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>

    <link href="/css/custom.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
    <meta charset="UTF-8">

    <title>Re:Flex</title>
</head>
<body>



<div class="navbar navbar-light d-flex flex-column flex-md-row p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">Re:Flex</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/signUp"  style="margin-left: 16px;">SignUp</a>
    </nav>
</div>

<div class="div-signin">
<form class="form-signin" action="/login" method="post">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input name="login" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <#if RequestParameters.error??>
        <div class="alert alert-danger" role="alert">
            Неправильный логин/пароль
        </div>
    </#if>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-outline-dark btn-block" type="submit">Sign in</button>
</form>
</div>

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


</body>
</html>