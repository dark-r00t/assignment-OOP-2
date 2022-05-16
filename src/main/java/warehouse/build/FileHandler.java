/*
 *  Copyright 2021 Jan Darge
 */

package warehouse.build;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileHandler {

    public static void createTempFolder() {
        // Check to see if there is a file called .temp
        // if there is not create one

        String temp_path = System.getProperty("user.dir") + "/.temp";

        File temp = new File(temp_path);

        if (temp.mkdir()) {
            System.out.print("");
        }
    }

    public static String getExtension(File file) {
        // take in a file
        // save file name as a string
        // see what's behind the files "." to get the file extension
        // return the extension of the file

        String name = file.getName();
        int index = name.lastIndexOf(".");

        return name.substring(index + 1);
    }

    public static void load(LinkedList<Item> items) {
        // take in a list of items
        // create a path string
        // create a file chooser
        // try to open a open dialogue window
        // for the selected file open only one
        // check the file selected extension
        // depending on the extension call a different load function

        String path = GeneralUtility.userDir();

        FileChooser fc = new FileChooser();

        try {

            fc.setInitialDirectory(new File(path));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Item List: ", " *.tsv", " *.json", " *html"));
            List<File> selectedFile = fc.showOpenMultipleDialog(null);

            for (File file : selectedFile) {

                String extension = getExtension(file);

                if (extension.equalsIgnoreCase("tsv")) {
                    FileTSV.loadTSV(items, file);
                } else if (extension.equalsIgnoreCase("json")) {
                    FileJSON.loadJSON(items, file);
                } else {
                    FileHTML.loadHTML(items, file);
                }
                break;
            }

        } catch (Exception ignore) {
        }
    }

    public static void save(LinkedList<Item> items) {
        // take in a linked list of items
        // create a path string
        // create a file chooser
        // show a save dialogue
        // create a file w/ what's selected
        // call saveSelectedFile w/ the linked list and the new file

        String path = GeneralUtility.userDir();
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(new File(path));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TSV: ", " *.tsv"),
                new FileChooser.ExtensionFilter("JSON: ", "*.json"),
                new FileChooser.ExtensionFilter("HTML: ", "*.html")
        );

        File file = fc.showSaveDialog(new Stage());

        saveSelectedFiles(items, file);
    }

    public static void saveSelectedFiles(LinkedList<Item> items, File file) {
        // take in a linked list of items and a file
        // if the file exists...
        // get the extension
        // depending on the extension call a different save function

        if (file != null) {
            try {

                String extension = getExtension(file);

                if (extension.equalsIgnoreCase("tsv")) {
                    FileTSV.saveTSV(items, file);
                } else if (extension.equalsIgnoreCase("json")) {
                    FileJSON.saveJSON(items, file);
                } else {
                    FileHTML.saveHTML(items, file);
                }

            } catch (Exception ignore) {
            }
        }
    }
}
