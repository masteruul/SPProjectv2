package org.mptelkom.administrator.controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mptelkom.administrator.DB;
import org.mptelkom.administrator.models.Person;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Anggota implements Initializable {
    @FXML
    private TableView<TableHolder> tableAnggota;
    @FXML
    private TableColumn<TableHolder, String> nimColumn;
    @FXML
    private TableColumn<TableHolder, String> namaColumn;
    @FXML
    private TableColumn<TableHolder, String> ttlColumn;
    @FXML
    private TableColumn<TableHolder, String> alamatColumn;
    @FXML
    private TableColumn<TableHolder, String> hpColumn;
    @FXML
    private TableColumn<TableHolder, String> lineColumn;
    @FXML
    private TableColumn<TableHolder, String> jurusanColumn;

    private ObservableList<TableHolder> members = FXCollections.observableArrayList();

    private void getData() {
        JdbcConnectionSource connection = null;
        List<Person> listPerson = new ArrayList<Person>();
        try {
            connection = new JdbcConnectionSource(DB.DB_URL);
            Dao<Person, Integer> personDao = DaoManager.createDao(connection, Person.class);
            QueryBuilder<Person, Integer> query = personDao.queryBuilder();
            listPerson = personDao.query(query.where().eq("group_id", 2).prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        for (Person p : listPerson) {
            members.add(new TableHolder(p.getNim(), p.getNama(), p.getTtl(), p.getAlamat(), p.getIdLine(), p.getJurusan(), p.getNohp()));
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        alamatColumn.setCellValueFactory(new PropertyValueFactory<TableHolder, String>("alamat"));
        hpColumn.setCellValueFactory(new PropertyValueFactory<TableHolder, String>("nohp"));
        jurusanColumn.setCellValueFactory(new PropertyValueFactory<TableHolder, String>("jurusan"));
        lineColumn.setCellValueFactory(new PropertyValueFactory<TableHolder, String>("id_line"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<TableHolder, String>("nama"));
        nimColumn.setCellValueFactory(new PropertyValueFactory<TableHolder, String>("nim"));
        ttlColumn.setCellValueFactory(new PropertyValueFactory<TableHolder, String>("ttl"));
        tableAnggota.setItems(this.members);
        this.getData();
    }

    public class TableHolder {
        public SimpleStringProperty nim = new SimpleStringProperty();
        public SimpleStringProperty nama = new SimpleStringProperty();
        public SimpleStringProperty ttl = new SimpleStringProperty();
        public SimpleStringProperty alamat = new SimpleStringProperty();
        public SimpleStringProperty id_line = new SimpleStringProperty();
        public SimpleStringProperty jurusan = new SimpleStringProperty();
        public SimpleStringProperty nohp = new SimpleStringProperty();

        public TableHolder(
                String nim,
                String nama,
                String ttl,
                String alamat,
                String id_line,
                String jurusan,
                String nohp
        ) {
            this.nim.set(nim);
            this.nama.set(nama);
            this.ttl.set(ttl);
            this.alamat.set(alamat);
            this.id_line.set(id_line);
            this.jurusan.set(jurusan);
            this.nohp.set(nohp);
        }

        public String getNohp() {
            return nohp.get();
        }

        public SimpleStringProperty nohpProperty() {
            return nohp;
        }

        public String getNim() {
            return nim.get();
        }

        public SimpleStringProperty nimProperty() {
            return nim;
        }

        public String getNama() {
            return nama.get();
        }

        public SimpleStringProperty namaProperty() {
            return nama;
        }

        public String getTtl() {
            return ttl.get();
        }

        public SimpleStringProperty ttlProperty() {
            return ttl;
        }

        public String getAlamat() {
            return alamat.get();
        }

        public SimpleStringProperty alamatProperty() {
            return alamat;
        }

        public String getId_line() {
            return id_line.get();
        }

        public SimpleStringProperty id_lineProperty() {
            return id_line;
        }

        public String getJurusan() {
            return jurusan.get();
        }

        public SimpleStringProperty jurusanProperty() {
            return jurusan;
        }
    }
}
