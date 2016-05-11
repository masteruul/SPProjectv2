package org.mptelkom.administrator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mptelkom.administrator.App;

public class Login {
    @FXML
    public PasswordField password;
    @FXML
    public TextField user;

    public void mauLogin(ActionEvent actionEvent) {
        String username = user.getText();
        String pass = password.getText();

        App app = App.getInstance();

        app.login(username, pass);
    }
}
