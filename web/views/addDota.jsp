<%--
  Created by IntelliJ IDEA.
  User: sevak
  Date: 31.05.2019
  Time: 4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-tester-select: none;
            -moz-tester-select: none;
            -ms-tester-select: none;
            tester-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        /* Show it is fixed to the top */
        body {
            min-height: 75rem;
            padding-top: 4.5rem;
        }
    </style>
</head>
<body>
<main role="main" class="container">
    <form name="dota" id="dota" class="form form-inline" method="post">
        <input type="hidden" name="htmlFormName"
               value="dota"/> <%--Для определения из какой формы был клик, req.getParameterValues("htmlFormName")--%>
        <label>
            <input name="name" type="text" class="form-control" placeholder="DotaName" required>
        </label>
        <label>
            <input name="quality" type="text" class="form-control" placeholder="DotaQuality" required>
        </label>
        <label>
            <input name="cost" type="text" class="form-control" placeholder="DotaCost" required>
        </label>
        <label>
            <input name="rarity" type="text" class="form-control" placeholder="DotaRarity" required>
        </label>
        <label>
            <input name="hero" type="text" class="form-control" placeholder="DotaHero" required>
        </label>
        <label>
            <input name="itemType" type="text" class="form-control" placeholder="DotaItemType" required>
        </label>
        <button name="dotasubmit" type="submit" value="active" class="btn btn-primary">Add contact</button>
    </form>
    <br>


    <%--<table class="table table-striped table-hover">--%>
    <%--<thead class="thead-dark">--%>
    <%--<tr>--%>
    <%--<th scope="col">#</th>--%>
    <%--<th scope="col">Name</th>--%>
    <%--<th scope="col">Quality</th>--%>
    <%--<th scope="col">Cost</th>--%>
    <%--<th scope="col">Rarity</th>--%>
    <%--<th scope="col">Hero</th>--%>
    <%--<th scope="col">ItemType</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>
    <%--<tr th:each="dotaItem:${dotaItems}">--%>
    <%--<th th:text="${dotaItem.id}"></th>--%>
    <%--<td th:text="${dotaItem.name}"></td>--%>
    <%--<td th:text="${dotaItem.quality}"></td>--%>
    <%--<td th:text="${dotaItem.cost}"></td>--%>
    <%--<td th:text="${dotaItem.rarity}"></td>--%>
    <%--<td th:text="${dotaItem.hero}"></td>--%>
    <%--<td th:text="${dotaItem.itemType}"></td>--%>
    <%--</tr>--%>
    <%--</tbody>--%>
    <%--</table>--%>
</main>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
