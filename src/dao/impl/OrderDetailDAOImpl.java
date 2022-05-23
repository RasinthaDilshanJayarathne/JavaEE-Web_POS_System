package dao.impl;

import dao.customer.OrderDetailDAO;
import entity.OrderDetail;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean add(OrderDetail orderDetails, Connection conection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(conection,"INSERT INTO `order detail` VALUES (?,?,?,?,?)",orderDetails.getOrderId(),
                orderDetails.getCode(), orderDetails.getOrderQty(), orderDetails.getPrice(), orderDetails.getTotal());
    }

    @Override
    public boolean update(OrderDetail orderDetail, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection conection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<OrderDetail> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
