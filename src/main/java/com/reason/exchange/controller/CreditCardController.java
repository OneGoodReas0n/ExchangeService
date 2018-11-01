package com.reason.exchange.controller;

import com.reason.exchange.business.ExchangeProcess;
import com.reason.exchange.model.Client;
import com.reason.exchange.model.ClientToCards;
import com.reason.exchange.model.CreditCard;
import com.reason.exchange.service.ClientService;
import com.reason.exchange.service.ClientToCardsService;
import com.reason.exchange.service.CreditCardService;
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
@RequestMapping("/cards")
public class CreditCardController {
    
    @Autowired
    CreditCardService service;
    
    @Autowired
    ClientService clientService;
    
    @Autowired
    ClientToCardsService clientToCardsService;
    
    @Autowired
    ExchangeProcess process;
    
    @GetMapping(path = "", produces = "application/json")
    public List<CreditCard> getAll(){
        return service.getAll();
    }
    
    @GetMapping(path = "/{id}", produces = "application/json")
    public CreditCard getOne(@PathVariable int id){
        return service.getOne(id);
    }
    
    @PostMapping(path = "", produces = "application/json")
    public boolean add(@RequestParam Map<String,String> post){
        return process.addCurrency(post.get("client_name"), post.get("name"));
       
    }
    
    @PostMapping(path = "/{id}/update", produces = "application/json")
    public String update(@RequestParam Map<String,String> post){
        CreditCard card = service.getOneByNumber(post.get("card_number"));
        card.setAmount(card.getAmount()-Double.parseDouble(post.get("totalSum")));
        if(service.update(card)){
            return "Card balance updated";
        }
        else{
            return "Error";
        }
    }
    
    @GetMapping(path = "/{id}/delete", produces = "application/json")
    public String delete(@PathVariable int id){
        CreditCard card = service.getOne(id);
        ClientToCards bind = clientToCardsService.getOneByCardId(id);
        clientToCardsService.delete(bind);
        if(service.delete(card)){
            return "Card deleted";
        }
        else{
            return "Error";
        }
    }
}
