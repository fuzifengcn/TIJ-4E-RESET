package annotation.practice;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnectUtil {

    public static Statement getSqlSession(String classPath){
        InputStream resourceAsStream = DBConnectUtil.class.getClassLoader().getResourceAsStream(classPath);
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("not found database configuration");
            System.exit(0);
        }
        try {
            Class.forName(properties.getProperty("database.driver"));
        } catch (ClassNotFoundException e) {
            System.err.println("not found driver class");
            System.exit(0);
        }
        String url = properties.getProperty("database.url");
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            return connection.createStatement();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        return null;
    }


}
