/*
 *  Copyright 2021 Jan Darge
 */

package warehouse.build;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class FileJSON {

    public static void loadJSON(LinkedList<Item> items, File file) {
        // take in a linked list of items and a file
        // create a JSON parser
        // try to parse the file
        // create a json array for "items"
        // for every object in the array...
        // parse the keys into strings and try to create the object (if valid)
        // format value
        // add item to linked list

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray array = (JSONArray) jsonObject.get("items");

            for (Object o : array) {

                JSONObject item = (JSONObject) o;

                String sn = (String) item.get("sn");
                String name = (String) item.get("name");
                String value = (String) item.get("value");

                if (GeneralUtility.addItemInit(items, sn, name, value)) {
                    items.add(new Item(sn, name, GeneralUtility.priceFormat(value)));
                }
            }

        } catch (Exception ignore) {
        }
    }

    public static void saveJSON(LinkedList<Item> items, File file) throws FileNotFoundException {
        // take in a linked list of item and a file
        // create a print write
        // write the expected json file format for every single item in the linked list
        // make sure to call priceDeformat on the price of the item (remove $)

        PrintWriter pw = new PrintWriter(file);

        pw.write("{\n\t\"items\" : \n\t[\n");

        for (Item item : items) {

            String price = GeneralUtility.priceDeformat(item.getValue());

            pw.write("\t\t{\n\t\t\t");
            pw.write("\"sn\" : \"" + item.getSerial_number() + "\",\n");
            pw.write("\t\t\t\"name\" : \"" + item.getName() + "\",\n");
            pw.write("\t\t\t\"value\" : \"" + price + "\"\n");

            if (items.get(items.size() - 1).equals(item)) {
                pw.write("\t\t}\n");
            } else {
                pw.write("\t\t},\n");
            }
        }

        pw.write("\t]\n}");

        pw.close();
    }
}
