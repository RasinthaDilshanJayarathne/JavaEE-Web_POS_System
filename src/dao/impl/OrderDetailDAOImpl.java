package dao.impl;

import dao.customer.OrderDetailDAO;
import entity.OrderDetail;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean add(OrderDetail orderDetail, Connection conection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection conection) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<OrderDetail> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
