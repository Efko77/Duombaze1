package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDbUtils {

    private  static  final String URL = "jdbc:mysql://127.0.0.1:3306/krc"; //konstantos nurodo duonbazes adresa.
    private static final String NAME = "root";
    private  static final String PASSWORD ="";//nepalikti tarpo arba reikalingas paswordas


    public static Connection createConnection(){
        Connection connection = null; // susikureme objekta Connection kuris yra lygus 0
        try {
            connection =DriverManager.getConnection(URL, NAME, PASSWORD);//driveris
        } catch (SQLException e) {
            System.out.println("prisijungti nepavyko" + e); //jei neprisijungia tai objekto reiksme lieka lygi null
        }
        return connection;

    }

}
