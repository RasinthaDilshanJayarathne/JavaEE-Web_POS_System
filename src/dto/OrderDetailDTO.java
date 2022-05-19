package dto;

public class OrderDetailDTO {
    private String code;
    private String orderId;
    private int orderQty;
    private double price;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String code, String orderId, int orderQty, double price) {
        this.code = code;
        this.orderId = orderId;
        this.orderQty = orderQty;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "code='" + code + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderQty=" + orderQty +
                ", price=" + price +
                '}';
    }
}
