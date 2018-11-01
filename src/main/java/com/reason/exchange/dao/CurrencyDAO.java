package com.reason.exchange.dao;

import com.reason.exchange.logic.DbConnection;
import com.reason.exchange.model.currency.Currency;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDAO extends Dao<Currency, Integer>{

    private final static String TABLENAME = "currency_journal";
    private final static String OBJECT = "currency";
    private PreparedStatement statement;
    private ResultSet result;
    private Connection connection;

    public CurrencyDAO() {
    }
    
    @Override
    public List<Currency> getAll() {
        List<Currency> currencies = new ArrayList<>();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(getAllQuery(TABLENAME));
            result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    int id = result.getInt("currency_id");
                    String name = result.getString("currency_name");
                    double currency_buy = result.getDouble("currency_buy_curs");
                    double currency_sell = result.getDouble("currency_sell_curs");
                    double currency_buyNBU = result.getDouble("currency_buyNBU_curs");
                    Currency currency = new Currency(id,name, currency_buy, currency_sell,currency_buyNBU);
                    currencies.add(currency);
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
        return currencies;
    }

    @Override
    public Currency add(Currency o) {
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("INSERT into %s "
                    + "(currency_name,currency_buy_curs,currency_sell_curs,currency_buyNBU_curs) values (?,?,?,?)", TABLENAME));
            statement.setString(1, o.getCur_name());
            statement.setDouble(2, o.getBuyPrivat());
            statement.setDouble(3, o.getSellPrivat());
            statement.setDouble(4, o.getBuyNBU());
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
    public Currency getOneById(Integer key) {
        Currency currency = new Currency();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(getOneQuery(TABLENAME, OBJECT, key));
            result = statement.executeQuery();
            while (result.next()) {
                currency.setId(result.getInt("currency_id"));
                currency.setCur_name(result.getString("currency_name"));
                currency.setBuyPrivat(result.getDouble("currency_buy_curs"));
                currency.setSellPrivat(result.getDouble("currency_sell_curs"));
                currency.setSellPrivat(result.getDouble("currency_buyNBU_curs"));
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
        return currency;
    }

    @Override
    public boolean update(Currency o) {
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("UPDATE %s SET "
                    + "currency_name=?, currency_buy_curs=?, currency_sell_curs=?, currency_buyNBU_curs=?  "
                    + "Where currency_id=%d", TABLENAME,o.getId()));
            statement.setString(1, o.getCur_name());
            statement.setDouble(2, o.getBuyPrivat());
            statement.setDouble(3, o.getSellPrivat());
            statement.setDouble(4, o.getBuyNBU());
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
    
}
