package bo.custom;

import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public interface CustomerBO extends SuperBO{

    public ObservableList<CustomerDTO>getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;

    boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(Connection connection,String id) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(Connection connection,String cId) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Connection connection,CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

}
