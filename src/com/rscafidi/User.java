package com.rscafidi;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    String userName;
    ArrayList<CheckList> lists = new ArrayList<CheckList>();
    String LIST_ERROR_MESSAGE = "The list was not created.";

    User(String name) {
        //System.out.println("construct user");
        this.userName = name;

    }

    public void createList(String listName, int type){
        if (listName == null || listName == "") {
            System.out.println(LIST_ERROR_MESSAGE + "List Name is required.");
        }
        else {
            switch(type) {
                case 1:
                    ShoppingList s = new ShoppingList(listName);
                    lists.add(s);
                    break;
                case 2:

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

    public void editList(CheckList list) {
        int answer = -1;
        String stringAnswer = "";
        Item currentItem = null;
        Scanner in = new Scanner(System.in);
        System.out.println("EDITING LIST " + list.name);
        System.out.println();
        while (answer != 0) {

            System.out.println("1 - Change List Name");
            System.out.println("2 - Add list item");
            System.out.println("3 - Edit list item");
            System.out.println("4 - Remove list item");
            System.out.println("5 - Add user to list");
            System.out.println("0 - exit");
            System.out.println();
            if (in.hasNextInt()){
                answer = in.nextInt();
            }
            else {
                in.nextLine();
            }
            switch(answer){
                case 1:
                    // Change list name
                    System.out.println("Enter new list name:");
                    stringAnswer = in.next();
                    list.name = stringAnswer;
                    System.out.println("List name updated to " + stringAnswer);
                    printLists();
                    break;
                case 2:
                    // Add item
                    in.nextLine();
                    System.out.println("Enter name for list item:");
                    stringAnswer = in.nextLine();
                    list.items.add(new Item(stringAnswer));
                    printLists();
                    break;
                case 3:
                    // Edit item
                    in.nextLine();
                    printListItems(list);
                    System.out.println("Enter item name to edit:");
                    stringAnswer = in.nextLine();
                    currentItem = list.getCurrentItemBasedOnName(stringAnswer);
                    if (currentItem == null) {
                        System.out.println("The item does not exist.");
                    }
                    else {
                        list.editItem(currentItem);
                    }
                    break;
                case 4:
                    // Remove list item
                    //list.deleteItem();
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

    public void printLists() {
        if (lists == null || lists.isEmpty()) {
            System.out.println("There are no lists to display.");
        }
        else {
            System.out.println("Lists:");
            int size = lists.size();
            CheckList currentList;
            Item currentItem;
            for (int i = 0; i < size; ++i) {
                currentList = lists.get(i);
                System.out.println(currentList.name);

                int listItemSize = currentList.items.size();
                for (int j = 0; j < listItemSize; ++j) {
                    currentItem = currentList.items.get(j);
                    System.out.print('\t' + currentItem.name + '\n');
                }

            }
            System.out.println();
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
