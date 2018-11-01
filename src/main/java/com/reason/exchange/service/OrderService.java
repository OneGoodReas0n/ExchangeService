package com.reason.exchange.service;

import com.reason.exchange.dao.OrderDAO;
import com.reason.exchange.model.Client;
import com.reason.exchange.model.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractService<Order>{

    @Autowired
    OrderDAO dao;
    
    @Override
    public Order save(Order t) {
        try {
            return dao.add(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Order getOne(int id) {
        return dao.getOneById(id);
    }

    @Override
    public boolean update(Order t) {
        try {
            dao.update(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Order t) {
        try {
            dao.deleteById(t.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> getAll() {
        return dao.getAll();
    }

    public List<Order> getAllOrdersByClient(Client client){
        List<Order> orders = new ArrayList<>();
        for (Order order : dao.getAll()) {
            if(order.getClient_id()==client.getId()){
                orders.add(order);
            }
        }
        return orders;
    }
    
}
