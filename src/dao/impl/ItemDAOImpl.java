package dao.impl;

import dao.customer.ItemDAO;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
