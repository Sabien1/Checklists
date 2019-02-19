package com.rscafidi;

import java.util.ArrayList;

public class User {
    String userName;
    ArrayList<CheckList> lists = new ArrayList<CheckList>();
    private String LIST_ERROR_MESSAGE = "The list was not created.";
    TaskHistory history = new TaskHistory();

    User(String name) {
        //System.out.println("construct user");
        this.userName = name;

    }

    public void createList(ListDriver driver, String listName, int type){
        if (listName == null || listName.equals("")) {
            System.out.println(LIST_ERROR_MESSAGE + "List Name is required.");
        }
        else {
            switch(type) {
                case 1:
                    ShoppingList s = new ShoppingList(listName);
                    lists.add(s);
                    break;
                case 2:
                    Integer priority;
                    System.out.println("Indicate list priority.");
                    //reuse get menu option to get an integer
                    priority = driver.getMenuOption();
                    ToDoList td = new ToDoList(listName, priority, this.userName);
                    lists.add(td);
                    break;
                case 3:
                    //goal list
                    break;
                case 4:
                    //team list
                    break;
                default:
                    System.out.println(LIST_ERROR_MESSAGE + "No valid list type supplied.");

            }
        }
    }

    public void editList(CheckList list, ListDriver driver) {
        int answer = -1;
        String stringAnswer = "";
        Item currentItem = null;
        System.out.println("EDITING LIST " + list.name);
        System.out.println();
        while (answer != 0) {
            printListItems(list);
            System.out.println("1 - Change List Name");
            System.out.println("2 - Add list item");
            System.out.println("3 - Edit list item");
            System.out.println("4 - Remove list item");
            System.out.println("5 - Add user to list");
            System.out.println("0 - Back to lists menu");
            System.out.println();
            answer = driver.getMenuOption();
            switch(answer){
                case 1:
                    // Change list name
                    System.out.println("Enter new list name:");
                    stringAnswer = driver.getStringInput();
                    list.name = stringAnswer;
                    System.out.println("List name updated to " + stringAnswer);
                    driver.printLists(this);
                    break;
                case 2:
                    // Add item
                    System.out.println("Enter name for list item:");
                    stringAnswer = driver.getStringInput();
                    Item thisItem = new Item(stringAnswer);
                    list.items.add(thisItem);

                    break;
                case 3:
                    // Edit item
                    System.out.println("Enter item name to edit:");
                    stringAnswer = driver.getStringInput();
                    currentItem = list.getCurrentItemBasedOnName(stringAnswer);
                    if (currentItem == null) {
                        System.out.println("The item does not exist.");
                    }
                    else {
                        list.editItem(currentItem, history);
                    }
                    break;
                case 4:
                    // Remove list item
                    System.out.println("Enter item name to delete:");
                    stringAnswer = driver.getStringInput();
                    if (list.items.indexOf(list.getCurrentItemBasedOnName(stringAnswer)) < 0) {
                        System.out.println("The item does not exist.");
                    }
                    else {
                        list.items.remove(list.getCurrentItemBasedOnName(stringAnswer));
                    }
                    break;
                case 5:
                    // Add user to list
                    break;
                case 0:
                    // exit
                    break;

                default:
                    System.out.println("The input is not valid.");
                    System.out.println("Choose an option:");

            }
        }
    }

    public void printListItems(CheckList list) {
        if (list == null || list.items.isEmpty()) {
            System.out.println("The list is empty.");
        }
        else {
            System.out.println("Items:");
            int size = list.items.size();
            Item currentItem = null;
            for (int i = 0; i < size; ++i) {
                currentItem = list.items.get(i);
                System.out.println(currentItem.name);
            }
        }
    }


}
