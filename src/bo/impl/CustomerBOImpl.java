package bo.impl;

import bo.custom.CustomerBO;
import dao.customer.CustomerDAO;
import dao.impl.DAOFactory;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        Customer customer = new Customer(
                customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getContact()

        );
        return customerDAO.add(customer,connection);
    }
}
