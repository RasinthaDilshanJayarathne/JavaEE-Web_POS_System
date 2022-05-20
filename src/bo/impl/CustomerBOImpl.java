package bo.impl;

import bo.custom.CustomerBO;
import dao.customer.CustomerDAO;
import dao.impl.DAOFactory;
import dto.CustomerDTO;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ObservableList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        ObservableList<Customer> customers = customerDAO.getAll(connection);

        ObservableList<CustomerDTO> obCusList = FXCollections.observableArrayList();

        for (Customer temp : customers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    temp.getId(),temp.getName(),temp.getAddress(),temp.getContact()
            );

            obCusList.add(customerDTO);
        }
        return obCusList;
    }

    @Override
    public boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        Customer customer = new Customer(
                customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getContact()

        );
        return customerDAO.add(customer,connection);
    }

    @Override
    public boolean deleteCustomer(Connection connection, String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id,connection);
    }

    @Override
    public CustomerDTO searchCustomer(Connection connection, String cId) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(cId,connection);

        CustomerDTO customerDTO = new CustomerDTO(
                customer.getId(),customer.getName(),customer.getAddress(),customer.getContact()
        );
        return customerDTO;
    }

    @Override
    public boolean updateCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(
                customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(), customerDTO.getContact()
        );

        return customerDAO.update(customer,connection);
    }

}
