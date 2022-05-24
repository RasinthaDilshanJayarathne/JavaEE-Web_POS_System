package entity;

public class OrderDetail {
    private String orderID;
    private String itemCode;
    private int qty;
    private int price;
    private int total;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, String itemCode, int qty, int price, int total) {
        this.setOrderID(orderID);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setPrice(price);
        this.setTotal(total);
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
        return "OrderDetail{" +
                "orderID='" + orderID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
