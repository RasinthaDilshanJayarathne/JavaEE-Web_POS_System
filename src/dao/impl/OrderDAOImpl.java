package dao.impl;

import dao.customer.OrderDAO;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(Order orders, Connection conection) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.executeUpdate(conection, "INSERT INTO `Order` VALUES (?,?,?,?,?)", orders.getOrderId(),
                orders.getCustomerId(), orders.getOrderDate(), orders.getTotal(), orders.getSubTotal());
        return b;
    }

    @Override
    public boolean update(Order order, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection conection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Order> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT * FROM `Order`");

        ObservableList<Order> obList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Order orders = new Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    LocalDate.parse(resultSet.getString(3)),
                    resultSet.getInt(4),
                    resultSet.getInt(5)
            );
            obList.add(orders);
        }
        return obList;
    }

    @Override
    public boolean ifOrderExist(String oid, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewOrderId(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT orderID FROM `Order` ORDER BY orderID DESC LIMIT 1");

        if (resultSet.next()){
            return resultSet.getString(1);
        }else {
            return null;
        }
    }
}
