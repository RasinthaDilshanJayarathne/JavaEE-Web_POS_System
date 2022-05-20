package bo.custom;

import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemBO extends SuperBO{

    public ObservableList<ItemDTO> getAllItem(Connection connection) throws SQLException, ClassNotFoundException;

    boolean addItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteItem(Connection connection,String id) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(Connection connection,String code) throws SQLException, ClassNotFoundException;

    boolean updateItem(Connection connection,ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
}
