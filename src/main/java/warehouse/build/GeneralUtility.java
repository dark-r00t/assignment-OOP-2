/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Jan Darge
 */

package warehouse.build;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.LinkedList;

public class GeneralUtility {

    public static String userDir() {
        // create a user/Documents path string
        // return the string

        String path = System.getProperty("user.home");

        if (Files.exists(Path.of(path + "\\Documents"))) {
            path = path + "\\Documents";
        }

        return path;
    }

    public static Item addItem(LinkedList<Item> items, String sn, String name, String price) {
        // take in a linked list of items, sn string, name string, price string
        // if the item is valid
        // return a new item w/ given data
        // otherwise return BAD data

        if (addItemInit(items, sn, name, price)) {
            return new Item(sn, name, priceFormat(price));
        } else {
            return new Item("bad", "bad", "price");
        }
    }

    public static boolean addItemInit(LinkedList<Item> items, String sn, String name, String price) {
        // take in a linked list of items, sn string, name string, price string
        // if serial number is bad return false
        // if price check is bad return false
        // if name check is bad return false

        if (!checkSerialNumberInit(items, sn)) {
            return false;
        }

        if (!priceCheck(price)) {
            return false;
        }

        return nameCheck(name);
    }

    public static void deleteItem(LinkedList<Item> list, Item item) {
        // take in a linked list of item and an item
        // for every item in the linked list,
        // check to see there's a match between the two item's
        // if there is a match remove that specific item from the list

        for (Item target : list) {
            if (target == item) {
                list.remove(item);
            }
        }
    }

    public static void updateItemSerialNumber(LinkedList<Item> items, Item item, String sn) {
        // take in a linked list of items an item and a new serial number string
        // if the new serial number is invalid return
        // otherwise update the items serial number

        if (!checkSerialNumberInit(items, sn)) {
            return;
        }

        item.setSerial_number(sn);
    }

    public static void updateItemName(Item item, String name) {
        // take in a linked list of items an item and a new name string
        // if the new name is invalid return
        // otherwise update the items name

        if (!nameCheck(name)) {
            return;
        }

        item.setName(name);
    }

    public static void updateItemValue(Item item, String value) {
        // take in a linked list of items an item and a new value string
        // if the new value is invalid return
        // otherwise update the items value

        if (!priceCheck(value)) {
            return;
        }

        item.setValue(priceFormat(value));
    }

    public static boolean nameCheck(String name) {
        // take in a string
        // if the name is less than 2 or greater than 256 return false
        // otherwise return true

        return name.length() <= 256 && name.length() >= 2;
    }

    public static boolean priceCheck(String price) {
        // take in a price string
        // try to parse the string
        // if it's fails to parse display a help window AND return false
        // if it passes return true

        try {
            Double.parseDouble(price);
        } catch (Exception ignore) {
            SceneController.userHelp("PriceHelp.fxml");
            return false;
        }

        return true;
    }

    public static boolean tryDoubleParse(String new_value) {
        // take in a price string
        // try to parse the string
        // if it's fails to parse ONLY return false
        // if it passes return true

        try {
            Double.parseDouble(new_value);
        } catch (Exception ignore) {
            return false;
        }

        return true;
    }

    public static String priceFormat(String price) {
        // take in a price string
        // format the string into usd format
        // return updated string

        NumberFormat usd = NumberFormat.getCurrencyInstance();

        return usd.format(Double.parseDouble(price));
    }

    public static boolean checkSerialNumberInit(LinkedList<Item> items, String sn) {
        // take in a linked list of item and a serial number string
        // check to see if the serial number is valid
        // if not... display help window AND return false
        // if so... return true

        try {
            if (!GeneralUtility.checkSerialNumber(items, sn)) {
                SceneController.userHelp("SerialNumberHelp.fxml");
                return false;
            }
        } catch (Exception ignore) {
        }

        return true;
    }

    public static boolean checkSerialNumber(LinkedList<Item> items, String sn) {
        // take in a linked list of items and a serial number string
        // if the length isnt 10 return false
        // check if every character in the string is either a digit or a letter, if not return false
        // check if the serial number is already within the linked list, if so return false
        // otherwise return true

        if (sn.length() != 10) {
            return false;
        }

        for (int i = 0; i < sn.length(); i++) {
            if (!Character.isLetterOrDigit(sn.charAt(i))) {
                return false;
            }
        }

        for (Item item : items) {
            if (item.getSerial_number().equalsIgnoreCase(sn)) {
                return false;
            }
        }

        return true;
    }

    public static String priceDeformat(String price) {
        // take in a price string
        // replace the dollar sign w/ a blank

        return price.replace("$", "");
    }

    public static LinkedList<Item> searchForItem(LinkedList<Item> items, String text) {
        // take in a linked list of item and a text string
        // create a linked list called search
        // for every item in the original linked list...
        // if the item's serial number of name mathces the text string add the item to the search linked list
        // return the search linked list

        LinkedList<Item> search = new LinkedList<>();

        for (Item item : items) {
            if (item.getSerial_number().contains(text) || item.getName().contains(text)) {
                search.add(item);
            }
        }

        return search;
    }
}
