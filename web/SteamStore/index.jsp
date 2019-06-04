<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sevak
  Date: 31.05.2019
  Time: 6:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Boolean isLog = (Boolean) session.getAttribute("isLog");
    if (isLog == null) {
        isLog = false;
        session.setAttribute("isLog", isLog);
    }

    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if (isAdmin == null) {
        isAdmin = false;
        session.setAttribute("isAdmin", isAdmin);
    }


    String[] people = new String[]{"Tom", "Bob", "Sam"};
    String header = "Users list";
%>




<%--
  Created by IntelliJ IDEA.
  User: sevak
  Date: 04.06.2019
  Time: 4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SteamStore</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/evil-icons@1.9.0/assets/evil-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/evil-icons@1.9.0/assets/evil-icons.min.js"></script>

    <link href="css/style.css" rel='stylesheet' type='text/css' />

    <style>
        /* Remove the navbar's default rounded borders and increase the bottom margin */
        .navbar {
            margin-bottom: 50px;
            border-radius: 0;
        }

        /* Remove the jumbotron's default bottom margin */
        .jumbotron {
            background: url(https://i.giphy.com/media/xUOwG4onOFOY5ACDks/giphy.webp);
            margin-bottom: 0;
        }


        /* Add a gray background color and some padding to the footer */
        footer {
            background-color: #f2f2f2;
            padding: 25px;
        }
    </style>
</head>
<body>
<div class="jumbotron">
    <div class="container text-center">
        <h1>Steam Online Store</h1>
        <p>Mission, Vission & Values</p>
    </div>
</div>




<!--bg-dark navbar-dark-->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand/logo -->
    <a class="navbar-brand" href="#">
        <img src="https://apollo-frankfurt.akamaized.net/v1/files/ds9qr67iexep-KZ/image;s=261x203" alt="logo"
             style="width:40px;">
    </a>

    <!-- Left -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link active" href="#">Главная</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="filter">Предметы</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">О нас</a>
        </li>
    </ul>

    <!-- Right -->
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" href="reg"><span class="fa fa-address-card fa-lg"></span> Ваш аккаунт</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#"><span class="fa fa-shopping-cart fa-lg"></span> Корзина</a>
        </li>
        <c:if test="${isAdmin.equals(true)}">
        <li class="nav-item">
            <a class="nav-link" href="adminpanel"><span class="fa fa-wrench  fa-lg"></span> Admin</a>
        </li>
        </c:if>
</nav>


<div class="container">
    <h4>Популярные предметы</h4><br>
    <div class="row">
        <%

        out.print(" <div class=\"col-sm-4 d-flex\">\n" +
                "            <div class=\"card bg-dark text-white\">\n" +
                "                <img class=\"card-img-top\"\n" +
                "                     src=\"https://steamcommunity-a.akamaihd.net/economy/image/-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpovbSsLQJf2PLacDBA5ciJlY20mvbmMbfUqW1Q7MBOhuDG_ZjKhFWmrBZyNmynJNCRdQdtMlyBqwW2lbq7g8Po6ZnLwCM17yhxsX2JlxXkgEsabPsv26LDJQinCA/360fx360f\"\n" +
                "                     alt=\"Card image\" style=\"width:100%\">\n" +
                "                <div class=\"card-body\">\n" +
                "                    <h4 class=\"card-title\">★ Керамбит | Мраморный градиент</h4>\n" +
                "                    <p class=\"card-text\">Качество: Factory New<br>Раритетность: Тайное</p>\n" +
                "                    <a href=\"https://steamcommunity.com/market/listings/730/%E2%98%85%20Karambit%20%7C%20Marble%20Fade%20(Factory%20New)\"\n" +
                "                       class=\"btn btn-primary stretched-link\">Подробнее</a>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>");
        %>
        <div class="col-sm-4 d-flex">
            <div class="card bg-dark text-white">
                <img class="card-img-top"
                     src="https://steamcommunity-a.akamaihd.net/economy/image/-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpovbSsLQJf2PLacDBA5ciJlY20mvbmMbfUqW1Q7MBOhuDG_ZjKhFWmrBZyNmynJNCRdQdtMlyBqwW2lbq7g8Po6ZnLwCM17yhxsX2JlxXkgEsabPsv26LDJQinCA/360fx360f"
                     alt="Card image" style="width:100%">
                <div class="card-body">
                    <h4 class="card-title">★ Керамбит | Мраморный градиент</h4>
                    <p class="card-text">Качество: Factory New<br>Раритетность: Тайное</p>

                </div>
            </div>
        </div>


    </div>
    <br>
    <div class="row">
        <div class="col-sm-4">
            <div class="card bg-dark text-white">
                <img class="card-img-top"
                     src="https://steamcommunity-a.akamaihd.net/economy/image/-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KW1Zwwo4NUX4oFJZEHLbXK9QlSPcUivB9aSQPAUuCq0vDAWFh4IBBYuIWtJAhr7PHHdSR94N2kk4XFlvahZurTlDoJ65Qni-2U997z0AW3-xY5YDqndYCXIw8_NF7V_AToyPCv28HpnWRhMA/360fx360f"
                     alt="Card image" style="width:100%">
                <div class="card-body">
                    <h4 class="card-title">Dragonclaw Hook</h4>
                    <p class="card-text">Используется: Pudge<br>Раритетность: Immortal<br>Качество: Standart</p>
                    <a href="https://steamcommunity.com/market/listings/570/Dragonclaw%20Hook"
                       class="btn btn-primary stretched-link">Подробнее</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card bg-dark text-white">
                <img class="card-img-top"
                     src="https://steamcommunity-a.akamaihd.net/economy/image/-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KW1Zwwo4NUX4oFJZEHLbXK9QlSPcUgvBlUT0efRvau1sHSHFB1IhFEibm8Ow9lwczEcC9F6ZLkxNnZkaOlYL-BlDwAv5Iki7DFrI703layqRVvZW32IofGcAVoYViC5BHglmEC4zWT/360fx360f"
                     alt="Card image" style="width:100%">
                <div class="card-body">
                    <h4 class="card-title">The Magus Cypher</h4>
                    <p class="card-text">Используется: Rubick<br>Раритетность: Arcana<br>Качество: Exalted</p>
                    <a href="https://steamcommunity.com/market/listings/570/Exalted%20The%20Magus%20Cypher"
                       class="btn btn-primary stretched-link">Подробнее</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card bg-dark text-white">
                <img class="card-img-top"
                     src="https://steamcommunity-a.akamaihd.net/economy/image/-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KW1Zwwo4NUX4oFJZEHLbXK9QlSPcU4vBxaSV7eRvG5mM7BUFx6JEtdo72iLhVu0ubcTjxQ7924lb-GluT_DKjFj2dUufp9i_vG8ML0iQLgrkBqZWHxJoaSelc6Z1-E_Afsx-jugJbp752cwHMxviVxty7fgVXp1qH0S4DD/360fx360f"
                     alt="Card image" style="width:100%">
                <div class="card-body">
                    <h4 class="card-title">Bladeform Legacy</h4>
                    <p class="card-text">Используется: Juggernaut<br>Раритетность: Arcana<br>Качество: Exalted</p>
                    <a href="https://steamcommunity.com/market/listings/570/Exalted%20Bladeform%20Legacy"
                       class="btn btn-primary stretched-link">Подробнее</a>
                </div>
            </div>
        </div>
    </div>
</div><br>



<footer class="container-fluid text-center">
    <div class="container">

        <div class="col-md-14 our-st text-center ">
            <div class="clearfix "> </div>
            <li><i class="add"> </i>Санкт-Петербург, ЛЭТИ</li>
            <li><i class="phone"> </i>+7 (904) 555-18-96</li>
            <li><a href="mailto:info@example.com"><i class="mail"> </i>steam-shop@mail.ru</a></li>

        </div>

    </div>
</footer>

</body>
</html>
