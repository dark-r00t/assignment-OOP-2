/*
 *  Copyright 2021 Jan Darge
 */

package warehouse.build;

public class Item {
    // create 3 string
    // - serial_number
    // - name
    // - value
    // create getter/setters
    // create constructor
    // make a toString method for testing

    public String serial_number;
    public String name;
    public String value;

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Item(String serial_number, String name, String value) {
        this.serial_number = serial_number;
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "-|-|> SN: " + serial_number + " | NAME: " + name + " | VALUE: " + value + " ";
    }
}
