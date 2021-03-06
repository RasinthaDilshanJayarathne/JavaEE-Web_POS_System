package dao.impl;

import dao.customer.CustomerDAO;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer customer, Connection conection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(conection,"INSERT INTO Customer VALUES(?,?,?,?)",customer.getId(),
                customer.getName(),customer.getAddress(),customer.getContact());
    }

    @Override
    public boolean update(Customer customer, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "UPDATE Customer SET cusName = ?,cusAddress = ?,cusTp = ? WHERE cusId = ?",customer.getName(),
                customer.getAddress(),customer.getContact(),customer.getId());

    }


    @Override
    public boolean delete(String id, Connection conection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(conection,"Delete from Customer WHERE cusId=?",id);
    }

    @Override
    public Customer search(String cId, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection,"SELECT * FROM Customer WHERE cusId =?",cId);
        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }else {
            return null;
        }
    }

    @Override
    public ObservableList<Customer> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT * FROM Customer");

        ObservableList<Customer> obList = FXCollections.observableArrayList();

        while (resultSet.next()){

            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            obList.add(customer);
        }
        return obList;
    }
}
