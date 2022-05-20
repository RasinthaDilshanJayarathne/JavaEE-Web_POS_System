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
                    temp.getCode(),temp.getName(), (int) temp.getPrice(),temp.getQtyOnHand()
            );

            obCusList.add(itemDTO);
        }
        return obCusList;
    }

    @Override
    public boolean addItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Item item = new Item(
                itemDTO.getCode(),itemDTO.getName(),itemDTO.getPrice(),itemDTO.getQtyOnHand()

        );
        return itemDAO.add(item,connection);
    }

    @Override
    public boolean deleteItem(Connection connection, String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id,connection);
    }

    @Override
    public ItemDTO searchItem(Connection connection, String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(code,connection);

        ItemDTO itemDTO = new ItemDTO(
                item.getCode(),item.getName(),item.getPrice(),item.getQtyOnHand()
        );
        return itemDTO;
    }

    @Override
    public boolean updateItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Item item = new Item(
                itemDTO.getCode(),itemDTO.getName(),itemDTO.getPrice(), itemDTO.getQtyOnHand()
        );

        return itemDAO.update(item,connection);
    }
}
