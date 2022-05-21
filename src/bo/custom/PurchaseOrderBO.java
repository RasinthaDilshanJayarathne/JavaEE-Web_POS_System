package bo.custom;

import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO{
    boolean saveOrder(Connection connection, OrderDTO ordersDTO) throws SQLException, ClassNotFoundException;

    ObservableList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException;

    OrderDTO searchOrder(String orderId, Connection connection) throws SQLException, ClassNotFoundException;

    String generateNewOrderId(Connection connection)throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomers(Connection connection)throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems(Connection connection)throws SQLException, ClassNotFoundException;
}
