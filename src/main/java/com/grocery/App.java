package com.grocery;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Hello world!
 */
public final class App {

    DBconnect dBconnect;

    private App() {
        this.dBconnect = DBconnect.getInstance();
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        App ap = new App();
        ap.createSchema();
        ap.display();

    }

    public void createSchema() {
        try {
            Service sq = new Service(this.dBconnect);
            sq.createSchema();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Please Enter Item");
        Scanner sc = new Scanner(System.in);
        String item = sc.nextLine();
        System.out.println("Please Enter Quantity");
        String qty = sc.nextLine();

        Grocerymodel gm = new Grocerymodel();
        gm.setGdate(dtf.format(now));
        gm.setItem(item);
        gm.setQuantity(qty);
        try {
            Service sq = new Service(this.dBconnect);
            sq.sqlInsert(gm);
            System.out.println("Saved successfully !!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        display();
    }

    public void list(boolean doDisplay) {
        System.out.println("Groceries List");
        try {
            Service sq = new Service(this.dBconnect);
            sq.sqlDisplay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (doDisplay)
            display();
    }

    public void update() {
        System.out.println("Update Groceries");
        list(false);
        Grocerymodel gitem = new Grocerymodel();
        System.out.println("Please enter id to update");
        Scanner sc = new Scanner(System.in);
        Integer id = sc.nextInt();
        try {
            Service sq = new Service(this.dBconnect);
            gitem = sq.getItem(id);
            if (gitem != null)
                System.out.println(gitem.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gitem != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter Item (enter to skip)");
            String item = scanner.nextLine();
            System.out.println("Please Enter Quantity (enter to skip)");
            String qty = scanner.nextLine();
            if (!item.isEmpty()) {
                gitem.setItem(item);
            }
            gitem.setGdate(dtf.format(now));
            if (!qty.isEmpty()) {
                gitem.setQuantity(qty);
            }
            try {
                Service sq = new Service(this.dBconnect);
                sq.sqlUpdate(gitem);
                System.out.println("Updated successfully !!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Id not found");
        }
        display();
    }

    public void delete() {
        System.out.println("Delete Groceries");
        list(false);
        System.out.println("Please enter id to delete");
        Scanner sc = new Scanner(System.in);
        Integer id = sc.nextInt();
        try {
            Service sq = new Service(this.dBconnect);
            sq.deleteItem(id);
            System.out.println("Deleted successfully !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        display();
    }

    public void closeDB() {
        this.dBconnect.closeConnection();
    }

    public void display() {
        System.out.println("1. Add");
        System.out.println("2. List");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Exit");
        System.out.println("Please Enter Your Choice:");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                add();
                break;
            case "2":
                list(true);
                break;
            case "3":
                update();
                break;
            case "4":
                delete();
                break;
            case "5":
                this.closeDB();
                System.exit(0);
                break;
            default:
                this.closeDB();
                System.out.println("Wrong input");
        }
    }
}
