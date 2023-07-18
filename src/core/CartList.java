/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGUYENTRAN
 */
public class CartList extends ArrayList<Product1> {

    private static final String FILENAME = "src/data/cart";

     public void readFromfileCart() {
        BufferedReader reader;
        String line;
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println("File not exist!!!");
            System.exit(0);
        }
        try {
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(", ");
                if (row.length >= 7) {
                    String ID = row[0];
                    String name = row[1];
                    int ratingCount = Integer.parseInt(row[2]);
                    float starRating = Float.parseFloat(row[3]);
                    int quantity = Integer.parseInt(row[4]);
                    int soldQuantity = Integer.parseInt(row[5]);
                    int price = Integer.parseInt(row[6]);

                    Product1 cart = new Product1(ID, name, ratingCount, starRating, quantity, soldQuantity, price);
                    this.add(cart);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFileCart() {
        try {
            PrintWriter out = new PrintWriter(FILENAME);
            out.println("ID, Name, RatingCount, StarRating, Quantity, SoldQuantity, Price");
            this.forEach((prod) -> {
                out.println(prod.getID() + ", " + prod.getName() + ", " + prod.getRatingCount() + ", " + prod.getStarRating() + ", " + prod.getQuantity() + ", " + prod.getSoldQuantity() + ", " + prod.getPrice());
            });
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
