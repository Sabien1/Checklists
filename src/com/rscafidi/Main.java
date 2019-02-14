package com.rscafidi;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<User> users = new ArrayList<User>();
    public static void main(String[] args) {
        String name;
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Checklists Program");
        System.out.println("Design by Jose Farias");
        System.out.println("Implementation by Richard Scafidi");
        System.out.println();
        System.out.println("Enter your user name to start.");
        name = in.nextLine();
        User currentUser = new User(name);
        System.out.println("Welcome, " + currentUser.userName);
        users.add(currentUser);
        printMenu(currentUser);
    }

    public static CheckList searchListByName(@NotNull User user, String name) {
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

    public static void listUsers() {
        int size = users.size();
        User currentUser;
        System.out.println("Current Users:");
        for (int i = 0; i < size; ++i){
            System.out.println((users.get(i).userName));

        }
    }

    public static User getUserByName(String name) {
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

    public static void printMenu(User user) {
        Scanner in = new Scanner(System.in);
        Integer answer = -1;
        String nameAnswer = "";
        System.out.println("Main Menu");
        while (answer != 0) {

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
            if (in.hasNextInt()){
                answer = in.nextInt();
            }
            else {
                in.nextLine();
            }
            switch(answer){
                case 1:
                    //Display lists
                    user.printLists();
                    break;
                case 2:
                    in.nextLine();
                    //Edit a list
                    user.printLists();
                    System.out.println("Enter the name of the list you want to edit.");
                    nameAnswer=in.nextLine();
                    CheckList searchResult = searchListByName(user, nameAnswer);
                    if (searchResult == null) {
                        System.out.println("The list '" + nameAnswer + "' does not exist.");
                    }
                    else {
                        user.editList(searchResult);
                    }
                    break;
                case 3:
                    // Create a list
                    in.nextLine();
                    System.out.println("Enter list name:");
                    nameAnswer = in.nextLine();
                    user.createList(nameAnswer, 1);
                    break;
                case 4:
                    //Remove a list
                    in.nextLine();
                    user.printLists();
                    System.out.println("Enter list name to remove:");
                    nameAnswer = in.nextLine();
                    if (user.lists.contains(searchListByName(user, nameAnswer))) {
                        user.lists.remove(searchListByName(user, nameAnswer));
                    }
                    else {
                        System.out.println("Invalid list name.  List does not exist.");
                    }
                    break;
                case 5:
                    //Add a user to system
                    listUsers();
                    in.nextLine();
                    System.out.println("Enter the new user name:");
                    nameAnswer = in.nextLine();
                    users.add(new User(nameAnswer));
                    System.out.println("User " + nameAnswer + " added.");

                    break;
                case 6:
                    //remove user
                    in.nextLine();
                    listUsers();
                    System.out.println("Enter user name to remove:");
                    nameAnswer = in.nextLine();
                    users.remove(getUserByName(nameAnswer));
                    break;
                case 7:
                    //Switch user
                    break;
                case 8:
                    //TEST settings
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
