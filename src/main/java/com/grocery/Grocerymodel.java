package com.grocery;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

import java.sql.Date;

public class Grocerymodel {
    private int id;
    private String gdate ;
    private String item;
    private String quantity;
    public String getGdate() {
        return gdate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setGdate(String gdate) {
        this.gdate = gdate;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Grocerymodel [gdate=" + gdate + ", id=" + id + ", item=" + item + ", quantity=" + quantity + "]";
    }
    
}

