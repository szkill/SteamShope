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
    Boolean True = true;
    Boolean False = false;

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
<html>
<head>
    <title>Регистрация</title>

    <link href="SteamStore/css/style.css" rel='stylesheet' type='text/css'/>
    <%--    <style><%@include file="/WEB-INF/css/style.css"%></style>--%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/evil-icons@1.9.0/assets/evil-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/evil-icons@1.9.0/assets/evil-icons.min.js"></script>


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


<%
    //    if (request.getAttribute("loginError") != null) {
//        out.println("<script type=\"text/javascript\">");
//        out.println("alert('This mail is already using.');");
//        out.println("</script>");
//    }
    if (request.getAttribute("loginError") != null) {
        out.println("<script>" +
                "$(document).ready(function () {" +
                "alert('This mail is already using.');" +
                "});" +
                "</script>");
    }
    if (request.getAttribute("mailError") != null) {
        out.println("<script>" +
                "$(document).ready(function () {" +
                "alert('" + request.getAttribute("mailError") + "');" +
                "});" +
                "</script>");
    }
    if (request.getAttribute("passError") != null) {
        out.println("<script>" +
                "$(document).ready(function () {" +
                "alert('" + request.getAttribute("passError") + "');" +
                "});" +
                "</script>");
    }

%>


<div class="jumbotron">
    <div class="container text-center">
        <h1>Steam Online Store</h1>
        <p>Mission, Vission & Values</p>
    </div>
</div>


<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

    <a class="navbar-brand" href="index.html">
        <img src="https://apollo-frankfurt.akamaized.net/v1/files/ds9qr67iexep-KZ/image;s=261x203" alt="logo"
             style="width:40px;">
    </a>


    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="index.html">Главная</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="productsDota.html">Предметы</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">О нас</a>
        </li>
    </ul>


    <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
            <a class="nav-link" href="#"><span class="fa fa-address-card fa-lg"></span> Ваш аккаунт</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#"><span class="fa fa-shopping-cart fa-lg"></span> Корзина</a>
        </li>
        <c:if test="${isAdmin.equals(True)}">
            <li class="nav-item">
                <a class="nav-link" href="adminpanel.html"><span class="fa fa-wrench  fa-lg"></span> Admin</a>
            </li>
        </c:if>
    </ul>
</nav>


<div class="container">
    <div class="main">

        <div class="registration">
            <div class="reg">
                <h2>Новый пользователь? <span> Создай аккаунт </span></h2>

                <div class="registration_form">

                    <form id="registration_form" method="post">
                        <div>
                            <label>
                                <input name="regName" placeholder="Имя:" type="text" tabindex="1" required autofocus>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input name="regSurname" placeholder="Фамилия:" type="text" tabindex="2" required
                                       autofocus>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input name="regMail" placeholder="Почта:" type="email" tabindex="3" required>
                            </label>
                        </div>

                        <div>
                            <label>
                                <input name="regPass" placeholder="Пароль" type="password" tabindex="4" required>
                            </label>
                        </div>

                        <div>
                            <button name="regSubmit" type="submit" value="active" class="btn btn-primary">Создать
                                аккаунт
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <div class="log">
                <h2>Уже есть аккаунт?</h2>
                <div class="registration_form">
                    <form id="log" method="post">
                        <div>
                            <label>
                                <input name="logMail" placeholder="Почта:" type="email" tabindex="3" required>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input name="logPass" placeholder="Пароль" type="password" tabindex="4" required>
                            </label>
                        </div>

                        <div>
                            <button
                                    name="logSubmit" type="submit" value="active" class="btn btn-primary">Вход
                            </button>
                        </div>
                    </form>

                    <br><br>
                    <h2>Вы администратор?</h2>
                    <a href="adminlogin.html"><input type="submit" value="Вход администратора"></a>
                </div>

            </div>
            <div class="clearfix"></div>
        </div>

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
