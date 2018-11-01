<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

    <head>
        <spring:url value="/resources/css/style.css" var="mainCss" />
        <spring:url value="/resources/js/script.js" var="mainJs" />
        <spring:url value="/resources/images/" var="mainImg" />
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" media="screen,projection" />
        <link rel="stylesheet" href="${mainCss}">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta charset="utf-8">
        <title></title>
    </head>

    <body>
        <div class="content">
            <div class="container">
                <div class="row center">
                    <div class="col s11" id="main_screen">
                        <div class="col l6 m6 s8">
                            <div class="exchange_rates">
                                <div class="card blue-grey darken-1">
                                    <div class="card-content white-text">
                                        <span class="card-title"><strong>Курсы валют</strong></span>
                                        <table id="currency_table">
                                            <c:forEach items="${cources}" var="cours">
                                                <tr>
                                                    <th><img src="${mainImg}${cours.getInfo().getPhoto()}" alt="" class="icon"></th>
                                                    <th><strong>${cours.getInfo().getName()}</strong>
                                                        <p class="currency-secondary-text">${cours.getCur_name()}</p>
                                                    </th>
                                                    <th><strong>Покупка</strong></th>
                                                    <th><strong>Продажа</strong></th>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td class="secondary-text"><strong>Курс НБУ</strong></td>
                                                    <td>  -  </td>
                                                    <td>${cours.getBuyNBUFormat()}</td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td class="secondary-text"><strong>Наличный курс</strong></td>
                                                    <td>${cours.getBuyPrivatFormat()}</td>
                                                    <td>${cours.getSellPrivatFormat()}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col l4 s8 m6 right">
                            <div class="entrance_form">
                                <form action="/" method="POST">
                                    <div class="card" id="entrance_card_box">
                                        <div id="card-buttons">
                                            <a class="toggle" href="#" id="user_entrance">Пользователю</a></li>
                                            <a class="toggle" href="#" id="admin_entrance">Администратору</a></li>
                                        </div>
                                        <div class="card-content teal lighten-5" id="user_form_box">
                                            <div class="container">
                                                <div class="row">
                                                    <img src="${mainImg}user.svg" alt="" class="icon left">
                                                    <div class="input-field col s10" id="login_box">
                                                        <input id="user_login" name="user_login" type="text" class="validate">
                                                        <label for="user_login">Логин</label>
                                                    </div>
                                                    <img src="${mainImg}lock.svg" alt="" class="icon left">
                                                    <div class="input-field col s10" id="password_box">
                                                        <input id="user_password" name="user_password" type="password" class="validate">
                                                        <label for="user_password">Пароль</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="center button_box">
                                                <div class="row">
                                                    <button type="submit" class="button white-text">Войти</button>
                                                    <div class="col s12" id="help_section">
                                                        <a href="#" class="text-underline left">Забыли пароль?</a>
                                                        <a href="#" class="text-underline right">Нужна помощь?</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-content teal lighten-5" id="admin_form_box">
                                            <div class="container">
                                                <div class="row">
                                                    <img src="${mainImg}user.svg" alt="" class="icon left">
                                                    <div class="input-field col s10" id="login_box">
                                                        <input id="admin_login" name="admin_login" type="text" class="validate">
                                                        <label for="admin_login">Логин</label>
                                                    </div>
                                                    <img src="${mainImg}lock.svg" alt="" class="icon left">
                                                    <div class="input-field col s10" id="password_box">
                                                        <input id="admin_password" name="admin_password" type="password" class="validate">
                                                        <label for="admin_password">Пароль</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="center button_box">
                                                <div class="row">
                                                    <button type="submit" class="button white-text">Войти</button>
                                                </div>
                                            </div>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>




    <!--JavaScript at end of body for optimized loading-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="${mainJs}"></script>
</body>

</html>

