<%--
  Created by IntelliJ IDEA.
  User: sevak
  Date: 04.06.2019
  Time: 5:30
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


    <link rel='stylesheet' href="css/style.css" type='text/css'/>


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
            <a class="nav-link" href="index.html">Главная</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="#">Предметы</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">О нас</a>
        </li>
    </ul>

    <!-- Right -->
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" href="register.html"><span class="fa fa-address-card fa-lg"></span> Ваш аккаунт</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#"><span class="fa fa-shopping-cart fa-lg"></span> Корзина</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="adminpanel.html"><span class="fa fa-wrench  fa-lg"></span> Admin</a>
        </li>
    </ul>
</nav>


<div class="row">


    <div class="col-sm-2" id="leftfilter">

        <nav class="navbar navbar-expand-sm navbar-light" id="leftfilterpadding1">
            <p class="selectiontitle">
                Игра
            </p>
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#"><img class="img-fluid rounded" id="imgactive"
                                                      src="https://steamcdn-a.akamaihd.net/steamcommunity/public/images/apps/570/0bbb630d63262dd66d2fdd0f7d37e8661a410075.jpg"></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="productsCs.html"><img class="img-fluid rounded"
                                                                    src="https://steamcdn-a.akamaihd.net/steamcommunity/public/images/apps/730/69f7ebe2735c366c65c0b33dae00e12dc40edbe4.jpg"></a>
                </li>
            </ul>
        </nav>


        <div class="selectionblock" id="leftfilterpadding2">
            <form class="form-inline" method="post">
                <input name="input" class="form-control mr-sm-2" type="search" placeholder="Поиск по названию"
                       aria-label="Search">
                <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Поиск</button>
            </form>
        </div>

        <div class="selectiontitle" id="leftfilterpadding3">
            Цена
        </div>
        <div class="selectionblock" id="leftfilterpadding4">
            <form class="form-inline" method="post">
                От
                <input name = "minCost" class="form-control mr-sm-2 " type="submit" placeholder="0" aria-label="Search"
                       id="priceformcontrol1">
                До
                <input name="maxCost" class="form-control mr-sm-2 " type="submit" placeholder="25000" aria-label="Search"
                       id="priceformcontrol2">
            </form>
        </div>

        <br>
        <form method="post">
            <div class="form-group">
                <button
                        name="logSubmit" type="submit" value="active" class="btn btn-primary">Фильтровать
                </button>
                <br><br>

                <div class="selectiontitle" id="leftfilterpadding5">
                    Раритетность
                </div>

                <div class="selectionblock" id="leftfilterpadding6">
                    <select name="rarity" class="custom-select">
                        <option selected value="">Все</option>
                        <option value="Common">Common</option>
                        <option value="Uncommon">Uncommon</option>
                        <option value="Rare">Rare</option>
                        <option value="Mythical">Mythical</option>
                        <option value="Legendary">Legendary</option>
                        <option value="Immortal">Immortal</option>
                        <option value="Arcana">Arcana</option>
                    </select>

                </div>
            </div>


            <div class="selectiontitle" id="leftfilterpadding7">
                Качество
            </div>
            <div class="selectionblock" id="leftfilterpadding8">
                <select name="quality" class="custom-select">
                    <option selected value="">Все</option>
                    <option value="Standart">Standart</option>
                    <option value="NoStandart">NoStandart</option>
                    <option value="Exalted">Exalted</option>
                    <option value="Genuine">Genuine</option>
                    <option value="Inscribed">Inscribed</option>
                    <option value="Infused">Infused</option>
                </select>
            </div>


            <div class="selectiontitle" id="leftfilterpadding9">
                Герои
            </div>
            <div class="selectionblock" id="leftfilterpadding10">
                <select name="hero" class="custom-select">
                    <option value="" selected>Все</option>
                    <option value="Pudge">Pudge</option>
                    <option value="Juggernaut">Juggernaut</option>
                    <option value="Rubick">Rubick</option>
                </select>
            </div>

            <div class="selectiontitle" id="leftfilterpadding11">
                Тип
            </div>
            <div class="selectionblock" id="leftfilterpadding12">
                <select name="itemType" class="custom-select">
                    <option value="" selected>Все</option>
                    <option value="decoration">Decoration</option>
                </select>
            </div>
        </form>

    </div>


    <div class="col-sm-10">
        <div class="container">
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
                            <p class="card-text">Используется: Juggernaut<br>Раритетность: Arcana<br>Качество: Exalted
                            </p>
                            <a href="https://steamcommunity.com/market/listings/570/Exalted%20Bladeform%20Legacy"
                               class="btn btn-primary stretched-link">Подробнее</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
    </div>

</div>


<footer class="container-fluid text-center">
    <div class="container">

        <div class="col-md-14 our-st text-center ">
            <div class="clearfix "></div>
            <li><i class="add"> </i>Санкт-Петербург, ЛЭТИ</li>
            <li><i class="phone"> </i>+7 (904) 555-18-96</li>
            <li><a href="mailto:info@example.com"><i class="mail"> </i>steam-shop@mail.ru</a></li>

        </div>

    </div>
</footer>
</body>
</html>
