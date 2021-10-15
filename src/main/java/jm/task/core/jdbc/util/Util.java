package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/qqq1";
    private final static String NAME = "root";
    public final  static String PAS = "dredd1991";

    public static Connection getconnectionjdbc() throws SQLException {
        return DriverManager.getConnection(URL, NAME, PAS);
    }


}
