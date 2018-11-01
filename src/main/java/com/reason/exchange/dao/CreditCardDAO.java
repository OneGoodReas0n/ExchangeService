package com.reason.exchange.dao;

import com.reason.exchange.logic.DbConnection;
import com.reason.exchange.model.CreditCard;
import com.reason.exchange.model.info.CurrencyType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CreditCardDAO extends Dao<CreditCard, Integer> {

    private final static String TABLENAME = "credit_cards";
    private final static String OBJECT = "card";
    private PreparedStatement statement;
    private ResultSet result;
    private Connection connection;

    public CreditCardDAO() {
    }

    @Override
    public List<CreditCard> getAll() {
        List<CreditCard> cards = new ArrayList<>();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(getAllQuery(TABLENAME));
            result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    int id = result.getInt("card_id");
                    String name = result.getString("card_name");
                    double amount = result.getDouble("card_amount");
                    int currency_type_id = result.getInt("currency_type_id");
                    CreditCard card = new CreditCard(name);
                    card.setId(id);
                    card.setAmount(id);
                    card.setCurrencyTypeInt(currency_type_id);
                    cards.add(card);
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
        return cards;
    }

    @Override
    public CreditCard add(CreditCard o) {
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("INSERT into %s (card_name, card_number, currency_type_id, card_amount) values (?,?,?,?)", TABLENAME));
            statement.setString(1, o.getName());
            statement.setString(2, o.getCardNumber());
            statement.setInt(3, o.getCurrencyTypeInt());
            statement.setDouble(4, o.getAmount());
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
    public CreditCard getOneById(Integer key) {
        CreditCard card = new CreditCard();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(getOneQuery(TABLENAME, OBJECT, key));
            result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    card.setId(result.getInt("card_id"));
                    card.setName(result.getString("card_name"));
                    card.setCardNumber(result.getString("card_number"));
                    card.setCurrencyTypeInt(result.getInt("currency_type_id"));
                    card.setAmount(result.getDouble("card_amount"));
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
        return card;
    }

    @Override
    public boolean update(CreditCard o) {
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("UPDATE %s SET card_name=?, card_number=?, currency_type_id=?, card_amount=?  Where client_id=?", TABLENAME));
            statement.setString(1, o.getName());
            statement.setString(2, o.getCardNumber());
            statement.setInt(3, o.getCurrencyTypeInt());
            statement.setDouble(3, o.getAmount());
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

    public CreditCard getOneByNumber(String number) {
        CreditCard card = new CreditCard();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s_number='%s'", TABLENAME, OBJECT, number));
            result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    card.setId(result.getInt("card_id"));
                    card.setName(result.getString("card_name"));
                    card.setCardNumber(result.getString("card_number"));
                    card.setCurrencyTypeInt(result.getInt("currency_type_id"));
                    card.setAmount(result.getDouble("card_amount"));
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
        return card;
    }

}
