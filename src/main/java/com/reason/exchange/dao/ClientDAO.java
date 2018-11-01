package com.reason.exchange.dao;

import com.reason.exchange.logic.DbConnection;
import com.reason.exchange.model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ClientDAO extends Dao<Client, Integer> {

    private final static String TABLENAME = "clients";
    private final static String OBJECT = "client";
    private PreparedStatement statement;
    private ResultSet result;
    private Connection connection;

    public ClientDAO() {
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(getAllQuery(TABLENAME));
            result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    int id = result.getInt("client_id");
                    String name = result.getString("client_name");
                    Client client = new Client(id, name);
                    clients.add(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
        return clients;
    }

    @Override
    public Client add(Client o) {
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("INSERT into %s "
                    + "(client_name) values (?)", TABLENAME));
            statement.setString(1, o.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
        return o;
    }

    @Override
    public Client getOneById(Integer key) {
        Client client = new Client();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(getOneQuery(TABLENAME, OBJECT, key));
            result = statement.executeQuery();
            while (result.next()) {
                client.setId(result.getInt(1));
                client.setName(result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
        return client;
    }

    @Override
    public boolean update(Client o) {
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("UPDATE %s SET name=?  Where client_id=?", TABLENAME));
            statement.setString(1, o.getName());
            statement.setInt(2, o.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public boolean deleteById(Integer key) {
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(deleteOneQuery(TABLENAME, OBJECT, key));
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public Client getOneByName(String name) {
        Client client = new Client();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s_name='%s'", TABLENAME, OBJECT, name));
            result = statement.executeQuery();

            if (result.next() != false) {
                do {
                    client.setId(result.getInt(1));
                    client.setName(result.getString(2));
                } while (result.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
        return client;
    }

}
