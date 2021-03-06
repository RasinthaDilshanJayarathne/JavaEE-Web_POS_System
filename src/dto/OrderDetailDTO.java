package dto;

public class OrderDetailDTO {
    private String orderId;
    private String code;
    private int orderQty;
    private int price;
    private int total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderId, String code, int orderQty, int price, int total) {
        this.setOrderId(orderId);
        this.setCode(code);
        this.setOrderQty(orderQty);
        this.setPrice(price);
        this.setTotal(total);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderId='" + orderId + '\'' +
                ", code='" + code + '\'' +
                ", orderQty=" + orderQty +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
