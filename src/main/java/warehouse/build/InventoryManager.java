/*
 *  Copyright 2021 Jan Darge
 */

package warehouse.build;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/*

Write a program that tracks your personal inventory.
The program should allow you to enter an item, a serial number, and estimated value.
The program should then be able to display a tabular report of the data that looks like this:

Value     Serial Number   Name
$399.00   AXB124AXY       Xbox One
$599.99   S40AZBDE4       Samsung TV

The program should also allow the user to export the data as either a tab-separated value (TSV) file, or as a HTML file.
When exported as an HTML file, the data should be stored inside of a table structure to mimic the displayed appearance.
Constraints

    Ensure that your application meets the following requirements specification:

        The user shall interact with the application through a Graphical User Interface
        The user shall be able to store at least 100 inventory items
        The user shall be able to add a new inventory item
        The user shall be able to remove an existing inventory item

        The user shall be able to edit the value of an existing inventory item
        The user shall be able to edit the serial number of an existing inventory item
        The user shall be able to edit the name of an existing inventory item

        The user shall be able to sort the inventory items by value
        The user shall be able to sort inventory items by serial number
        The user shall be able to sort inventory items by name

        The user shall be able to search for an inventory item by serial number
        The user shall be able to search for an inventory item by name

        The user shall be able to save their inventory items to a file
        The user shall provide the file name and file location of the file to save
        The user shall be able to load inventory items from a file that was previously created by the application.
        The user shall provide the file name and file location of the file to load

        Each inventory item shall have a value representing its monetary value in US dollars
        Each inventory item shall have a unique serial number in the format of XXXXXXXXXX where X can be either a letter or digit
        Each inventory item shall have a name consisting of at least 2 characters

        The application shall prevent the user from adding items with an existing serial number.
        The application shall prevent the user from duplicating the serial number
        The user shall be able to select the file format from among the following set of options: TSV, HTML, JSON
            TSV files shall shall list one inventory item per line,
                separate each field within an inventory item using a tab character, and end with the extension .txt
            HTML files shall contain valid HTML and end with the extension .html
            JSON files shall contain valid JSON and end with the extension .json

*/

public class InventoryManager extends Application {

    public static Stage current;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // load and show the main window
        // save current display scene as a public variable

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainWindow.fxml")));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Inventory Manager");
            primaryStage.show();

            current = primaryStage;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
