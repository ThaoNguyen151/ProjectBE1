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
 * @author Suko
 */
public class ReceiptList extends ArrayList<Receipt> {

    private static final String FILENAME = "src/data/receipt";

    public void readFromfile() {
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
                String[] row = line.split(", ", 3);
                if (row.length >= 3) {
                    String user = row[0];
                    int totalPrice = Integer.parseInt(row[1]);
                    String allItem = row[2];

                    Receipt rec = new Receipt(user ,totalPrice, allItem);
                    this.add(rec);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile() {
        try {
            PrintWriter out = new PrintWriter(FILENAME);
            out.println("Username, Price, ItemsBought");
            for (Receipt rec : this) {
                out.println(rec.getUser() + ", " + rec.getTotalPrice() + ", " + rec.getAllProd());
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
