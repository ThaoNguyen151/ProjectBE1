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
public class Receipt{
    
    private String user;
    private int totalPrice;
    private String allProd;

    public Receipt() {
    }

    
    
    public Receipt(String user ,int totalPrice, String allProd) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.allProd = allProd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    
    
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAllProd() {
        return allProd;
    }

    public void setAllProd(String allProd) {
        this.allProd = allProd;
    }
    
    public String toString(){
        return user + " - " + totalPrice + " - " + allProd;
    }
    
    public void appendProd(String str){
        allProd = allProd.concat(str);
    }
}
