package com.reason.exchange.dao;

import com.reason.exchange.logic.DbConnection;
import com.reason.exchange.model.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderDAO extends Dao<Order, Integer>{

    private final static String TABLENAME = "orders";
    private final static String OBJECT = "order";
    private PreparedStatement statement;
    private ResultSet result;
    private Connection connection;

    public OrderDAO() {
    }
    
    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(getAllQuery(TABLENAME));
            result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    int id = result.getInt("order_id");
                    double sum = result.getDouble("order_sum");
                    int fromCardId = result.getInt("order_fromCardId");
                    int toCardId = result.getInt("order_toCardId");
                    double curs = result.getDouble("order_curs");
                    double totalMoney = result.getDouble("order_totalMoney");
                    int status = result.getInt("order_status");
                    int client_id = result.getInt("client_id");
                    Date date = result.getDate("order_date");
                    String fromCardEqual = result.getString("order_fromCardEqual");
                    String toCardEqual = result.getString("order_toCardEqual");
                    orders.add(new Order(id, sum, fromCardId, fromCardEqual, toCardId, 
                            toCardEqual, totalMoney, curs, status, client_id, date));
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
        return orders;
    }

    @Override
    public Order add(Order o) {
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("INSERT into %s "
                    + "(%s_sum,%s_fromCardId,%s_toCardId,%s_curs,%s_totalMoney, "
                    + "client_id, order_date, order_status, order_fromCardEqual,order_toCardEqual) "
                    + "values (?,?,?,?,?,?,?,?,?,?)", TABLENAME,OBJECT,OBJECT,OBJECT,OBJECT,OBJECT));
            statement.setDouble(1, o.getSum());
            statement.setInt(2, o.getFromCardId());
            statement.setInt(3, o.getToCardId());
            statement.setDouble(4, o.getCurs());
            statement.setDouble(5, o.getMoneyAmount());
            statement.setInt(6, o.getClient_id());
            statement.setDate(7, o.getDate());
            statement.setInt(8, o.getStatus());
            statement.setString(9, o.getFromCardEquality());
            statement.setString(10, o.getToCardEquality());
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
    public Order getOneById(Integer key) {
        Order order = new Order();
        try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(getOneQuery(TABLENAME, OBJECT, key));
            result = statement.executeQuery();
            while (result.next()) {
                order.setId(result.getInt("order_id"));
                order.setSum(result.getInt("order_sum"));
                order.setFromCardId(result.getInt("order_fromCardId"));
                order.setToCardId(result.getInt("order_toCardId"));
                order.setCurs(result.getDouble("order_curs"));
                order.setMoneyAmount(result.getDouble("order_totalMoney"));
                order.setStatus(result.getInt("order_status"));
                order.setClient_id(result.getInt("client_id"));
                order.setDate(result.getDate("order_date"));
                order.setFromCardEquality(result.getString("order_fromCardEqual"));
                order.setToCardEquality(result.getString("order_toCardEqual"));
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
        return order;
    }

    @Override
    public boolean update(Order o) {
         try {
            connection = DbConnection.getConnection();
            statement = connection.prepareStatement(String.format("UPDATE %s SET order_status=?, "
                    + "Where client_id=%d", TABLENAME,o.getId()));
            statement.setInt(1, o.getStatus());
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
