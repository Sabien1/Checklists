package com.rscafidi;

public class ItemFactory {

    public ItemFactory ItemFactory() {
        return new ItemFactory();
    }

    public Item createItem(String type) {
        if (type.equals("ShoppingItem")) {
            return new Item();
        }
        else if (type.equals("ToDoItem")) {
            return new ToDoItem();
        }
        return null;
    }
}
