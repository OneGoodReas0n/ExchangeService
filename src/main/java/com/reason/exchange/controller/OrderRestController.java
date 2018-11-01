package com.reason.exchange.controller;

import com.reason.exchange.business.ExchangeProcess;
import com.reason.exchange.model.Order;
import com.reason.exchange.service.ClientService;
import com.reason.exchange.service.OrderService;
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
@RequestMapping("/orders")
public class OrderRestController {
    
    @Autowired
    OrderService service;
    
    @Autowired
    ExchangeProcess process;
    
    @Autowired
    ClientService clientService;
    
    @GetMapping(path = "", produces = "application/json")
    public List<Order> getAll(){
        return service.getAll();
    }
    
    @GetMapping(path = "/{id}", produces = "application/json")
    public Order getOne(@PathVariable int id){
        return service.getOne(id);
    }
    
    @PostMapping(path = "", produces = "application/json")
    public boolean save(@RequestParam Map<String,String> post){
        String client = post.get("clientName");
        double sum = Double.parseDouble(post.get("sum"));
        int fromCardId = Integer.parseInt(post.get("fromCardId"));
        int toCardId = Integer.parseInt(post.get("toCardId"));
        double curs = process.converter(fromCardId, toCardId);
        return process.canMakeTrade(clientService.getOneByName(client), fromCardId, toCardId, curs, sum);
    }
    
    @PostMapping(path = "/{id}/update", produces = "application/json")
    public boolean update(@PathVariable int id,@RequestParam Map<String,String> post){
        int stasus = Integer.parseInt("order_status");
        Order order = service.getOne(id);
        order.setStatus(stasus);
        return service.update(order);
    }
    
    @GetMapping(path = "/{id}/delete", produces = "application/json")
    public boolean delete(@PathVariable int id){
        Order order = service.getOne(id);
        return service.delete(order);
    }
}
