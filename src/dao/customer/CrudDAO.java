package dao.customer;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T,ID,C> extends SuperDAO{
    public boolean add(T t, C c) throws SQLException, ClassNotFoundException;
    public boolean update(T t,C c) throws SQLException, ClassNotFoundException;
    public boolean delete(ID id,C c) throws SQLException, ClassNotFoundException;
    public T search(ID id,C c) throws SQLException, ClassNotFoundException;
    ObservableList<T> getAll(C c) throws SQLException, ClassNotFoundException;
}
