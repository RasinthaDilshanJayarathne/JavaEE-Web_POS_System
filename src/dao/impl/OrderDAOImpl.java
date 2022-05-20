package dao.impl;

import dao.customer.OrderDAO;
import entity.Order;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public boolean add(Order order, Connection conection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection conection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Order> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
