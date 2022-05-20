package dao.impl;

import dao.customer.ItemDAO;
import entity.Customer;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item item, Connection conection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(conection,"INSERT INTO Item VALUES(?,?,?,?)",item.getCode(),
                item.getName(),item.getPrice(),item.getQtyOnHand());
    }

    @Override
    public boolean update(Item item, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "UPDATE Item SET itemName = ?,unitPrice = ?,unitPrice = ? WHERE itemCode = ?",item.getName(),
                item.getPrice(),item.getQtyOnHand(),item.getCode());
    }

    @Override
    public boolean delete(String code, Connection conection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(conection,"Delete from Item WHERE itemCode=?",code);
    }

    @Override
    public Item search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection,"SELECT * FROM Item WHERE itemCode =?",code);
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)
            );
        }else {
            return null;
        }
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
