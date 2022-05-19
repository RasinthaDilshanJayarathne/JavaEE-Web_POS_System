package dao.impl;

import dao.customer.OrderDAO;
import entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
