package com.reason.exchange.service;

import com.reason.exchange.dao.ClientDAO;
import com.reason.exchange.model.Client;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractService<Client>{

    @Autowired
    ClientDAO dao;
    
    @Override
    public Client save(Client t) {
        try {
            return dao.add(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client getOne(int id) {
        return dao.getOneById(id);
    }

    @Override
    public boolean update(Client t) {
        try {
            dao.update(t);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Client t) {
        try {
            dao.deleteById(t.getId());
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        for (Client client : dao.getAll()) {
            clients.add(client);
        }
        return clients;
    }
    
    public Client getOneByName(String name){
        return dao.getOneByName(name);
    }
    
}
