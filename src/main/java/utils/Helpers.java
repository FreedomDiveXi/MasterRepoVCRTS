package utils;

import java.util.ArrayList;

public class Helpers {

    /**
     * Method meant to add an item to a list, used a genric so it can be applied
     * anywhere
     *
     * @param list
     * @param item
     * @return the list so that we can continue method chaining
     */
    public static ArrayList<Object> addToList(ArrayList<Object> list, Object item) {
        list.add(item);
        return list;
    }

    /**
     * Method will remove an item from the list based on a integer quantifier
     *
     * @param list
     * @param item
     * @return the list so that we can continue method chaining
     */
    public static ArrayList<Object> removeItemFromList(ArrayList<Object> list, Object item, int number) {
        return list;
    }


}
