<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

    <head>
        <spring:url value="/resources/css/style.css" var="mainCss" />
        <spring:url value="/resources/css/service-style.css" var="serviceCss" />
        <spring:url value="/resources/css/admin-style.css" var="adminCss" />
        <spring:url value="/resources/js/script.js" var="mainJs" />
        <spring:url value="/resources/images/" var="mainImg" />
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" media="screen,projection" />
        <link rel="stylesheet" href="${mainCss}">
        <link rel="stylesheet" href="${serviceCss}">
        <link rel="stylesheet" href="${adminCss}">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta charset="utf-8">
        <title></title>
    </head>

    <body>
        <div class="container">
            <div class="exit">
                <span id="client_name"><c:out value="${name}"/></span>
                <img src="${mainImg}/user.svg" alt="" class="icon">
                <a href="/"><img src="${mainImg}/exit.svg" alt="" class="icon_exit"></a>
            </div>
            <div class="service_block left">
                <div class="service_left_part left block">
                    <div class="content">
                        <div class="title">
                            <img src="${mainImg}/news-report.svg" alt="" class="icon">
                            <span class="title_text">Журнал транзакций</span>
                        </div>
                        <div class="transaction_select_block right">
                            <select class="transaction_select">
                                <c:if test="${orders.size()<5}">
                                    <option value="1" selected>Последние 5 транзакций</option>
                                </c:if>
                                <c:if test="${orders.size()>=5}}">
                                    <option value="2">Последние 10 транзакций</option>
                                </c:if>
                            </select>
                        </div>
                        <div class="transaction_table_block">
                            <table>
                                <tr>
                                    <th>Дата</th>
                                    <th>Кол-во</th>
                                    <th>Курс</th>
                                    <th>Общая сумма</th>
                                    <th>Статус</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <c:set var="count" value="0" scope="page" />
                                <c:forEach items="${orders}" var="order">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <c:if test="${order.getStatus()==0}">
                                        <c:set var="status" value="В обработке"></c:set>
                                            <tr class="processing">
                                        </c:if>
                                        <c:if test="${order.getStatus()==1}">
                                            <c:set var="status" value="Проведен"></c:set>
                                            <tr class="ready">
                                        </c:if>
                                        <c:if test="${order.getStatus()==-1}">
                                            <c:set var="status" value="Удален"></c:set>
                                            <tr class="deleted">
                                        </c:if>
                                        <td>${order.getDate()}</td>
                                        <td>${order.getSumFormat()} ${order.getToCardEquality()}</td>
                                        <td>${order.getCursFormat()} ${order.getFromCardEquality()}/${order.getToCardEquality()}</td>
                                        <td>${order.getMoneyAmountFormat()} ${order.getFromCardEquality()}</td>
                                        <td class="td_status">${status}</td>
                                        <td><span class="button white-text confirm" id="confirm${counter}">Провести</span></td>
                                        <td><span class="button white-text cantion" id="delete${counter}">Удалить</span></td>
                                    </tr>
                                </c:forEach>
                                    <input hidden value="${counter}" id="counter">
                            </table>
                        </div>
                        <div class="right">
                            <a href="" class="button button_transaction_journal">Перейти к справочнику</a>
                        </div>
                    </div>
                </div>
                <div class="service_right_part right block">
                    <div class="content">
                        <div class="title">
                            <img src="${mainImg}/changing-money.svg" alt="" class="icon">
                            <span class="title_text">Курс валют</span>
                        </div>
                        <div class="inline_select_course_block">
                            <div class="select_course">
                                <span class="toggle select_button" id="Privat">Наличный курс</span>
                            </div>
                            <div class="course_table_block">
                                <table class="course_table" id="privatTable">
                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th>Покупка</th>
                                        <th>Продажа</th>
                                    </tr>
                                    <c:forEach items="${currency}" var="curs">
                                        <tr>
                                            <td><img src="${mainImg}/${curs.getInfo().getPhoto()}" alt="" class="icon"></td>
                                            <td><strong>${curs.getCur_name()}</strong>
                                                <p class="secondary-text">${curs.getInfo().getName()}</p>
                                            </td>
                                            <td>${curs.getBuyPrivatFormat()}</td>
                                            <td>${curs.getSellPrivatFormat()}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <table class="course_table" id="privatTableForm">

                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th>Покупка</th>
                                        <th>Продажа</th>
                                    </tr>
                                    <c:forEach items="${currency}" var="curs">
                                        <tr>
                                            <td><img src="${mainImg}/${curs.getInfo().getPhoto()}" alt="" class="icon"></td>
                                            <td><strong>${curs.getCur_name()}</strong>
                                                <p class="secondary-text">${curs.getInfo().getName()}</p>
                                            </td>
                                            <td class="value_input"><input type="text" value="${curs.getBuyPrivat()}" name="buyCurs${curs.getCur_name()}"></td>
                                            <td class="value_input"><input type="text" value="${curs.getSellPrivat()}" name="sellCurs${curs.getCur_name()}"></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>

                        <div class="setDataForCurrencyBase">
                            <span class="button white-text left" id="UpdateCursApi">Обновить курс с API</span>
                            <span class="button white-text right" id="editCurs">Редактировать вручную</span>
                            <span class="button white-text left" id="cancelEditCurs">Отмена</span>
                            <span class="button white-text right" id="sendEditCurrency"><strong>Сохранить</strong></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script src="${mainJs}"></script>
    </body>

</html>
