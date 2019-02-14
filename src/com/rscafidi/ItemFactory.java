package com.rscafidi;

public class ItemFactory {

    public Item createItem(String type, String name) {
        if (type.equals("ShoppingItem")) {
            return new Item(name);
        }
        else if (type.equals("ToDoItem")) {
            return new ToDoItem(name);
        }
        return null;
    }
}
