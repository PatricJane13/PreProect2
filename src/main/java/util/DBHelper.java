package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static DBHelper dbHelper;
    private static SessionFactory sessionFactory;

    public static DBHelper dbHelper() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    private DBHelper() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    public static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        Properties properties = PropertyReader.getProperties();

        assert properties != null;
        configuration.setProperty("hibernate.dialect", properties.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", properties.getProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url", properties.getProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", properties.getProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", properties.getProperty("hibernate.connection.password"));
        configuration.setProperty("hibernate.show_sql", properties.getProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hibernate.hbm2ddl.auto"));
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    public static Connection getMySqlConnectionJDBC() {
        try {
            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("pre_project?").         //db name
                    append("user=root&").           //login
                    append("password=root1");       //password

            DriverManager.registerDriver(DriverManager.getDriver(url.toString()));
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

}
