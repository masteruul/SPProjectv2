package org.mptelkom.administrator.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import org.mptelkom.administrator.DB;
import org.mptelkom.administrator.models.Admin;

import java.sql.SQLException;

public class Authenticator {
    public static Admin checkLogin(String username, String password) {
        Admin admin = null;
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(DB.DB_URL);
            Dao<Admin, Integer> adminDao = DaoManager.createDao(connectionSource, Admin.class);
            QueryBuilder<Admin, Integer> query = adminDao.queryBuilder();
            admin = adminDao.queryForFirst(
                    query.where()
                            .eq("username", username)
                            .and()
                            .eq("password", password)
                            .prepare()
            );

        } catch (SQLException e) {
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

        return admin;
    }
}
