/*
 *  Copyright 2021 Jan Darge
 */

import org.junit.jupiter.api.Test;
import warehouse.build.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.fail;

public class InventoryManagerTest {

    @Test
    void one_hundred_items_test() {
        // create linked list
        // fill up linked list with items
        // check if the size is greater than 100

        LinkedList<Item> list = new LinkedList<>();

        for (int i = 0; i < 150; i++) {
            StringBuilder sn = new StringBuilder(String.valueOf(i));
            String name = "name";
            String price = "1";

            while (sn.length() != 10) {
                sn.append("x");
            }

            list.add(GeneralUtility.addItem(list, sn.toString(), name, price));
        }

        if (list.size() < 100) {
            fail();
        }
    }

    @Test
    void us_monetary_value_test() {
        // create linked list
        // create item
        // check if the item has a USD format

        LinkedList<Item> list = new LinkedList<>();

        String sn = "xxxxxxxxxx";
        String name = "name";
        String price = "1";

        Item item = GeneralUtility.addItem(list, sn, name, price);

        if (!item.getValue().equalsIgnoreCase("$1.00")) {
            fail();
        }
    }

    @Test
    void unique_serial_number_test() {
        // create a linked list
        // create an item
        // try to reuse the same serial number from the previous item
        // if it can use that serial number
        //  fail()

        LinkedList<Item> list = new LinkedList<>();

        String sn = "xxxxxxxxxx";
        String name = "name";
        String price = "1";

        list.add(GeneralUtility.addItem(list, sn, name, price));

        String check = "xxxxxxxxxx";

        if (GeneralUtility.checkSerialNumber(list, check)) {
            fail();
        }
    }

    @Test
    void name_length_check() {
        // create a string w/ length 1
        // check if the name is allowed
        // add letters to the string
        // check if the length is the allowed
        // test if we can use that string

        StringBuilder name = new StringBuilder();

        name.append("a");

        if (GeneralUtility.nameCheck(name.toString())) {
            fail();
        }

        name.append("a".repeat(300));

        if (name.toString().length() < 256) {
            fail();
        }

        if (GeneralUtility.nameCheck(name.toString())) {
            fail();
        }
    }

    @Test
    void add_item_test() {
        // create an item w/ random data
        // check to see if the item exists
        // check to see if the item contains the expected information

        LinkedList<Item> list = new LinkedList<>();

        String sn = "xxxxxxxxxx";
        String name = "name";
        String price = "1";

        Item item = GeneralUtility.addItem(list, sn, name, price);

        String item_sn = item.getSerial_number();
        String item_name = item.getName();
        String item_price = item.getValue();

        if (!item_price.equalsIgnoreCase(GeneralUtility.priceFormat(price))) {
            fail();
        }

        if (!item_name.equalsIgnoreCase(name)) {
            fail();
        }

        if (!item_sn.equalsIgnoreCase(sn)) {
            fail();
        }
    }

    @Test
    void error_serial_number_test() {
        // create a linked list
        // create an item
        // try to reuse the same serial number from the previous item
        // if it can use that serial number
        //  fail()

        LinkedList<Item> list = new LinkedList<>();

        String sn = "xxxxxxxxxx";
        String name = "name";
        String price = "1";

        list.add(GeneralUtility.addItem(list, sn, name, price));

        String check = "xxxxxxxxxx";

        if (GeneralUtility.checkSerialNumber(list, check)) {
            fail();
        }

        /*
         *
         * NOTE:
         * TO TEST THE POP-UP ERROR MESSAGE FEATURE...
         * RUN THE APPLICATION AND TRY TO INPUT AN INVALID SERIAL NUMBER
         * (i.e. "x" or "x@!xxx@!xx" or "12345678910")
         *
         * */
    }

    @Test
    void remove_item_test() {
        // create a list
        // add 2 items to the list
        // check if the lsit holds 2 items
        // remove an item
        // check if the list length is now 1

        LinkedList<Item> list = new LinkedList<>();

        Item remove = null;

        for (int i = 0; i < 2; i++) {
            StringBuilder sn = new StringBuilder(String.valueOf(i));
            String name = "name";
            String price = "1";

            while (sn.length() != 10) {
                sn.append("x");
            }

            Item add = GeneralUtility.addItem(list, sn.toString(), name, price);

            list.add(add);

            remove = add;
        }

        if (list.size() != 2) {
            fail();
        }

        GeneralUtility.deleteItem(list, remove);

        if (list.size() != 1) {
            fail();
        }
    }

    @Test
    void search_sn() {
        // create two items
        // add two items to a linked list
        // create a search key
        // call searchForItem and save result into a new linked list
        // take new linked list and check results

        LinkedList<Item> list = new LinkedList<>();

        for (int i = 1; i <= 2; i++) {
            StringBuilder sn = new StringBuilder(String.valueOf(i));
            String name = "name" + i;
            String price = "1";

            while (sn.length() != 10) {
                sn.append("x");
            }

            Item add = GeneralUtility.addItem(list, sn.toString(), name, price);

            list.add(add);
        }

        String search_key = "xxxxx";

        LinkedList<Item> search_items = GeneralUtility.searchForItem(list, search_key);

        if (!search_items.get(0).getSerial_number().contains(search_key)) {
            fail();
        }

        if (!search_items.get(1).getSerial_number().contains(search_key)) {
            fail();
        }
    }

    @Test
    void search_name() {
        // create two items
        // add two items to a linked list
        // create a search key
        // call searchForItem and save result into a new linked list
        // take new linked list and check results
        // ensure that theres only one item in linked list
        //  and that the item in the new linked list contains the correct contents

        LinkedList<Item> list = new LinkedList<>();

        for (int i = 1; i <= 2; i++) {
            StringBuilder sn = new StringBuilder(String.valueOf(i));
            String name = "name" + i;
            String price = "1";

            while (sn.length() != 10) {
                sn.append("x");
            }

            Item add = GeneralUtility.addItem(list, sn.toString(), name, price);

            list.add(add);
        }

        String search_key = "name1";

        LinkedList<Item> search_items = GeneralUtility.searchForItem(list, search_key);

        if (!search_items.get(0).getName().equals(search_key)) {
            fail();
        }

        if (search_items.size() > 1) {
            fail();
        }
    }

    @Test
    void sort_value_test() {
        
        /*
         *
         * NOTE:
         * TO TEST THIS FEATURE...
         * CREATE/LOAD A LIST OF MULTIPLE ITEMS
         * CLICK ON THE TYPE IDENTIFIER TAB
         * THIS WILL AUTOMATICALLY SORT ITEMS BASED ON THE CONTEXT
         *
         * i.e. CLICKING ON "Value" WILL SORT VALUES BASED ON ASCENDING OR DESCENDING ORDER
         * YOU CAN VIEW WHICH MODE IT'S IN BY THE ARROW PROVIDED ON THE TAB
         *
         * */
    }

    @Test
    void sort_sn_test() {

        /*
         *
         * NOTE:
         * TO TEST THIS FEATURE...
         * CREATE/LOAD A LIST OF MULTIPLE ITEMS
         * CLICK ON THE TYPE IDENTIFIER TAB
         * THIS WILL AUTOMATICALLY SORT ITEMS BASED ON THE CONTEXT
         *
         * i.e. CLICKING ON "Serial Number" WILL SORT VALUES BASED ON ASCENDING OR DESCENDING ORDER
         * YOU CAN VIEW WHICH MODE IT'S IN BY THE ARROW PROVIDED ON THE TAB
         *
         * */
    }

    @Test
    void sort_name_test() {

        /*
         *
         * NOTE:
         * TO TEST THIS FEATURE...
         * CREATE/LOAD A LIST OF MULTIPLE ITEMS
         * CLICK ON THE TYPE IDENTIFIER TAB
         * THIS WILL AUTOMATICALLY SORT ITEMS BASED ON THE CONTEXT
         *
         * i.e. CLICKING ON "Item Name" WILL SORT VALUES BASED ON ASCENDING OR DESCENDING ORDER
         * YOU CAN VIEW WHICH MODE IT'S IN BY THE ARROW PROVIDED ON THE TAB
         *
         * */
    }

    @Test
    void edit_value_test() {
        // create linked list
        // create a new item
        // try to change the item's value
        // check to see if the change worked

        LinkedList<Item> list = new LinkedList<>();

        String sn = "xxxxxxxxxx";
        String name = "name";
        String price = "1";

        Item item = GeneralUtility.addItem(list, sn, name, price);
        String new_value = "2";

        GeneralUtility.updateItemValue(item, new_value);

        if (!item.getValue().equalsIgnoreCase("$2.00")) {
            fail();
        }
    }

    @Test
    void edit_sn_test() {
        // create linked list
        // create a new item
        // try to change the item's serial number
        // check to see if the change worked

        LinkedList<Item> list = new LinkedList<>();

        String sn = "xxxxxxxxxx";
        String name = "name";
        String price = "1";

        Item item = GeneralUtility.addItem(list, sn, name, price);
        String new_value = "xxxxxxxxxy";

        GeneralUtility.updateItemSerialNumber(list, item, new_value);

        if (!item.getSerial_number().equalsIgnoreCase(new_value)) {
            fail();
        }
    }

    @Test
    void edit_name_test() {

        // create linked list
        // create a new item
        // try to change the item's name
        // check to see if the change worked

        LinkedList<Item> list = new LinkedList<>();

        String sn = "xxxxxxxxxx";
        String name = "name";
        String price = "1";

        Item item = GeneralUtility.addItem(list, sn, name, price);
        String new_value = "xyxyxyxyxy";

        GeneralUtility.updateItemName(item, new_value);

        if (!item.getName().equalsIgnoreCase(new_value)) {
            fail();
        }
    }

    @Test
    void edit_bad_value_test() {
        // create an idea for an invalid input
        // test to see if we can use that input

        String new_value = "two";

        if (GeneralUtility.tryDoubleParse(new_value)) {
            fail();
        }
    }

    @Test
    void edit_bad_sn_test() {
        // create a linked list
        // create a bad serial number
        // see if we can use it

        LinkedList<Item> list = new LinkedList<>();

        String new_value = "bad#";

        if(GeneralUtility.checkSerialNumber(list, new_value)) {
            fail();
        }
    }

    @Test
    void edit_bad_name_test() {
        // create an idea for an invalid input
        // test to see if we can use that input

        String new_value = "x";

        if (GeneralUtility.nameCheck(new_value)) {
            fail();
        }
    }

    @Test
    void save_TSV_test() throws IOException {
        // create temp folder
        // create a linked list of items
        // create a file using the helper_file temp.tsv
        // load the file
        // create a file in .temp/JUNIT.tsv
        // save file using saveTSV
        // check if file exists

        FileHandler.createTempFolder();

        LinkedList<Item> items = new LinkedList<>();

        String path = System.getProperty("user.dir") + "/src/main/resources/helper_files/temp.tsv";
        File file = new File(path);

        FileTSV.loadTSV(items, file);

        String test_path = System.getProperty("user.dir") + "/.temp/JUNIT.tsv";
        File test_file = new File(test_path);

        FileTSV.saveTSV(items, test_file);

        if (!test_file.isFile()) {
            fail();
        }
    }

    @Test
    void save_JSON_test() throws FileNotFoundException {
        // create temp folder
        // create a linked list of items
        // create a file using the helper_file temp.json
        // load the file
        // create a file in .temp/JUNIT.json
        // save file using saveJSON
        // check if file exists

        FileHandler.createTempFolder();

        LinkedList<Item> items = new LinkedList<>();

        String path = System.getProperty("user.dir") + "/src/main/resources/helper_files/temp.json";
        File file = new File(path);

        FileJSON.loadJSON(items, file);

        String test_path = System.getProperty("user.dir") + "/.temp/JUNIT.json";
        File test_file = new File(test_path);

        FileJSON.saveJSON(items, test_file);

        if (!test_file.isFile()) {
            fail();
        }
    }

    @Test
    void save_HTML_test() throws FileNotFoundException {
        // create temp folder
        // create a linked list of items
        // create a file using the helper_file temp.html
        // load the file
        // create a file in .temp/JUNIT.html
        // save file using saveHTML
        // check if file exists

        FileHandler.createTempFolder();

        LinkedList<Item> items = new LinkedList<>();

        String path = System.getProperty("user.dir") + "/src/main/resources/helper_files/temp.html";
        File file = new File(path);

        FileHTML.loadHTML(items, file);

        String test_path = System.getProperty("user.dir") + "/.temp/JUNIT.html";
        File test_file = new File(test_path);

        FileHTML.saveHTML(items, test_file);

        if (!test_file.exists()) {
            fail();
        }
    }

    @Test
    void load_TSV_test() {
        // create a linked list
        // create a file using a helper file (temp.tsv)
        // call loadTSV w/ the created linked list
        // check if there are items w/in the list

        LinkedList<Item> items = new LinkedList<>();

        String path = System.getProperty("user.dir") + "/src/main/resources/helper_files/temp.tsv";
        File file = new File(path);

        FileTSV.loadTSV(items, file);

        if (items.isEmpty()) {
            fail();
        }
    }

    @Test
    void load_JSON_test() {
        // create a linked list
        // create a file using a helper file (temp.json)
        // call loadTSV w/ the created linked list
        // check if there are items w/in the list

        LinkedList<Item> items = new LinkedList<>();

        String path = System.getProperty("user.dir") + "/src/main/resources/helper_files/temp.json";
        File file = new File(path);

        FileJSON.loadJSON(items, file);

        if (items.isEmpty()) {
            fail();
        }
    }

    @Test
    void load_HTML_test() throws FileNotFoundException {
        // create a linked list
        // create a file using a helper file (temp.html)
        // call loadTSV w/ the created linked list
        // check if there are items w/in the list

        LinkedList<Item> items = new LinkedList<>();

        String path = System.getProperty("user.dir") + "/src/main/resources/helper_files/temp.html";
        File file = new File(path);

        FileHTML.loadHTML(items, file);

        if(items.isEmpty()) {
            fail();
        }

    }

}
