/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Suko
 */
public class Product1 {

    private String ID;
    private String name;
    private int ratingCount;
    private float starRating;
    private int quantity;
    private int soldQuantity;
    private int price;

    public Product1() {

    }

    public Product1(String ID, String name, int ratingCount, float starRating, int quantity, int soldQuantity, int price) {
        this.ID = ID;
        this.name = name;
        this.ratingCount = ratingCount;
        this.starRating = starRating;
        this.quantity = quantity;
        this.soldQuantity = soldQuantity;
        this.price = price;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public float getStarRating() {
        return starRating;
    }

    public void setStarRating(float starRating) {
        this.starRating = starRating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "ID=" + ID + ", name=" + name + ", ratingCount=" + ratingCount + ", starRating=" + starRating + ", quantity=" + quantity + ", soldQuantity=" + soldQuantity + ", price=" + price + '}';
    }

    public String toStringMisc() {
        return "Product{" + "ID=" + ID + ", name=" + name + ", starRating=" + starRating + ", quantity=" + quantity + "soldQuantity=" + soldQuantity + ", price=" + price + '}';
    }

    //Method
    public int getTotal() {
        return price * quantity;
    }

    public void rateProd(float r) {
        if (ratingCount == 0.0) {
            ratingCount++;
            starRating = r;
        } else {
            starRating = ((starRating * ratingCount) + r) / (ratingCount + 1);
            ratingCount++;
        }
    }

}
