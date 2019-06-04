<%--
  Created by IntelliJ IDEA.
  User: ArtemPC
  Date: 04.06.2019
  Time: 1:42
  To change this template use File | Settings | File Templates.
--%>

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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShoppingCart</title>

    <link href="SteamStore/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="SteamStore/css/cart.css" rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/evil-icons@1.9.0/assets/evil-icons.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script>
        $(document).ready(function () {

            $("#cart").on("click", function () {
                $(".shopping-cart").fadeToggle("fast");
            });

        });
    </script>


</head>
<body>
<nav>
    <div class="container">
        <ul class="navbar-left">
            <li><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
        </ul> <!--end navbar-left -->

        <ul class="navbar-right">
            <li><a href="#" id="cart"><i class="fa fa-shopping-cart"></i> Cart <span class="badge">3</span></a></li>
        </ul> <!--end navbar-right -->
    </div> <!--end container -->
</nav>


bool isAdmin(string email);

<div class="container">
    <div class="shopping-cart" style="display: none;">
        <div class="shopping-cart-header">
            <i class="fa fa-shopping-cart cart-icon"></i><span class="badge">3</span>
            <div class="shopping-cart-total">
                <span class="lighter-text">Total:</span>
                <span class="main-color-text">$2,229.97</span>
            </div>
        </div> <!--end shopping-cart-header -->

        <ul class="shopping-cart-items">
            <li class="clearfix">
                <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/cart-item1.jpg" alt="item1"/>
                <span class="item-name">Sony DSC-RX100M III</span>
                <span class="item-price">$849.99</span>
                <span class="item-quantity">Quantity: 01</span>
            </li>

            <li class="clearfix">
                <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/cart-item2.jpg" alt="item1"/>
                <span class="item-name">KS Automatic Mechanic...</span>
                <span class="item-price">$1,249.99</span>
                <span class="item-quantity">Quantity: 01</span>
            </li>

            <li class="clearfix">
                <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/cart-item3.jpg" alt="item1"/>
                <span class="item-name">Kindle, 6" Glare-Free To...</span>
                <span class="item-price">$129.99</span>
                <span class="item-quantity">Quantity: 01</span>
            </li>
        </ul>

        <a href="#" class="button">Checkout</a>
    </div> <!--end shopping-cart -->
</div> <!--end container -->
</body>
</html>
