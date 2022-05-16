/*
 *  Copyright 2021 Jan Darge
 */

package warehouse.build;

import java.io.*;
import java.util.LinkedList;

public class FileHTML {

    public static void loadHTML(LinkedList<Item> items, File file) throws FileNotFoundException {
        // take in a linked list of items and a file
        // create a stringBuilder and file reader
        // try to read the line in the file
        // remove garbage from the file
        // append the good data to the string builder
        // after loop is done running, make a string array splitting new lines
        // call createItemsHTML from the string array content

        StringBuilder html = new StringBuilder();
        FileReader fr = new FileReader(file);

        try {

            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {

                if (htmlRemoveExtra(line)) {
                    line = reformatInputHTML(line);
                    html.append(line).append("\n");
                }
            }

            br.close();

            String[] data = html.toString().split("\n");

            createItemsHTML(items, data);

        } catch (Exception ignore) {
        }

    }

    private static String reformatInputHTML(String line) {
        // take in a string
        // remove garbage from string
        // return string

        line = line.replace("<td>", "");
        line = line.replace("</td>", "");
        line = line.replace("\t", "");

        return line;
    }

    private static void createItemsHTML(LinkedList<Item> items, String[] lines) {
        // take in a linked list and a string array w/ good content (from an html file)
        // for every item in the string array...
        // try to create an item w/ the data if valid
        // add valid item to the linked list

        for (int i = 0; i < lines.length; i = i + 3) {
            String value = lines[i];
            String sn = lines[(i + 1)];
            String name = lines[(i + 2)];

            if (GeneralUtility.checkSerialNumber(items, sn) && GeneralUtility.priceCheck(value) && GeneralUtility.nameCheck(name)) {
                items.add(new Item(sn, name, GeneralUtility.priceFormat(value)));
            }
        }
    }

    private static boolean htmlRemoveExtra(String line) {
        // take in a string
        // check to see if the string contains information we care about

        return line.contains("<td>");
    }

    public static void saveHTML(LinkedList<Item> items, File file) throws FileNotFoundException {
        // take in a linked list of items and a file
        // create a print writer
        // write basic html document things
        // for every item in the linked list...
        // add the item to the file w/ proper formatting
        // finish writing html formatting

        PrintWriter pw = new PrintWriter(file);

        pw.write("""
                <!DOCTYPE html>
                <html>
                <head>
                    <style>
                            table {
                                font-family: arial, sans-serif;
                                border-collapse: collapse;
                                width: 100%;
                            }

                            td, th {
                                border: 1px solid #000000;
                                text-align: left;
                                padding: 4px;
                            }

                            tr:nth-child(even) {
                                background-color: #ffffff;
                            }
                    </style>
                </head>
                <body>

                <h2>ITEM TABLE</h2>

                <table>
                    <tr>
                \t\t<th>Value</th>
                \t\t<th>Serial Number</th>
                \t\t<th>Item Name</th>
                    </tr>
                    """);

        for (Item item : items) {

            String price = GeneralUtility.priceDeformat(item.getValue());
            pw.write("\t<tr>\n");
            pw.write("\t\t<td>" + price + "</td>\n");
            pw.write("\t\t<td>" + item.getSerial_number() + "</td>\n");
            pw.write("\t\t<td>" + item.getName() + "</td>\n");
            pw.write("\t</tr>\n");
        }

        pw.write("""
                </table>

                </body>
                </html>""");

        pw.close();
    }
}
