package org.mptelkom.administrator.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "admins")
public class Admin {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(unique = true)
    private String username;
    @DatabaseField
    private String password;
    @DatabaseField(foreign = true, columnName = "person_id", foreignAutoRefresh = true)
    private Person profile;

    Admin() {

    }

    public Admin(String username, String password, Person person) {
        this.username = username;
        this.password = password;
        this.profile = person;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(Person profile) {
        this.profile = profile;
    }

    public Person getProfile() {
        return profile;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
