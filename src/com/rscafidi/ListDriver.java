package com.rscafidi;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

public class ListDriver {


    long TIMER_LONG = 864000000;
    long TIMER_SHORT = 60000;
    //set timerToggle to true for 1 day deletion
    boolean timerToggle = true;
    // users array holds all the current users
    ArrayList<User> users = new ArrayList<User>();
    public CheckList searchListByName(User user, String name) {
        int size = user.lists.size();
        CheckList currentList;
        for (int i = 0; i < size; ++i) {
            currentList = user.lists.get(i);
            if (currentList.name.equals(name)) {
                return currentList;
            }

        }
        return null;
    }

    public void listUsers() {
        int size = users.size();
        System.out.println("Current Users:");
        for (int i = 0; i < size; ++i){
            System.out.println((users.get(i).userName));

        }
    }

    public User getUserByName(String name) {
        User currentUser = null;
        int size = users.size();
        for (int i = 0; i < size; ++i) {
            currentUser = users.get(i);
            if (currentUser.userName.equals(name)){
                return currentUser;
            }
        }

        return null;
    }

    public void printLists(User user) {
        if (user.lists == null || user.lists.isEmpty()) {
            System.out.println("There are no lists to display.");
        }
        else {
            System.out.println("Lists:");
            int size = user.lists.size();
            CheckList currentList;
            Item currentItem;
            for (int i = 0; i < size; ++i) {
                currentList = user.lists.get(i);
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

    public int getMenuOption(){
        Scanner in = new Scanner(System.in);
        int answer = -1;
        if (in.hasNextInt()){
            answer = in.nextInt();
        }
        else {
            in.nextLine();
        }
        return answer;
    }

    public String getStringInput() {
        Scanner in = new Scanner(System.in);
        String answer = "";
        if (in.hasNextLine()){
            answer = in.nextLine();
        }
        return answer;
    }

    public Pair<String, Integer> getListTypeAndName() {

        System.out.println("Select type of list to add:");
        System.out.println();
        System.out.println("1 - Shopping list");
        System.out.println("2 - Todo list");
        System.out.println("3 - Goal list");
        System.out.println("4 - Team list");
        System.out.println();
        Integer menuOption = getMenuOption();
        System.out.println("Enter list name:");
        String nameAnswer = getStringInput();
        return new Pair<String, Integer>(nameAnswer, menuOption);
    }



    public void printMenu(User user) {
        User activeUser = user;
        int answer = -1;
        String nameAnswer = "";
        System.out.println("Main Menu");
        while (answer != 0) {
            if (activeUser == null) {
                System.out.println("No active user.  Enter user to switch or create.");
                nameAnswer = getStringInput();
                activeUser = getUserByName(nameAnswer);
                if (users.indexOf(getUserByName(nameAnswer)) < 0) {
                    activeUser = new User(nameAnswer);
                    System.out.println("User " + nameAnswer + " does not exist.  Created new user.");
                }
            }
            System.out.println("User " + activeUser.userName + ":");
            System.out.println("1 - Display all lists.");
            System.out.println("2 - Edit a list.");
            System.out.println("3 - Create a list.");
            System.out.println("4 - Remove a list.");
            System.out.println("5 - Add user to system");
            System.out.println("6 - Remove a user from system");
            System.out.println("7 - Switch User");
            System.out.println("8 - TEST settings");
            System.out.println("0 - exit");
            System.out.println();
            System.out.println("Enter menu option: ");
            answer = getMenuOption();
            switch(answer){
                case 1:
                    //Display lists
                    printLists(activeUser);
                    break;
                case 2:
                    //Edit a list
                    printLists(activeUser);
                    System.out.println("Enter the name of the list you want to edit.");
                    nameAnswer = getStringInput();
                    CheckList searchResult = searchListByName(activeUser, nameAnswer);
                    if (searchResult == null) {
                        System.out.println("The list '" + nameAnswer + "' does not exist.");
                    }
                    else {
                        user.editList(searchResult, this);
                    }
                    break;
                case 3:
                    // Create a list
                    Pair<String, Integer> listType = getListTypeAndName();
                    activeUser.createList(this, listType.getKey(), listType.getValue());
                    break;
                case 4:
                    //Remove a list
                    printLists(activeUser);
                    System.out.println("Enter list name to remove:");
                    nameAnswer = getStringInput();
                    if (activeUser.lists.contains(searchListByName(activeUser, nameAnswer))) {
                        activeUser.lists.remove(searchListByName(activeUser, nameAnswer));
                    }
                    else {
                        System.out.println("Invalid list name.  List does not exist.");
                    }
                    break;
                case 5:
                    //Add a user to system
                    listUsers();
                    System.out.println("Enter the new user name:");
                    nameAnswer = getStringInput();
                    users.add(new User(nameAnswer));
                    System.out.println("User " + nameAnswer + " added.");
                    break;
                case 6:
                    //remove user
                    listUsers();
                    System.out.println("Enter user name to remove:");
                    nameAnswer = getStringInput();
                    User selectedUser = getUserByName(nameAnswer);
                    if (selectedUser == null) {
                        System.out.println("That user does not exist.");
                    }
                    else {
                        if (selectedUser == activeUser) {
                            activeUser = null;
                        }
                        else {
                            users.remove(selectedUser);
                        }
                    }
                    break;
                case 7:
                    //Switch user
                    System.out.println("Enter user to switch to.");
                    nameAnswer = getStringInput();
                    if (users.indexOf(getUserByName(nameAnswer)) < 0) {
                        System.out.println("That user does not exist.");
                    }
                    else {
                        activeUser = getUserByName(nameAnswer);
                    }
                    break;
                case 8:
                    //TEST settings
                    System.out.println("Toggle the system task deletion timer from 1 minute to 1 day.");
                    if (timerToggle) {
                        System.out.println("System is currently set to delete after 1 day.");
                    }
                    else {
                        System.out.println("System is currently set to delete after 1 minute.");
                    }
                    System.out.println("1 - Change timer");
                    System.out.println("0 - exit");
                    answer = getMenuOption();
                    if (answer == 1) {
                        timerToggle = !timerToggle;
                        System.out.println("Timer updated.");
                    }
                    break;
                case 0:
                    System.out.println("Ok - exiting program.  Lists will be destroyed.");
                    break;
                default:
                    System.out.println("The input is not valid.");
                    System.out.println("Choose an option:");

            }
        }

    }
}
