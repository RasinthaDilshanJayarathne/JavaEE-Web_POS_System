package bo.custom;

import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public interface CustomerBO extends SuperBO{

    public ObservableList<CustomerDTO>getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;

    boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
