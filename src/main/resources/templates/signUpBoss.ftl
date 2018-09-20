<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Re:Flex</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/css/style.css" rel="stylesheet">
    <link href="static/css/font-awesome.css" rel="stylesheet">
</head>
<body>
<div class="signup-box">
    <div class="card">
        <div class="body">
            <form id="sign_up" action="/signUpBoss" method="POST" novalidate="novalidate">

                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">Имя</i>
                        </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="name" placeholder="Имя " value="${bossRegistrationForm.name!}" required="" autofocus="" aria-required="true">
                    </div>
                </div>
                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">email</i>
                        </span>
                    <div class="form-line">
                        <input type="email" class="form-control" name="email" placeholder="e-mail" value="${bossRegistrationForm.email!}" required="" aria-required="true">
                    </div>
                </div>
                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">Отдел</i>
                        </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="department" placeholder="Название отдела" value="${bossRegistrationForm.department!}" required="" aria-required="true">
                    </div>
                </div>

                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">Ключ регистрации</i>
                        </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="key" placeholder="Ваш ключ регистрации" value="${bossRegistrationForm.key!}" required="" aria-required="true">
                    </div>
                </div>

                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">Пароль</i>
                        </span>
                    <div class="form-line">
                        <input type="password" class="form-control validate-equalTo-blur" name="password" minlength="6" placeholder="Пароль" required="" aria-required="true" aria-invalid="false">
                    </div>
                </div>

                <#if error??>
                <div class="alert alert-danger" role="alert">
                    ${error!}
                </div>
                </#if>
                <button class="btn btn-block btn-lg bg-pink waves-effect" type="submit">Зарегистрироваться</button>

            </form>
        </div>
    </div>
</div>


</body>
</html>