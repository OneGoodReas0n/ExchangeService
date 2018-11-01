package com.reason.exchange.controller;

import com.reason.exchange.business.ExchangeProcess;
import com.reason.exchange.model.Client;
import com.reason.exchange.service.ClientService;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class RestClientController {
    
    @Autowired
    ClientService service;
    
    @Autowired
    ExchangeProcess process;
    
    @GetMapping(path = "", produces = "application/json")
    public List<Client> getClients(){
        System.out.println(service.getAll().size());
        return service.getAll();
    }
    
    @GetMapping(path = "/{id}", produces = "application/json")
    public Client getClient(@PathVariable int id){
        return service.getOne(id);
    }
    
    @PostMapping(path = "", produces = "application/json")
    public String addClient(@RequestParam Map<String,String> post){
        String name = post.get("user_name");
        if(!name.equals("")){
            if(process.checkNewClient(name)){
                process.createNewAccount(name);
                return "Пользователь "+name+" создан";
            }
        }
        return "Ошибка";
    }
}
