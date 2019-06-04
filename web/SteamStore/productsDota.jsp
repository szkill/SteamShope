<%@ page import="steamstore.json.model.DotaItem" %>
<%@ page import="java.util.LinkedList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sevak
  Date: 31.05.2029
  Time: 6:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

  DotaItem maItem1 = new DotaItem(10, "Dragonclaw Hook", "Standert", 150, "Immortal", "Pudge", "Оружие");
  DotaItem maItem2 = new DotaItem(10, "Crux of Perplex", "Standert", 150, "Immortal", "Rubick", "Оружие");
  LinkedList<DotaItem> base = new LinkedList<>();
  base.add(maItem1);
  base.add(maItem2);
  base.add(maItem2);
  base.add(maItem1);
  base.add(maItem2);
  base.add(maItem1);

  LinkedList<DotaItem> tripleItem = null;
  LinkedList<LinkedList<DotaItem>> allitems = new LinkedList<LinkedList<DotaItem>>();


  int i = 0;
  for (DotaItem elem :
          base) {
    if((i % 3) == 0)
    {
      tripleItem = new LinkedList<DotaItem>();
      allitems.add(tripleItem);
    }
    tripleItem.add(elem);

    i++;
  }

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

  pageContext.setAttribute("dotaitems3", allitems);

  String[] people = new String[]{"Tom", "Bob", "Sam"};
  String header = "Users list";
%>

<html lang="en" xmlns:c="http://java.sun.com/jsf/composite">

<head>
  <title>SteamStore</title>
  <meta charset="utf-8">




  <link href="SteamStore/css/style.css" rel='stylesheet' type='text/css'/>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
    integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">







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
          <a class="nav-link" href="index">Главная</a>
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
    </ul>
  </nav>







  <div class="row">

    <div class="col-sm-2 leftfilter">

      <nav class="navbar navbar-expand-sm navbar-light leftfilterpadding">
        <p class="selectiontitle">
          Игра 
        </p>
        <ul class="navbar-nav">
          <li class="nav-item active">
            <a class="nav-link" href="#"><img class="img-fluid rounded" id = "imgactive"
                src="https://steamcdn-a.akamaihd.net/steamcommunity/public/images/apps/570/0bbb630d63262dd66d2fdd0f7d37e8661a410075.jpg"></a>
          </li>
<%--          <li class="nav-item">--%>
<%--            <a class="nav-link" href="productsCs.jsp"><img class="img-fluid rounded"--%>
<%--                                                           src="https://steamcdn-a.akamaihd.net/steamcommunity/public/images/apps/730/69f7ebe2735c366c65c0b33dae00e12dc40edbe4.jpg"></a>--%>
<%--          </li>--%>
        </ul>
      </nav>


      <div class="selectionblock leftfilterpadding">
        <form class="form-inline">
          <input class="form-control mr-sm-2" type="search" placeholder="Поиск по названию" aria-label="Search">
          <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Поиск</button>
        </form>
      </div>


      <div class="selectiontitle leftfilterpadding">
        Цена
      </div>
      <div class="selectionblock leftfilterpadding">
        <form class="form-inline">
          От
          <input class="form-control mr-sm-2 priceformcontrol" type="search" placeholder="0" aria-label="Search">
          До
          <input class="form-control mr-sm-2 priceformcontrol" type="search" placeholder="5000" aria-label="Search">
        </form>
      </div>


      <div class="selectiontitle leftfilterpadding">
        Раритетность
      </div>
      <div class="selectionblock leftfilterpadding">
        <select class="custom-select">
          <option selected>Все</option>
          <option value="1">Common</option>
          <option value="2">Uncommon</option>
          <option value="3">Rare</option>
          <option value="4">Mythical</option>
          <option value="5">Legendary</option>
          <option value="6">Immortal</option>
          <option value="7">Arcana</option>
        </select>
      </div>

      <div class="selectiontitle leftfilterpadding">
        Качество
      </div>
      <div class="selectionblock leftfilterpadding">
        <select class="custom-select">
          <option selected>Все</option>
          <option value="1">Standart</option>
          <option value="2">Exalted</option>
          <option value="3">Genuine</option>
          <option value="4">Inscribed</option>
          <option value="5">Infused</option>
        </select>
      </div>


      <div class="selectiontitle leftfilterpadding">
        Герои
      </div>
      <div class="selectionblock leftfilterpadding">
        <select class="custom-select">
          <option selected>Все</option>
          <option value="1">Pudge</option>
          <option value="2">Juggernaut</option>
          <option value="3">Rubick</option>
        </select>
      </div>

      <!-- <div class="selectiontitle" id="leftfilterpadding">
        Тип
      </div>
      <div class="selectionblock" id="leftfilterpadding">
        <select class="custom-select">
          <option selected>Все</option>
          <option value="1">One</option>
          <option value="2">Two</option>
          <option value="3">Three</option>
        </select>
      </div> -->

    </div>





    <div class="col-sm-10">
      <div class="container">
        <br>
        <c:forEach var="item3" items="${dotaitems3}">

          <div class="row">

            <c:forEach var="item" items="${item3}">

              <div class="col-sm-4">
                <div class="card bg-dark text-white">
                  <img class="card-img-top"
                       src=<c:out value="${item.getUrl()}"/>
                       alt="Card image" style="width:100%">
                  <div class="card-body">
                    <h4 class="card-title"><c:out value="${item.getName()}"/></h4>
                    <p class="card-text">Используется: <c:out value="${item.getHero()}"/>
                      <br>Раритетность: <c:out value="${item.getRarity()}"/>
                      <br>Качество: <c:out value="${item.getQuality()}"/>
                    </p>
                    <a href="https://steamcommunity.com/market/listings/570/Dragonclaw%20Hook" class="btn btn-primary stretched-link">Подробнее</a>
                  </div>
                </div>
              </div>

            </c:forEach>

          </div>
          <br>

        </c:forEach>
      </div>
      <br>
    </div>




<%--    <li><c:out value="${user}" /></li>--%>
<%--    <div class="col-sm-10">--%>
<%--      <div class="container">--%>
<%--        <br>--%>
<%--        <div class="row">--%>
<%--          <div class="col-sm-4">--%>
<%--            <div class="card bg-dark text-white">--%>
<%--              <img class="card-img-top"--%>
<%--                src="https://steamcommunity-a.akamaihd.net/economy/image/-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KW1Zwwo4NUX4oFJZEHLbXK9QlSPcUivB9aSQPAUuCq0vDAWFh4IBBYuIWtJAhr7PHHdSR94N2kk4XFlvahZurTlDoJ65Qni-2U997z0AW3-xY5YDqndYCXIw8_NF7V_AToyPCv28HpnWRhMA/360fx360f"--%>
<%--                alt="Card image" style="width:100%">--%>
<%--              <div class="card-body">--%>
<%--                <h4 class="card-title">Dragonclaw Hook</h4>--%>
<%--                <p class="card-text">Используется: Pudge<br>Раритетность: Immortal<br>Качество: Standart</p>--%>
<%--                <a href="https://steamcommunity.com/market/listings/570/Dragonclaw%20Hook" class="btn btn-primary stretched-link">Подробнее</a>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--          <div class="col-sm-4">--%>
<%--            <div class="card bg-dark text-white">--%>
<%--              <img class="card-img-top"--%>
<%--                src="https://steamcommunity-a.akamaihd.net/economy/image/-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KW1Zwwo4NUX4oFJZEHLbXK9QlSPcUgvBlUT0efRvau1sHSHFB1IhFEibm8Ow9lwczEcC9F6ZLkxNnZkaOlYL-BlDwAv5Iki7DFrI703layqRVvZW32IofGcAVoYViC5BHglmEC4zWT/360fx360f"--%>
<%--                alt="Card image" style="width:100%">--%>
<%--              <div class="card-body">--%>
<%--                <h4 class="card-title">The Magus Cypher</h4>--%>
<%--                <p class="card-text">Используется: Rubick<br>Раритетность: Arcana<br>Качество: Exalted</p>--%>
<%--                <a href="https://steamcommunity.com/market/listings/570/Exalted%20The%20Magus%20Cypher" class="btn btn-primary stretched-link">Подробнее</a>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--          <div class="col-sm-4">--%>
<%--            <div class="card bg-dark text-white">--%>
<%--              <img class="card-img-top"--%>
<%--                src="https://steamcommunity-a.akamaihd.net/economy/image/-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KW1Zwwo4NUX4oFJZEHLbXK9QlSPcU4vBxaSV7eRvG5mM7BUFx6JEtdo72iLhVu0ubcTjxQ7924lb-GluT_DKjFj2dUufp9i_vG8ML0iQLgrkBqZWHxJoaSelc6Z1-E_Afsx-jugJbp752cwHMxviVxty7fgVXp1qH0S4DD/360fx360f"--%>
<%--                alt="Card image" style="width:100%">--%>
<%--              <div class="card-body">--%>
<%--                <h4 class="card-title">Bladeform Legacy</h4>--%>
<%--                <p class="card-text">Используется: Juggernaut<br>Раритетность: Arcana<br>Качество: Exalted</p>--%>
<%--                <a href="https://steamcommunity.com/market/listings/570/Exalted%20Bladeform%20Legacy" class="btn btn-primary stretched-link">Подробнее</a>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--      <br>--%>
<%--    </div>--%>

  </div>





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

  <!--  
  <footer class="container-fluid text-center">
    <div class="container">
      <div class="col-md-14 our-st text-center ">
        <p>Steam Online Store Copyright</p>
      </div>
    </div>
  </footer>
  -->

  <!--  
    <footer class="container-fluid text-center">
    <p>Online Store Copyright</p>
  </footer>
  -->

</body>

</html>