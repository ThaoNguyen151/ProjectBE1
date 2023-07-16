/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utils.MyUtils;

/**
 *
 * @author Suko
 */
public class Cart extends ArrayList<Product1> {
    private boolean isAdmin;
    private static final String receipt = "src/data/receipt";

    public void confirmPurchase(Shop shop) {
        if (this.size() > 0) {
            for (Product1 prod : this) {
                for (int i = 0; i < shop.size(); i++) {
                    if (shop.get(i).getID().equals(prod.getID())) {
                        shop.get(i).setQuantity(shop.get(i).getQuantity() - prod.getQuantity());
                        shop.get(i).setSoldQuantity(shop.get(i).getSoldQuantity() + prod.getQuantity());

                        System.out.println("Bought " + prod.getQuantity() + " " + prod.getName());
                        shop.writeToFile();
                    }
                }
            }

            this.clear();
        } else {
            System.out.println("Nothing in Cart to Confirm");
        }
    }

    public void addToCart(Product1 prod, int quantity) {
        Shop shop = new Shop();
        shop.readFromfile();

        //prod 1: Pass in ; prod 2: Already in Cart
        for (Product1 prod2 : this) {
            if (prod2.getID().equals(prod.getID())) {
                for (int i = 0; i < shop.size(); i++) {
                    if (shop.get(i).getID().equals(prod.getID())) {

                        //System.out.println(shop.get(i).getQuantity() + "-" + prod2.getQuantity() + "-" + quantity);
                        if (shop.get(i).getQuantity() >= prod2.getQuantity() + quantity) {
                            Product1 newProd1 = new Product1(prod.getID(), prod.getName(), prod.getRatingCount(), prod.getStarRating(), prod2.getQuantity() + quantity, prod.getSoldQuantity(), prod.getPrice());
                            this.remove(prod2);
                            this.add(newProd1);
                            System.out.println("\nPut " + quantity + " " + prod.getName() + " Into Cart");
                            return;
                        } else {
                            System.out.println("Not enough quantity");
                            return;
                        }
                    }
                }
            }
        }

        Product1 newProd2 = new Product1(prod.getID(), prod.getName(), prod.getRatingCount(), prod.getStarRating(), quantity, prod.getSoldQuantity(), prod.getPrice());
        this.add(newProd2);
        System.out.println("\nPut " + quantity + " " + prod.getName() + " Into Cart");
    }

    public void removeFromCart() {
        if (this.isEmpty()) {
            System.out.println("Nothing to remove");
            return;
        }

        System.out.println("Choose Item to Remove: ");

        ArrayList<String> mmList = new ArrayList();

        for (int i = 0; i < this.size(); i++) {
            mmList.add(this.get(i).toString());
        }

        System.out.println("\n");
        for (int i = 0; i < mmList.size(); i++) {
            System.out.println((i + 1 + ". ") + mmList.get(i));
        }
        System.out.println("\n");

        int choice2 = MyUtils.inputInt("Enter your option: ", "Invalid choice", 1, mmList.size());
        this.remove(choice2 - 1);
    }

    public void viewCart() {
        System.out.println();

        Collections.sort(this, new Comparator<Product1>() {
            @Override
            public int compare(Product1 t, Product1 t1) {
                return t.getID().compareTo(t1.getID());
            }
        });

        if (isAdmin) {

            for (int i = 0; i < this.size(); i++) {
                System.out.println((i + 1 + ". ") + this.get(i).toString());
            }
            System.out.println("\n");
        } else {

            for (int i = 0; i < this.size(); i++) {
                System.out.println((i + 1 + ". ") + this.get(i).toStringMisc());
            }
            System.out.println("\n");
        }
    }

    public void changeQuantity(Shop shop) {
        if (this.isEmpty()) {
            System.out.println("No item to change Quantity");
            return;
        }

        System.out.println("Choose Item to Remove: ");

        ArrayList<String> mmList = new ArrayList();

        for (int i = 0; i < this.size(); i++) {
            mmList.add(this.get(i).toString());
        }

        System.out.println("\n");
        for (int i = 0; i < mmList.size(); i++) {
            System.out.println((i + 1 + ". ") + mmList.get(i));
        }
        System.out.println("\n");

        int choice = MyUtils.inputInt("Enter your choice to change quantity", "Invalid choice", 1, mmList.size());
        int quantity = MyUtils.inputInt("Enter Quantity: ", "Quantity can't be empty", 1, shop.get(choice - 1).getQuantity());
        this.get(choice - 1).setQuantity(quantity);
    }
    
    public void cartMenu(Shop shop, User user) {
        ArrayList<String> mList = new ArrayList();
        isAdmin = user.isAdminPerm();
        
        System.out.println("============ Cart ============");
        mList.add("Confirm all Purchase");
        mList.add("View all product in cart");
        mList.add("Change Quantity");
        mList.add("Remove from Cart");
        mList.add("Exit (Will lose data inside Cart)");
        if (isAdmin) {
            mList.add("(Admin) View all receipt");
        }

        do {

            for (int i = 0; i < mList.size(); i++) {
                System.out.println((i + 1 + ". ") + mList.get(i));
            }

            int choice = MyUtils.inputInt("Enter your option: ", "Invalid choice", 1, mList.size());

            switch (choice) {
                case 1:
                    confirmPurchase(shop);
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    changeQuantity(shop);
                    break;
                case 4:
                    removeFromCart();
                    break;
                case 5:
                    return;

                //View receipt
            }

        } while (true);

    }
}
