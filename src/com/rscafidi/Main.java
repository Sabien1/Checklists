package com.rscafidi;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ListDriver driver = new ListDriver();
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
        driver.users.add(currentUser);
        driver.printMenu(currentUser);
    }
}
