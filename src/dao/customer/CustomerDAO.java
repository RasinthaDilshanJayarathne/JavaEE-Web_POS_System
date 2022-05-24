package dao.customer;

import entity.Customer;

import java.sql.Connection;

public interface CustomerDAO extends CrudDAO<Customer,String, Connection>{
}
