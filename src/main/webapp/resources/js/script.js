$(document).ready(function () {

    $("#user_entrance").addClass('active_button');
    $("#admin_form_box").addClass('hide');

    $("#user_entrance").click(function () {
        if (!$("#user_entrance").hasClass('active_button')) {
            if ($("#admin_form_box").hasClass('hide') && !$("#user_form_box").hasClass('hide')) {
                $("#user_entrance").addClass('active_button');
                $("#admin_entrance").removeClass('active_button');
            } else {
                $("#admin_form_box").addClass('hide');
                $("#user_form_box").removeClass('hide');
                $("#user_entrance").addClass('active_button');
                $("#admin_entrance").removeClass('active_button');
            }
        }

    });

    $("#admin_entrance").click(function () {
        if (!$("#admin_entrance").hasClass('active_button')) {
            if ($("#user_form_box").hasClass('hide')) {
                $("#admin_entrance").addClass('active_button');
                $("#admin_form_box").removeClass('hide');
                $("#user_entrance").removeClass('active_button');
            } else {
                $("#user_form_box").addClass('hide');
                $("#admin_entrance").addClass('active_button');
                $("#admin_form_box").removeClass('hide');
                $("#user_entrance").removeClass('active_button');
            }
        }

    });

    $("#Privat").addClass('active_button');
    $("#nbuTable").addClass('hide');

    $("#NBU").click(function () {
        if (!$("#NBU").hasClass('active_button')) {
            if ($("#privatTable").hasClass('hide') && !$("#nbuTable").hasClass('hide')) {
                $("#NBU").addClass('active_button');
                $("#Privat").removeClass('active_button');
            } else {
                $("#privatTable").addClass('hide');
                $("#nbuTable").removeClass('hide');
                $("#NBU").addClass('active_button');
                $("#Privat").removeClass('active_button');
            }
        }

    });

    $("#Privat").click(function () {
        if (!$("#Privat").hasClass('active_button')) {
            if ($("#nbuTable").hasClass('hide')) {
                $("#Privat").addClass('active_button');
                $("#privatTable").removeClass('hide');
                $("#NBU").removeClass('active_button');
            } else {
                $("#nbuTable").addClass('hide');
                $("#Privat").addClass('active_button');
                $("#privatTable").removeClass('hide');
                $("#NBU").removeClass('active_button');
            }
        }

    });



    $('select').formSelect();
    $('.dropdown-trigger').dropdown();
    $('.modal').modal();
    var client = $('#clientNameInput').val();



    $('#create_USD').click(function () {
        $.ajax({
            method: "POST",
            url: "/cards",
            data: {client_name: client, name: "USD"},
            success: function (response) {
                if (response == true) {
                    successAdd('Карта добавлена, обновите страницу!');
                } else {
                    successAdd('Такая карта у вас уже есть!');
                }
            }
        });
    });

    $('#create_EUR').click(function () {

        $.ajax({
            method: "POST",
            url: "/cards",
            data: {client_name: client, name: "EUR"},
            success: function (response) {
                if (response == true) {
                    successAdd('Карта добавлена, обновите страницу!');
                } else {
                    successAdd('Такая карта у вас уже есть!');
                }
            }
        });
    });

    $('#create_RUB').click(function () {
        $.ajax({
            method: "POST",
            url: "/cards",
            data: {client_name: client, name: "RUR"},
            success: function (response) {
                if (response == true) {
                    successAdd('Карта добавлена, обновите страницу!');
                } else {
                    successAdd('Такая карта у вас уже есть!');
                }
            }
        });
    });


    $('#UpdateCursApi').click(function () {
        $.ajax({
            method: "GET",
            url: "/currency/api",
            success: successAdd('Данные обновлены, обновите страницу!')
        });
    });

    $('#privatTableForm').hide();
    $('#sendEditCurrency').hide();
    $('#cancelEditCurs').hide();

    $('#editCurs').click(function () {
        $('#privatTable').hide();
        $('#privatTableForm').show();
        $('#editCurs').hide();
        $('#UpdateCursApi').hide();
        $('#cancelEditCurs').show();
        $('#sendEditCurrency').show();
    });



    $('#cancelEditCurs').click(cancel());

    $('#sendEditCurrency').click(function () {
        sendSeveralCurses();
        cancel();
    });

    $('#btn_exchange').click(function () {
        $.ajax({
            method: "POST",
            url: "/orders",
            data: {
                "clientName": $('#clientNameInput').val(),
                "fromCardId": $('#select_from_card').val(),
                "toCardId": $('#select_to_card').val(),
                "sum": $('#money_amount').val()},
            success: function (response) {
                if (response == true) {
                    successAdd('Запрос передан на обработку, ожидайте!');
                } else {
                    successAdd('У вас недостаточно денег для совершения этого обмена!');
                }
            }
        });
    });

    function sendSeveralCurses() {
        var data = {
            "buyCursUSD": $("input[name='buyCursUSD']").val(),
            "sellCursUSD": $("input[name='sellCursUSD']").val(),
            "buyCursEUR": $("input[name='buyCursEUR']").val(),
            "sellCursEUR": $("input[name='sellCursEUR']").val(),
            "buyCursRUR": $("input[name='buyCursRUR']").val(),
            "sellCursRUR": $("input[name='sellCursRUR']").val()
        };

        $.ajax({
            method: "POST",
            url: "/currency/multiAdd",
            data: data,
            success: successAdd('Данные обновлены, обновите страницу!')
        });
    }
    function cancel() {
        $('#privatTable').show();
        $('#privatTableForm').hide();
        $('#editCurs').show();
        $('#UpdateCursApi').show();
        $('#cancelEditCurs').hide();
        $('#sendEditCurrency').hide();
    }
    function successAdd(text) {
        M.toast({html: text});
    }
    
    var counter = $('#counter').val();
    $('.confirm').click(function () {
        alert(this.id);
    });
});