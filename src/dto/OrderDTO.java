package dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDTO {
    private String orderId;
    private String customerId;
    private LocalDate orderDate;
    private int total;
    private int subTotal;
    private ArrayList<OrderDetailDTO>orderDetail;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String customerId, LocalDate orderDate, int total, int subTotal, ArrayList<OrderDetailDTO> orderDetail) {
        this.setOrderId(orderId);
        this.setCustomerId(customerId);
        this.setOrderDate(orderDate);
        this.setTotal(total);
        this.setSubTotal(subTotal);
        this.setOrderDetail(orderDetail);
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

    public ArrayList<OrderDetailDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<OrderDetailDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate=" + orderDate +
                ", total=" + total +
                ", subTotal=" + subTotal +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
