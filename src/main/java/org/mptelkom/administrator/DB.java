package org.mptelkom.administrator;

import org.mptelkom.administrator.models.Admin;
import org.mptelkom.administrator.models.Group;
import org.mptelkom.administrator.models.Person;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DB {
    public final static String DB_URL = "jdbc:sqlite:db.sqlite";

    public static void createTable() throws Exception {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(DB_URL);
            TableUtils.createTable(connectionSource, Person.class);
            TableUtils.createTable(connectionSource, Group.class);
            TableUtils.createTable(connectionSource, Admin.class);
        } finally {
            if (connectionSource != null) {
                connectionSource.close();
            }
        }
    }

    public static void seedDb() {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(DB_URL);
            Dao<Person, Integer> personDao = DaoManager.createDao(connectionSource, Person.class);
            Dao<Group, Integer> groupDao = DaoManager.createDao(connectionSource, Group.class);
            Dao<Admin, Integer> adminDao = DaoManager.createDao(connectionSource, Admin.class);
            Group pengurus = new Group("Pengurus");
            Group anggota = new Group("Anggota");
            Group senior = new Group("Senior");
            groupDao.create(pengurus);
            groupDao.create(anggota);
            groupDao.create(senior);
            Person person = new Person("Bobi", "1122");
            person.setGroup(pengurus);
            personDao.create(person);
            Admin admin = new Admin("bobi", "1234", person);
            adminDao.create(admin);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
