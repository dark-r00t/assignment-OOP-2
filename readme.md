# Inventory Manger GUI

**Version 1.0.0**

#### Inventory manger that supports *tsv, *html, and *json files.
#### The user will be able to add the name of the item, the item's serial number, and the item's value.

#### To run the program in Intellij: please run the Gradle > Application > Run feature (located on the far right tab).

#### Example way of running this program via intellij:
1. Open intellij and create a new gradle project using JDK 16
2. Move all of this content into the project root folder.
3. Gradle -> build -> build (to test errors)

---
# USAGE
### ADD AND DELETE
1. To add an item, simply fill out the 3 text fields ( **Item Serial Number** | **Item Name** | **Item Price** ) then press **Add New Item**
2. Make sure to add an **integer** or **decimal** value into the value field without the ' **$** ' sign.
3. Make sure to include **exactly** "10" digits into the serial number.
4. Make sure to include at **least** "2" letters/digits into the name field or at **most** "256" letters/digits into the name field.
5. To delete an item, **select** the item you wish to delete and then press *Delete A Selected Item*
### EDIT VALUE OF ITEM
1. To edit any value of the item, **double-click** the item and field to change.
2. **Edit** the text field and then press "**Enter**" on your keyboard.
### SEARCH
1. To search for an item, type in the item's name or serial number into the "**Search for item by typing here...**" text field.
### SORT
1. To sort any field, simply click on the field name.
2. The sort type will either be **ascending** or **descending** order.
3. You will be able to tell the order based on the arrow presented to the **right** of the table column's name.
### FILE
1. To create a new list, click "**File**" and then click "**New**"
2. To load a previous list, click "**File**" and then click "**Load...**"
3. To save the current list, click "**File**" and then click "**Save As...**"
4. To quit the application, click "**File**" and then click "**Quit**"
### SAVE
1. You can select different file formats to save you file to.
### LOAD
1. You can select any *tsv, *json, or *html file to load; but, you can only select one file.
---

## License & Copyright ##
#### Copyright 2021 Jan Darge
