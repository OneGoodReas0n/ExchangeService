package com.reason.exchange.dao;

import com.reason.exchange.logic.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao<O,I> {
    
    private Connection connection = DbConnection.getConnection();
    
    private final String ALL_CLIENTS_QUERY = "SELECT * from %s";
    private final String GET_CLIENT_QUERY = "SELECT * from %s WHERE %s.%s_id = %d";
    private final String GET_CLIENT_BY_NAME_QUERY = "SELECT * from %s WHERE %s_name = %s";
    private final String DELETE_CLIENT_QUERY = "DELETE from %s WHERE %s.%s_id = %d";
    
    abstract public List<O> getAll();
    abstract public O add(O o);
    abstract public O getOneById(I key);
    abstract public boolean update(O o);
    abstract public boolean deleteById(I key);

    public String getAllQuery(String tableName) {
        return String.format(ALL_CLIENTS_QUERY, tableName);
    }

    public String getOneQuery(String tableName, String obj, int id) {
        return String.format(GET_CLIENT_QUERY, tableName,tableName,obj,id);
    }

    public String deleteOneQuery(String tableName, String obj, int id) {
        return String.format(DELETE_CLIENT_QUERY, tableName,tableName,obj,id);
    }
   
    
}
