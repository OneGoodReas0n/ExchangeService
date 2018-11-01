package com.reason.exchange.controller;

import com.reason.exchange.business.ExchangeProcess;
import com.reason.exchange.model.Client;
import com.reason.exchange.service.ClientToCardsService;
import com.reason.exchange.service.CurrencyService;
import com.reason.exchange.service.CurrencyApiService;
import com.reason.exchange.service.OrderService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    ExchangeProcess process;
    
    @Autowired
    CurrencyService currencyJournalService;
    
    @Autowired
    CurrencyService currencyService;
    
    @Autowired
    CurrencyApiService currencyApiService;
    
    @Autowired
    ClientToCardsService clientToCardsService;
    
    @Autowired
    OrderService orderService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getData(Map<String, Object> model) {
        model.put("cources", currencyService.getAll());
        return "index";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ModelAndView enter(@RequestParam Map<String, String> post, Map<String, Object> model) {
        if (post.get("user_login").isEmpty()==false || post.get("admin_login").isEmpty()==false) {
            if (post.get("user_login").isEmpty()==false) {
                String name = post.get("user_login");
                if (process.checkNewClient(name)) {
                    process.createNewAccount(name);
                }
                model.put("name", name);
                return new ModelAndView("redirect:/dashboard", model);
            }
            else{
                String name = post.get("admin_login");
                model.put("name", name);
                return new ModelAndView("redirect:/admin", model);
            }
        }
        return new ModelAndView("redirect:/");
        
    }

    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public String service(@RequestParam String name, Map<String, Object> model) {
        Client client = process.getExistAccount(name);
        model.put("cards", clientToCardsService.getCardIdsByClient(client.getId()));
        model.put("client", client);
        model.put("currency", currencyService.getAll());
        model.put("orders", orderService.getAllOrdersByClient(client));
        return "service";
    }
    
    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String admin(@RequestParam String name, Map<String, Object> model) {
        model.put("currency", currencyJournalService.getAll());
        model.put("name", name);
        model.put("orders", orderService.getAll());
        return "admin_panel";
    }
}
