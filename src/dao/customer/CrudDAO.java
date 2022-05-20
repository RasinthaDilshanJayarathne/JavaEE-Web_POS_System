package dao.customer;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T,ID> extends SuperDAO{
    public boolean add(T t, Connection conection) throws SQLException, ClassNotFoundException;
    public boolean update(T t) throws SQLException, ClassNotFoundException;
    public boolean delete(ID id,Connection conection) throws SQLException, ClassNotFoundException;
    public T search(ID id) throws SQLException, ClassNotFoundException;
    ObservableList<T> getAll(Connection connection) throws SQLException, ClassNotFoundException;
}
