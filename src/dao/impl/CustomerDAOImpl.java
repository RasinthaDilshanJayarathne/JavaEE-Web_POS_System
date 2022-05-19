package dao.impl;

import dao.customer.CustomerDAO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
