package com.rscafidi;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class CheckList {
    String name;
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Item> items = new ArrayList<Item>();


    public CheckList(String listName) {
        this.name = listName;
    }

    public void printItemEditMenu(Item item) {
        System.out.println("Edit Item " + item.name);
        int answer = -1;
        Scanner in = new Scanner(System.in);
        String stringAnswer = "";
        while (answer != 0) {

            System.out.println("1 - Change item name");
            System.out.println("2 - Toggle status");
            System.out.println("0 - exit");
            System.out.println();
            if (in.hasNextInt()) {
                answer = in.nextInt();
            } else {
                in.nextLine();
            }
            switch (answer) {
                case 1:
                    in.nextLine();
                    System.out.println("Enter item name:");
                    stringAnswer = in.nextLine();
                    item.name = stringAnswer;
                    System.out.println("Item name updated.");
                    break;
                case 2:
                    item.completed = !item.completed;
                    System.out.println("Item status is " + item.completed);
                    if (item.completed) {
                        item.setCompleted();
                    }
                    else {
                        item.timeCompleted = null;
                    }
            }
        }
    }

    public void editItem(Item item){
        item.printItemDetails();
        printItemEditMenu(item);

    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void deleteItem(Item item) {
        this.items.remove(item);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public Item getCurrentItemBasedOnName(String name) {
        Item currentItem = null;
        int size = items.size();
        for (int i = 0; i < size; ++i) {
            currentItem = items.get(i);
            if (currentItem.name.equals(name)){
                return currentItem;
            }
        }

        return null;
    }


}
