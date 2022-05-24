package entity;

import java.time.LocalDate;

public class Order {
    private String orderId;
    private String cusId;
    private LocalDate orderDate;
    private int total;
    private int subTotal;

    public Order() {
    }

    public Order(String orderId, String cusId, LocalDate orderDate,int total, int subTotal) {
        this.setOrderId(orderId);
        this.setCustomerId(cusId);
        this.setOrderDate(orderDate);
        this.setTotal(total);
        this.setSubTotal(subTotal);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return cusId;
    }

    public void setCustomerId(String customerId) {
        this.cusId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + cusId + '\'' +
                ", orderDate=" + orderDate +
                ", total=" + total +
                ", subTotal=" + subTotal +
                '}';
    }
}
