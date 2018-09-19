<!DOCTYPE html>
<html lang="en" class="gr__localhost" style="
    margin-left: 532px;
    position: fixed;"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Re:Flex</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/font-awesome.css" rel="stylesheet">
<body data-gr-c-s-loaded="true">
<h3 style="
    margin-left:  143px;
">Вход</h3>
<div>
    <form class="px-4 py-3" action="/login" method="post">
        <div class="form-group">
            <label for="exampleDropdownFormEmail1">Email адрес</label>
            <input type="email" name="login" class="form-control" id="exampleDropdownFormEmail1" placeholder="email@example.com">
        </div>
        <div class="form-group">
            <label for="exampleDropdownFormPassword1">Пароль</label>
            <input type="password" name="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="Пароль">
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="dropdownCheck">
            <label class="form-check-label" for="dropdownCheck">
                Запомнить меня
            </label>
        </div>
        <#if RequestParameters.error??>
            <div class="alert alert-danger" role="alert">
                Неправильный логин/пароль
            </div>
        </#if>
        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
    <div class="dropdown-divider"></div>
    <a class="dropdown-item" href="#">Впервые здесь? Зарегистрируйтесь!</a>
    <a class="dropdown-item" href="#">Забыли пароль?</a>
</div>

</body>
</html>