package dto;

public class ItemDTO {
    private String code;
    private String name;
    private int price;
    private int qtyOnHand;

    public ItemDTO() {
    }

    public ItemDTO(String code, String name, int price, int qtyOnHand) {
        this.setCode(code);
        this.setName(name);
        this.setPrice(price);
        this.setQtyOnHand(qtyOnHand);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
