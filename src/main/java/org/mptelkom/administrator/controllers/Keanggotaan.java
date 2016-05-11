package org.mptelkom.administrator.controllers;

import javafx.event.ActionEvent;
import org.mptelkom.administrator.App;

public class Keanggotaan {
    public void gotoAnggota(ActionEvent actionEvent) {
        App app = App.getInstance();

        app.gotoKeanggotaanAnggota();
    }
}
