package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
    private String orderId;
    private String customerId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private double coust;

    public Order() {
    }

    public Order(String orderId, String customerId, LocalDate orderDate, LocalTime orderTime, double coust) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.coust = coust;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public double getCoust() {
        return coust;
    }

    public void setCoust(double coust) {
        this.coust = coust;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", coust=" + coust +
                '}';
    }
}
