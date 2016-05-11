package org.mptelkom.administrator;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.mptelkom.administrator.models.Admin;
import javafx.application.Application;
import javafx.stage.Stage;
import org.mptelkom.administrator.services.Authenticator;

public class App extends Application {
    private Admin loggedAdmin;
    private Stage stage;

    private static App instance;

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public Stage getStage() {
        return stage;
    }

    public void login(String username, String password) {
        Admin admin = Authenticator.checkLogin(username, password);
        if (admin != null) {
            loggedAdmin = admin;
            gotoMainMenu();
        }
    }

    public static void main(String... args) throws Exception {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        gotoLogin();
        primaryStage.show();
    }

    public void gotoMainMenu() {
        try {
            replaceSceneContent("/fxml/mainmenu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoLogin() {
        try {
            replaceSceneContent("/fxml/login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoKeanggotaan() {
        try {
            replaceSceneContent("/fxml/keanggotaan.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoKeanggotaanAnggota() {
        try {
            replaceSceneContent("/fxml/anggota.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = FXMLLoader.load(App.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }

        stage.sizeToScene();
        return page;
    }
}
