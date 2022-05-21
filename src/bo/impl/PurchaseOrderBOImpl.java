package bo.impl;

import bo.custom.PurchaseOrderBO;
import dao.customer.CustomerDAO;
import dao.customer.ItemDAO;
import dao.customer.OrderDAO;
import dao.customer.OrderDetailDAO;
import dao.impl.DAOFactory;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import javafx.collections.ObservableList;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;


    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    @Override
    public boolean saveOrder(Connection connection, OrderDTO ordersDTO) throws SQLException, ClassNotFoundException {

        /*Transaction*/
        connection = null;

        connection = dataSource.getConnection();

        boolean orderAvailable = orderDAO.ifOrderExist(ordersDTO.getOrderId(),connection);
        /*if order id already exist*/
        if (orderAvailable) {
            return false;
        }

        connection.setAutoCommit(false);
        /* Add Order*/
        Order order = new Order(ordersDTO.getOrderId(), ordersDTO.getCustomerId(),ordersDTO.getOrderDate(),
                ordersDTO.getTotal(),ordersDTO.getSubTotal());
        boolean orderAdded = orderDAO.add(order,connection);
        if (!orderAdded) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO detail : ordersDTO.getOrderDetail()) {
            OrderDetail orderDetailDTO = new OrderDetail(detail.getOrderId(),detail.getCode(), detail.getOrderQty(), detail.getPrice(),detail.getTotal());
            boolean orderDetailsAdded = orderDetailsDAO.add(orderDetailDTO,connection);
            if (!orderDetailsAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
            Item search = itemDAO.search(detail.getCode(),connection);
            search.setQtyOnHand(search.getQtyOnHand() - detail.getOrderQty());
            boolean update = itemDAO.update(search,connection);
            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        //if every thing ok
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public ObservableList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDTO searchOrder(String orderId, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewOrderId(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
