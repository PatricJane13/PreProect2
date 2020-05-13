package DAO;

import util.DBHelper;
import util.PropertyReader;

import java.util.Properties;

public class UserDaoFactory {
    public static UserDAO create() {
        Properties properties = PropertyReader.getProperties();
        assert properties != null;
        String typeOfDao = properties.getProperty("daotype");
            switch (typeOfDao) {
                case "hibernate": {
                    return new UserHibernateDAO();
                }
                case "jdbc":
                    return new UserJdbcDAO(DBHelper.getMySqlConnectionJDBC());
                default:
                    throw new IllegalStateException("Ошибка выбора ДАО");
            }
    }
}
