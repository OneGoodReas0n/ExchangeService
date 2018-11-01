package com.reason.exchange.logic;

import com.reason.exchange.info.StaticInfo;
import com.reason.exchange.model.currency.Currency;
import com.reason.exchange.model.currency.NBUCurrency;
import com.reason.exchange.model.currency.PrivatBankCurrency;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Parser {

    private RestTemplate restTemplate;
    private JSONParser parser;
    private PrivatBankCurrency coursPrivat;
    private NBUCurrency coursNBU;
    
    public Parser() {
        restTemplate = new RestTemplate();
        parser = new JSONParser();
    }
    
    public List<PrivatBankCurrency> getPrivatCurrency() {
        List<PrivatBankCurrency> result = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.getForEntity(StaticInfo.getPRIVATPATH(), String.class);
        try {
            JSONArray array = (JSONArray) parser.parse(response.getBody());
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                String ccy = (String) obj.get("ccy");
                String base_ccy = (String) obj.get("base_ccy");
                double buy = Double.parseDouble((String) obj.get("buy"));
                double sell = Double.parseDouble((String) obj.get("sale"));
                coursPrivat = new PrivatBankCurrency(ccy, base_ccy, buy, sell);
                if ((coursPrivat.getCurrency_name().equals("USD")) || 
                        (coursPrivat.getCurrency_name().equals("EUR")) || 
                        (coursPrivat.getCurrency_name().equals("RUR"))) {
                    result.add(coursPrivat);
                }
            }
        } catch (NumberFormatException | ParseException e) {
            System.err.println(e);
        }
        return result;
    }
    
    public List<NBUCurrency> getNBUCurrency() {
        List<NBUCurrency> result = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.getForEntity(StaticInfo.getNBUPATH(), String.class);
        try {
            JSONArray array = (JSONArray) parser.parse(response.getBody());
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                long id = (long)obj.get("r030");
                String name = (String) obj.get("txt");
                double rate = (double)(obj.get("rate"));
                String currencyChar = (String) obj.get("cc");
                String exchangeDate = (String) obj.get("date");
                coursNBU = new NBUCurrency(id, name, rate, currencyChar, exchangeDate);
                if ((coursNBU.getCc().equals("USD")) || 
                        (coursNBU.getCc().equals("EUR")) || 
                        (coursNBU.getCc().equals("RUB"))) {
                    result.add(coursNBU);
                }
            }
        } catch (NumberFormatException | ParseException e) {
            System.err.println(e);
        }
        return result;
    }
    
    public List<Currency> getCurrency(){
        List<PrivatBankCurrency> coursPrivat = getPrivatCurrency();
        List<NBUCurrency> coursNBU = getNBUCurrency();
        List<Currency> currencyList = new ArrayList<>();
        for (NBUCurrency nbu : coursNBU) {
            for (PrivatBankCurrency privat : coursPrivat) {
                if(privat.getInfo().equals(nbu.getInfo())){
                    Currency currency = new Currency(privat.getCurrency_name(),privat.getBuy(), privat.getSell(), nbu.getRate(), 0);
                    currency.toString();
                    currencyList.add(currency);
                }
            }
        }
        return currencyList;
    }
    
}
