package vn.aptech.productfile;

import java.io.Serializable;

// để lưu ra file theo dạng Binary của ObjectOutputStream, cần implement Serializable
public class ProductDto implements Serializable {
    private int id;
    private String name;
    private int price;

    public ProductDto() {
    }

    public ProductDto(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
