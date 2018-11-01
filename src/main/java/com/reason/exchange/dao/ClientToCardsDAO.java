package com.reason.exchange.dao;

import com.reason.exchange.logic.DbConnection;
import com.reason.exchange.model.ClientToCards;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ClientToCardsDAO extends Dao<ClientToCards, Integer> {

    private final static String TABLENAME = "client_to_cards";
    private final static String OBJECT = "";
    private PreparedStatement statement;
    private ResultSet result;
    private Connection connection;

    @Override
    public List<ClientToCards> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientToCards add(ClientToCards o) {
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("INSERT into %s (client_id, card_id) values (?,?)", TABLENAME));
            statement.setInt(1, o.getClient_id());
            statement.setInt(2, o.getCard_id());
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
    public ClientToCards getOneById(Integer key) {
        ClientToCards conn = new ClientToCards();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(getOneQuery(TABLENAME, OBJECT, key));
            result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    conn.setId(result.getInt("id"));
                    conn.setClient_id(result.getInt("client_id"));
                    conn.setCard_id(result.getInt("card_id"));
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
        return conn;
    }

    @Override
    public boolean update(ClientToCards o) {
        boolean flag = false;
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("UPDATE %s SET card_id=? Where client_id=?", TABLENAME));
            statement.setInt(1, o.getCard_id());
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
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
        return flag;
    }

    @Override
    public boolean deleteById(Integer key) {
        boolean flag;
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("Delete from %s where id = %d", TABLENAME,key));
            statement.execute();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
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
        return flag;
    }

    public ClientToCards getOneByCardId(int id) {
        ClientToCards bind = new ClientToCards();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE card_id=%d", TABLENAME,id));
            result = statement.executeQuery();
            while (result.next()) {
                bind.setId(result.getInt("id"));
                bind.setClient_id(result.getInt("client_id"));
                bind.setCard_id(result.getInt("card_id"));
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
        return bind;
    }

    public List<Integer> getOneClientCards(int id) {
        List<Integer> cards = new ArrayList<>();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("Select * FROM %s WHERE client_id=%d", TABLENAME, id));
            result = statement.executeQuery();
            while (result.next()) {
                cards.add(result.getInt("card_id"));
            }
        } catch (Exception e) {
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
                e.printStackTrace();
            }
        }
        return cards;
    }

}
