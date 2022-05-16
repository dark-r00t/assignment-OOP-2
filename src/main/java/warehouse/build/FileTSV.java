/*
 *  Copyright 2021 Jan Darge
 */

package warehouse.build;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class FileTSV extends FileHandler {

    public static void loadTSV(LinkedList<Item> items, File file) {
        // take in a linked list and file
        // great a reader and string to hold file lines
        // take in a line and split each instance of a tab into different index's of a string array
        // try to create the item w/ the data received if and only if it is valid
        // add valid item to the list

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] id = line.split("\t");

                if (GeneralUtility.addItemInit(items, id[0], id[1], id[2])) {
                    items.add(new Item(id[0], id[1], GeneralUtility.priceFormat(id[2])));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTSV(LinkedList<Item> items, File file) throws IOException {
        // tank in a linked list and file
        // create print writer
        // for every item in the linked list...
        // deformat the price (remove $)
        // write each of the item's value's separated by a tab
        // remove extra new line

        PrintWriter pw = new PrintWriter(file);

        for (Item item : items) {

            String price = GeneralUtility.priceDeformat(item.getValue());

            pw.write(item.getSerial_number() + "\t" + item.getName() + "\t" + price + "\n");
        }

        pw.close();

        removeNewLine(file);
    }

    private static void removeNewLine(File file) throws IOException {
        // take in a file
        // save file content as a string
        // reduce length by one
        // overwrite file w/ updated length

        String content = Files.readString(Paths.get(String.valueOf(file)));

        content = content.substring(0, content.length() - 1);

        PrintWriter pw = new PrintWriter(file);
        pw.write(content);
        pw.close();
    }
}
