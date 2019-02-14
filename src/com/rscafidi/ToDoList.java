package com.rscafidi;

public class ToDoList extends CheckList {

    Integer priority;
    String users;
    public ToDoList(String listName) {
        super(listName);
    }


    public ToDoList(String listName, Integer priority, String user) {
        this.name = listName;
        this.priority = priority;
        this.users = user;
    }

}
