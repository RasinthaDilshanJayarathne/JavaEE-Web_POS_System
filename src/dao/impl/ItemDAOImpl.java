package dao.impl;

import dao.customer.ItemDAO;
import entity.Item;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {


    @Override
    public boolean add(Item item, Connection conection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item item, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection conection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Item search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Item> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
