package com.reason.exchange.controller;

import com.reason.exchange.model.currency.Currency;
import com.reason.exchange.model.currency.PrivatBankCurrency;
import com.reason.exchange.service.CurrencyService;
import com.reason.exchange.service.CurrencyApiService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyRestController {

    @Autowired
    CurrencyService service;

    @Autowired
    CurrencyApiService currencyService;
    
    @Autowired
    CurrencyApiService currencyApiService;

    @GetMapping(path = "", produces = "application/json")
    public List<Currency> getCurrencies() {
        return service.getAll();
    }

    @GetMapping(path = "/api", produces = "application/json")
    public List<Currency> getUpdatedCurrencies() {
        if (service.getAll().isEmpty()) {
            for (Currency obj : currencyService.getCurrencyList()) {
                Currency journal = new Currency(obj.getCur_name(), obj.getBuyPrivat(), obj.getSellPrivat(),obj.getBuyNBU());
                service.save(journal);
            }
        } else {
            for (Currency obj : currencyApiService.getCurrencyList()) {
                for (Currency currency : service.getAll()) {
                    if (currency.getCur_name().equals(obj.getCur_name())) {
                        currency.setBuyPrivat(obj.getBuyPrivat());
                        currency.setSellPrivat(obj.getSellPrivat());
                        currency.setBuyNBU(obj.getBuyNBU());
                        service.update(currency);
                    }
                }
            }
        }
        return service.getAll();
    }

    @GetMapping(path = "/{name}", produces = "application/json")
    public Currency getOne(@PathVariable String name) {
        Currency cur = null;
        for (Currency currency : service.getAll()) {
            if (currency.getCur_name().equals(name)) {
                cur = currency;
            }
        }
        return cur;
    }

    @PostMapping(path = "/multiAdd", produces = "application/json")
    public List<Currency> addSeveralCurses(@RequestParam Map<String, String> post) {
        for (Currency currency : service.getAll()) {
            switch (currency.getCur_name()) {
                case "USD":
                    currency.setBuyPrivat(Double.parseDouble(post.get("buyCursUSD")));
                    currency.setSellPrivat(Double.parseDouble(post.get("sellCursUSD")));
                    service.update(currency);
                    break;
                case "EUR":
                    currency.setBuyPrivat(Double.parseDouble(post.get("buyCursEUR")));
                    currency.setSellPrivat(Double.parseDouble(post.get("sellCursEUR")));
                    service.update(currency);
                    break;
                case "RUR":
                    currency.setBuyPrivat(Double.parseDouble(post.get("buyCursRUR")));
                    currency.setSellPrivat(Double.parseDouble(post.get("sellCursRUR")));
                    service.update(currency);
                    break;
            }
        }
        return service.getAll();
    }

}
