package org.mptelkom.administrator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.mptelkom.administrator.App;

public class MainMenu {
    @FXML
    public Button keanggotaanNav;

    public void gotoKeanggotaan(ActionEvent actionEvent) {
        App app = App.getInstance();

        app.gotoKeanggotaan();
    }
}
