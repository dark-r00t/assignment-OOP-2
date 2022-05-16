/*
 *  Copyright 2021 Jan Darge
 */

package warehouse.build;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {

    public static void userHelp(String type) {
        // take in a string of the fxml file we would like to open
        // depending on the input change the title
        // display the fxml file

        try {

            Parent help = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource(type)));

            Stage stage = new Stage();
            Scene scene = new Scene(help);

            if (type.equalsIgnoreCase("SerialNumberHelp.fxml")) {
                stage.setTitle("Inventory Manager Serial Number Help");
            } else {
                stage.setTitle("Inventory Manager Price Help");
            }

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
