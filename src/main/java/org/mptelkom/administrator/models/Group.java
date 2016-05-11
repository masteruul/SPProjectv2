package org.mptelkom.administrator.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "groups")
public class Group {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String nama;
    @ForeignCollectionField
    private ForeignCollection<Person> members;

    Group() {

    }

    public Group(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public ForeignCollection<Person> getMembers() {
        return members;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
