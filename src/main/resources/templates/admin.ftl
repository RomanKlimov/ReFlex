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
<div>
    <form class="px-4 py-3" action="/keyAdmin" method="post">
        <div class="form-group">
            <label>Введите email адреса начальников через пробел</label>
            <input type="text" name="emails"  placeholder="email@example.com">
        </div>

        <#if RequestParameters.error??>
            <div class="alert alert-danger" role="alert">
                Ошибка
            </div>
        </#if>
        <button type="submit" class="btn btn-primary">Отправить</button>
    </form>
    <#if keys??>
        Заявки
        <#list keys as key>
            <li>${key.email}</li>
        <#else >
        </#list>
    </#if>
</div>

</body>
</html>