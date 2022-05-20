package dao.impl;

import dao.customer.ItemDAO;
import entity.Customer;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
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
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT * FROM Item");

        ObservableList<Item> obList = FXCollections.observableArrayList();

        while (resultSet.next()){

            Item item = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
            obList.add(item);
        }
        return obList;
    }
}
