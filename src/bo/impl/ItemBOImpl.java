package bo.impl;

import bo.custom.ItemBO;
import dao.customer.CustomerDAO;
import dao.customer.ItemDAO;
import dao.impl.DAOFactory;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ObservableList<ItemDTO> getAllItem(Connection connection) throws SQLException, ClassNotFoundException {
        ObservableList<Item> customers = itemDAO.getAll(connection);

        ObservableList<ItemDTO> obCusList = FXCollections.observableArrayList();

        for (Item temp : customers) {
            ItemDTO itemDTO = new ItemDTO(
                    temp.getCode(),temp.getName(),temp.getPrice(),temp.getQtyOnHand()
            );

            obCusList.add(itemDTO);
        }
        return obCusList;
    }

    @Override
    public boolean addItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(Connection connection, String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CustomerDTO searchItem(Connection connection, String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return false;
    }
}
