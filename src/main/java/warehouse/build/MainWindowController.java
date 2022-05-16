/*
 *  Copyright 2021 Jan Darge
 */

package warehouse.build;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.LinkedList;

public class MainWindowController {

    @FXML
    public TableView<Item> itemsTableView;
    @FXML
    public TableColumn<Item, String> itemsSerialNumberColumn;
    @FXML
    public TableColumn<Item, String> itemsNameColumn;
    @FXML
    public TableColumn<Item, String> itemsValueColumn;
    @FXML
    public TextField serialNumberTextField;
    @FXML
    public TextField itemNameTextField;
    @FXML
    public TextField priceTextField;
    @FXML
    public TextField searchTextField;

    public static LinkedList<Item> items = new LinkedList<>();

    @FXML
    public void newButtonClicked() {
        // if the new button is clicked
        // clear everything

        clearEverything(items, itemsTableView);
    }

    @FXML
    public void loadButtonClicked() {
        // call checkTableView()
        // clear everything
        // call the load function
        // for every item generated from load()
        // set the item to be displayed

        checkTableView();

        clearEverything(items, itemsTableView);

        FileHandler.load(items);

        try {
            for (Item item : items) {
                setNewItem(item);
            }
        } catch (Exception ignore) {
        }
    }

    @FXML
    public void saveAsButtonClicked() {
        // call save()

        FileHandler.save(items);
    }

    @FXML
    public void quitButtonClicked() {
        // get current scene and close it

        try {
            InventoryManager.current.close();
        } catch (Exception ignore) {
        }
    }

    @FXML
    public void addItemButtonClicked() {
        // call checkTableView()
        // get all needed text from the text fields
        // try to create an item w/ the data
        // check to see if the item returned from addItem() was BAD
        // if not create the item and set it into the display

        checkTableView();

        String sn = serialNumberTextField.getText();
        String name = itemNameTextField.getText();
        String price = priceTextField.getText();

        Item item = GeneralUtility.addItem(items, sn, name, price);
        if (!item.getSerial_number().equalsIgnoreCase("bad")) {
            setNewItem(item);
            items.add(item);
        }
    }

    private void setNewItem(Item item) {
        // take in an item
        // create a new cell for each of the 3 main columns
        // add item to the tableView

        itemsSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
        itemsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemsValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        itemsTableView.getItems().add(item);
    }

    @FXML
    public void deleteSelectedItemButtonClicked() {
        // get the selected item
        // remove the item from the table
        // call deleteItem() w/ item selected

        Item item = itemsTableView.getSelectionModel().getSelectedItem();

        try {
            itemsTableView.getItems().remove(item);
        } catch (Exception ignore) {
        }

        GeneralUtility.deleteItem(items, item);
    }

    @FXML
    public void keyPressFilterList() {
        // for everytime there is a new character in the textbow
        // clearDisplay()
        // check to see if the box is empty >>> if so display everything
        // create a linked list of all matching items compared to the given text
        // update the table view w/ the items

        clearDisplay(itemsTableView);

        if (searchTextField.getText().equalsIgnoreCase("")) {
            fullDisplay();
            return;
        }

        LinkedList<Item> search = GeneralUtility.searchForItem(items, searchTextField.getText());

        for(Item item : search) {
            itemsTableView.getItems().add(item);
        }
    }

    @FXML
    public void changeSelectedSerialNumber(TableColumn.CellEditEvent cellEditEvent) {
        // get selected item
        // get the new string
        // try to update the item's serial number

        // TODO BAD REFRESH : only works if there's one item, try user deselect or reopen page?

        Item item = itemsTableView.getSelectionModel().getSelectedItem();

        String new_identity = cellEditEvent.getNewValue().toString();

        GeneralUtility.updateItemSerialNumber(items, item, new_identity);
    }

    @FXML
    public void changeSelectedItemName(TableColumn.CellEditEvent cellEditEvent) {
        // get selected item
        // get the new string
        // try to update the item's name

        // TODO BAD REFRESH : only works if there's one item, try user deselect or reopen page?

        Item item = itemsTableView.getSelectionModel().getSelectedItem();

        String new_identity = cellEditEvent.getNewValue().toString();

        GeneralUtility.updateItemName(item, new_identity);
    }

    @FXML
    public void changeSelectedItemValue(TableColumn.CellEditEvent cellEditEvent) {
        // get selected item
        // get the new string
        // try to update the item's value

        // TODO BAD REFRESH : only works if there's one item, try user deselect or reopen page?

        Item item = itemsTableView.getSelectionModel().getSelectedItem();

        String new_identity = cellEditEvent.getNewValue().toString();

        GeneralUtility.updateItemValue(item, new_identity);

        refreshDisplay();
    }

    private void checkTableView() {
        // check to see if the table view is editable
        // if note... make it editable
        // make every column editable as well

        try {
            if (!itemsTableView.isEditable()) {
                itemsTableView.setEditable(true);
                itemsSerialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                itemsNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                itemsValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
        } catch (Exception ignore) {
        }
    }

    public void refreshDisplay() {
        // clear the display of all items
        // display all the items

        clearDisplay(itemsTableView);
        fullDisplay();
    }

    public void fullDisplay() {
        // for every item in the items linked list
        // add the item into the display

        for (Item item : items) {
            itemsTableView.getItems().add(item);
        }
    }

    public static void clearDisplay(TableView<Item> itemsTableView) {
        // so long as there is an item in the table view, remove it

        try {
            while (!itemsTableView.getItems().isEmpty()) {
                itemsTableView.getItems().remove(0);
            }
        } catch (Exception ignore) {
        }
    }

    private static void clearEverything(LinkedList<Item> items, TableView<Item> itemsTableView) {
        // clear everything from the display
        // clear all items from the items linked list

        clearDisplay(itemsTableView);

        try {
            while (!items.isEmpty()) {
                items.remove();
            }
        } catch (Exception ignore) {
        }
    }

}
