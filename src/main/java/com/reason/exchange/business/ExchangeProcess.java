package com.reason.exchange.business;

import com.reason.exchange.model.Client;
import com.reason.exchange.model.ClientToCards;
import com.reason.exchange.model.CreditCard;
import com.reason.exchange.model.Order;
import com.reason.exchange.model.currency.Currency;
import com.reason.exchange.service.ClientService;
import com.reason.exchange.service.ClientToCardsService;
import com.reason.exchange.service.CreditCardService;
import com.reason.exchange.service.CurrencyService;
import com.reason.exchange.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeProcess {

    @Autowired
    ClientService clientService;

    @Autowired
    CreditCardService cardService;

    @Autowired
    ClientToCardsService clientToCardsService;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    OrderService orderService;

    public ExchangeProcess() {
    }

    public boolean checkNewClient(String name) {
        return clientService.getOneByName(name).getName() == null;
    }

    public void createNewAccount(String name) {
        Client client = new Client(name);
        clientService.save(client);
        CreditCard card = new CreditCard("гривневая");
        card.setAmount(3000);
        String cardNumber = card.getCardNumber();
        cardService.save(card);
        Client dbClient = clientService.getOneByName(name);
        CreditCard bdCard = cardService.getOneByNumber(cardNumber);
        clientToCardsService.save(new ClientToCards(dbClient.getId(), bdCard.getId()));
    }

    public Client getExistAccount(String name) {
        return clientService.getOneByName(name);
    }

    public boolean addCurrency(String client_name, String currency) {
        boolean flag = true;
        Client client = clientService.getOneByName(client_name);
        CreditCard card;
        switch (currency) {
            case "USD":
                card = new CreditCard("долларовая");
                break;
            case "EUR":
                card = new CreditCard("евро");
                break;
            case "RUB":
            case "RUR":
                card = new CreditCard("рублевая");
                break;
            default:
                card = null;
                flag = false;
                break;
        }
        for (CreditCard userCreditCard : clientToCardsService.getCardIdsByClient(client.getId())) {
            if (card.getName().equals(userCreditCard.getName())) {
                flag = false;
            }
        }
        if (flag == true) {
            cardService.save(card);
            clientToCardsService.save(new ClientToCards(client.getId(),
                    cardService.getOneByNumber(card.getCardNumber()).getId()));
        }
        return flag;
    }

    public boolean canMakeTrade(Client client, int fromCardId, int toCardId, double curs, double sum) {
        boolean flag = false;
        CreditCard fromCard = cardService.getOne(fromCardId);
        CreditCard toCard = cardService.getOne(toCardId);
        double totalSum = curs * sum;
        if (fromCard.getAmount() > totalSum) {
            flag = true;
        } else {
            flag = false;
        }
        if(flag){
            Order order = new Order(sum, fromCardId, toCardId, fromCard.getType().getEquality(), 
                    toCard.getType().getEquality(), totalSum, curs, client.getId());
            orderService.save(order);
            return flag;
        }
        return flag;
        
    }

    public double converter(int fromCardId, int toCardId) {
        double curs = 0;
        CreditCard fromCard = cardService.getOne(fromCardId);
        CreditCard toCard = cardService.getOne(toCardId);
        if (fromCard.getType().getCode().equals("UAH")) {
            if (toCard.getType().getCode().equals("EUR") || toCard.getType().getCode().equals("USD")) {
                Currency currency = currencyService.getOneByCode(toCard.getType().getCode());
                curs = currency.getSellPrivat();
            }
            if (toCard.getType().getCode().equals("RUB")) {
                Currency currency = currencyService.getOneByCode(toCard.getType().getCode());
                curs = currency.getSellPrivat();
            }
        }
        if (fromCard.getType().getCode().equals("EUR") || fromCard.getType().getCode().equals("USD")
                || fromCard.getType().getCode().equals("EUR") && toCard.getType().getCode().equals("UAH")) {
            if (fromCard.getType().getCode().equals("EUR") || fromCard.getType().getCode().equals("USD")) {
                Currency currency = currencyService.getOneByCode(toCard.getType().getCode());
                curs = 1 / currency.getBuyPrivat();
            }
            if (toCard.getType().getCode().equals("RUB")) {
                Currency currency = currencyService.getOneByCode(toCard.getType().getCode());
                curs = 1 / currency.getBuyPrivat();
            }
        }
        return curs;
    }
}
