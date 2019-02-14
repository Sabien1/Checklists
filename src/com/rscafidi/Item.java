package com.rscafidi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    String name;
    Boolean completed = false;
    Long timeCompleted = null;

    public Item(String name){
        this.name = name;
    }

    public void setCompleted() {
        this.timeCompleted = System.currentTimeMillis();
    }

    public void printItemDetails() {

        System.out.println("\t" + "Name: " + this.name);
        System.out.println("\t" + "Completed: " + this.completed);
        if (!(timeCompleted == null)) {
            Date date = new Date(this.timeCompleted);
            DateFormat formatter = new SimpleDateFormat("hh:mm a, MMMMM DD, YYYY");
            String dateFormatted = formatter.format(date);
            System.out.println("\t" + "Date Completed: " + dateFormatted);
        }
    }
}
