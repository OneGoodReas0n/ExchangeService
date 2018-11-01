<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

    <head>
        <spring:url value="/resources/css/style.css" var="mainCss" />
        <spring:url value="/resources/css/service-style.css" var="serviceCss" />
        <spring:url value="/resources/js/script.js" var="mainJs" />
        <spring:url value="/resources/images/" var="mainImg" />
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" media="screen,projection" />
        <link rel="stylesheet" href="${mainCss}">
        <link rel="stylesheet" href="${serviceCss}">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta charset="utf-8">
        <title></title>
    </head>

    <body>
        <div class="container">
            <div class="exit">
                <span id="client_name"><c:out value="${client.getName()}"/></span>
                <input hidden value="<c:out value="${client.getName()}"/>" id="clientNameInput">
                <img src="${mainImg}/user.svg" alt="" class="icon">
                <a href="/"><img src="${mainImg}/exit.svg" alt="" class="icon_exit"></a>
            </div>
            <div class="service_block left">
                <div class="service_left_part left block">
                    <div class="content">
                        <div class="title">
                            <img src="${mainImg}/wallet.svg" alt="" class="icon">
                            <span class="title_text">Карточные счета</span>
                        </div>
                        <div class="card_accounts_table_block">
                            <table class="card_accounts_table">
                                <c:forEach var="card" items="${cards}">
                                    <tr>
                                        <td><strong>${card.getName()}</strong>
                                            <p class="secondary-text"><strong>${card.getType().getCode()} </strong>${card.getCardNumber()}</p>
                                        </td>
                                        <td>${card.getAmount()} ${card.getType().getEquality()}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <c:if test="${cards.size()<4}">
                            <div class="row">
                                <div class="addCard_block col s12 m12 l12">
                                    <a href="#" id="addCard_button" class="dropdown-trigger" data-target='dropdown1'>Добавить валютную карту</a>
                                </div>
                                <ul id='dropdown1' class='dropdown-content'>
                                    <li><a class="waves-effect waves-light modal-trigger" href="#USD">USD</a></li>
                                    <li><a class="waves-effect waves-light modal-trigger" href="#EUR">EUR</a></li>
                                    <li><a class="waves-effect waves-light modal-trigger" href="#RUB">RUB</a></li>
                                </ul>

                                <!-- Modal Structure -->
                                <div id="USD" class="modal">
                                    <div class="modal-content">
                                        <h6>Подтвердите добавление Долларовой карты (USD)</h6>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="#!" class="modal-close waves-effect waves-green btn-flat">Отмена</a>
                                        <a href="#!" class="modal-close waves-effect waves-green btn-flat" id="create_USD">Подтвердить</a>
                                    </div>
                                </div>
                                <!-- Modal Structure -->
                                <div id="EUR" class="modal">
                                    <div class="modal-content">
                                        <h6>Подтвердите добавление Евро карты (EUR)</h6>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="#!" class="modal-close waves-effect waves-green btn-flat">Отмена</a>
                                        <a href="#!" class="modal-close waves-effect waves-green btn-flat" id="create_EUR">Подтвердить</a>
                                    </div>
                                </div>
                                <!-- Modal Structure -->
                                <div id="RUB" class="modal">
                                    <div class="modal-content">
                                        <h6>Подтвердите добавление Рублевой карты (RUB)</h6>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="#!" class="modal-close waves-effect waves-green btn-flat">Отмена</a>
                                        <a href="#!" class="modal-close waves-effect waves-green btn-flat" id="create_RUB">Подтвердить</a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="service_right_part right block">
                    <div class="content">
                        <div class="title">
                            <img src="${mainImg}/transfer.svg" alt="" class="icon">
                            <span class="title_text">Обмен валют</span>
                        </div>
                        <div class="exchange_form_block">
                            <form class="" action="index.html" method="post">
                                <div class="select_procedure_block left">
                                    <div class="select_block left">
                                        <select name="card_select" class="exchange_select" id="select_from_card">
                                            <option value="" selected disabled>Выберите карту</option>
                                            <c:forEach items="${cards}" var="card">
                                                <c:if test="${card.getAmount()>0}">
                                                    <option value="${card.getId()}">${card.getName()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="select_block right">
                                        <select name="currency_select" id="select_to_card">
                                            <option value="" selected disabled>Выберите валютную карту</option>
                                            <c:forEach items="${cards}" var="card">
                                                <c:if test="${card.getType().getCode()!="UAH"}">
                                                    <option value="${card.getId()}">${card.getType().getCode()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row exchange_process_block">
                                    <div class="input-field col s5 m5 l5 left" id="amount">
                                        <input id="money_amount" type="text" class="validate withBorder">
                                        <label for="money_amount" >Сумма</label>
                                    </div>
                                    <div class="input-field col s3 m3 l3 left" id="curs">
                                        <input id="currency_course" type="text" value="Курс" disabled class="validate withBorder">
                                    </div>
                                    <div class="input-field col s4 m4 l4 left" id="total_sum">
                                        <input id="total_sum" type="text" value="0.00" disabled class="validate withBorder">
                                    </div>
                                </div>
                                <a id="btn_exchange" class="button button_transaction_journal right">Обменять</a>
                            </form>
                        </div>
                    </div>

                </div>
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
                                </tr>
                                <c:forEach items="${orders}" var="order">
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
                                        <td>${status}</td>
                                    </tr>
                                </c:forEach>

                            </table>
                        </div>
                        <div class="right">
                            <a href="#" class="button button_transaction_journal">Перейти к справочнику</a>
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
                                <span class="toggle select_button" id="NBU">Курс НБУ</span>
                                <span class="toggle select_button" id="Privat">Наличный курс</span>
                            </div>
                            <div class="course_table_block">
                                <table class="course_table" id="nbuTable">
                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th>Продажа</th>
                                    </tr>
                                    <c:forEach items="${currency}" var="curs">
                                        <tr>
                                            <td><img src="${mainImg}/${curs.getInfo().getPhoto()}" alt="" class="icon"></td>
                                            <td><strong>${curs.getCur_name()}</strong>
                                                <p class="secondary-text">${curs.getInfo().getName()}</p>
                                            </td>
                                            <td>${curs.getBuyNBUFormat()}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
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
                            </div>
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
