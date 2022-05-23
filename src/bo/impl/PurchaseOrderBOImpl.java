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

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    @Override
    public boolean saveOrder(Connection connection, OrderDTO ordersDTO) throws SQLException, ClassNotFoundException {

       /* connection = null;

        connection = dataSource.getConnection();

        boolean orderAvailable = orderDAO.ifOrderExist(ordersDTO.getOrderId(),connection);

        if (orderAvailable) {
            return false;
        }

        connection.setAutoCommit(false);

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

            Item search = itemDAO.search(detail.getCode(),connection);
            search.setQtyOnHand(search.getQtyOnHand() - detail.getOrderQty());
            boolean update = itemDAO.update(search,connection);
            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;*/

        Connection con = null;
        try {
            con = connection;
            con.setAutoCommit(false);

            boolean ifSaveOrder = orderDAO.add(new Order(
                            ordersDTO.getOrderId(),
                            ordersDTO.getCustomerId(),
                            ordersDTO.getOrderDate(),
                            ordersDTO.getTotal(),
                            ordersDTO.getSubTotal()),
                    connection
            );

            if (ifSaveOrder){
                if (saveOrderDetail(connection,ordersDTO)){
                    con.commit();
                    return true;
                }else {
                    con.rollback();
                    return false;
                }
            }else {
                con.rollback();
                return false;
            }
        } catch (SQLException throwables) {
        } finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public ObservableList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrderDetail(Connection connection, OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO item : orderDTO.getOrderDetail()) {
            boolean ifOrderDetailSaved = orderDetailsDAO.add(new OrderDetail(
                            item.getOrderId(), item.getCode(), item.getOrderQty(), item.getPrice(), item.getTotal()),connection
            );
            System.out.println(item.getCode());
            /*if (ifOrderDetailSaved){
                if (updateQtyOnHand(connection,item.getCode(),item.getOrderQty())){

                }else {
                    return false;
                }
            }else {
                return false;
            }*/
        }
        return true;

    }

    @Override
    public boolean updateQtyOnHand(Connection connection, String id, int qty) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQtyOnHand(connection,id,qty);
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
