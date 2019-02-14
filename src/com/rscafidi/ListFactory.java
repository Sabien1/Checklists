package com.rscafidi;

public class ListFactory {

    public Object createList(String type, String name) {
        if (type.equals("ShoppingList")) {
            return new ShoppingList();
        }

        return null;
    }
}
